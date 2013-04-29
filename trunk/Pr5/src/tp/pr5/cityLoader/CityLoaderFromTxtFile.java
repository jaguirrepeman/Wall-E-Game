package tp.pr5.cityLoader;

//import java.io.WrongCityFormatException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.*;
import tp.pr5.cityLoader.cityLoaderExceptions.*;

@SuppressWarnings("unused")


public class CityLoaderFromTxtFile {
	public CityLoaderFromTxtFile() {

	}

	private void forceActualString(String expected) throws WrongCityFormatException {
		
		String value = stk.sval;
		if ((value == null) || (!value.equals(expected))) {
			throw new WrongCityFormatException("Error, se esperaba " + expected
					+ " en la línea " + stk.lineno() + " y se encontró "
					+ stk.sval);
		}
	
	}

	private String forceString() throws WrongCityFormatException {
		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		if (stk.sval != null)
			return stk.sval;
		else
			throw new WrongCityFormatException();

	}

	private String getStringValue() throws WrongCityFormatException {
		if (stk.sval != null)
			return stk.sval;
		throw new WrongCityFormatException();
	}

	private int forceNumber() throws WrongCityFormatException {
		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		if (stk.ttype == StreamTokenizer.TT_NUMBER)
			return (int) stk.nval;
		else
			throw new WrongCityFormatException();

	}

	private Direction forceDirection() throws WrongCityFormatException {
		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		String value = stk.sval;

		if (value == null)
			throw new WrongCityFormatException();
		else if (value.equalsIgnoreCase("EAST"))
			return Direction.EAST;
		else if (value.equalsIgnoreCase("NORTH"))
			return Direction.NORTH;
		else if (value.equalsIgnoreCase("SOUTH"))
			return Direction.SOUTH;
		else if (value.equalsIgnoreCase("UNKNOWN"))
			return Direction.UNKNOWN;
		else if (value.equalsIgnoreCase("WEST"))
			return Direction.WEST;
		else
			throw new WrongCityFormatException();

	}

	private boolean forceString(String ex1, String ex2) throws WrongCityFormatException {
		String ex;
		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		ex = stk.sval;
		if (ex != null) {
			if (ex.equalsIgnoreCase(ex1))
				return false;
			else if (ex.equalsIgnoreCase(ex2))
				return true;
			else
				throw new WrongCityFormatException();
		} else
			throw new WrongCityFormatException();
	}

	private void forceString(String expected) throws WrongCityFormatException {

		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		String value = stk.sval;
		if ((value == null) || (!value.equals(expected))) {
			throw new WrongCityFormatException("Error, se esperaba " + expected
					+ " en la línea " + stk.lineno() + " y se encontró "
					+ stk.sval);
		}

	}

	private void forceNumber(int expected) throws WrongCityFormatException {
		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		if ((stk.ttype != StreamTokenizer.TT_NUMBER)) {
			throw new WrongCityFormatException("Error, se esperaba " + expected
					+ " en la línea " + stk.lineno() + " y se encontró "
					+ stk.sval);
		} else if (((int) stk.nval != expected)) {
			throw new WrongCityFormatException("Error, se esperaba " + expected
					+ " en la línea " + stk.lineno() + " y se encontró "
					+ stk.sval);
		}

	}

	private int checkNumber() throws WrongCityFormatException { //
		try {
			stk.nextToken();
		} catch (IOException e) {
			
		}
		int num = (int) stk.nval;
		if ((num >= places.size()) || num < 0)
			throw new WrongCityFormatException();
		// el numero siguiente debe ser el numero de un place
		return num; 

	}

	private Place parsePlace(int num) throws WrongCityFormatException {
		boolean spaceship;

		if (!getStringValue().equals("place"))
			throw new WrongCityFormatException();
		forceNumber(num);
		String name = forceString();
		String desc = forceString();
		spaceship = forceString("noSpaceShip", "SpaceShip");
		Place ret = new Place(name, spaceship, desc.replace('_', ' '));
		return ret;

	}

	private Street parseStreet(int num) throws WrongCityFormatException {
		boolean open = true;
		String key = null;
		int initPlace, targetPlace;
			
		Direction dir;
		forceActualString("street");
		forceNumber(num);
		forceString("place");
		initPlace = checkNumber();
		dir = forceDirection();//
		forceString("place");
		targetPlace = checkNumber();
		open = forceString("closed", "open");
		if (!open)
			key = forceString();
		Street ret = new Street(places.get(initPlace), dir,
				places.get(targetPlace), open, key);
		return ret;

	}

	private Item parseItem(int num) throws WrongCityFormatException {
		Item ret;
		int number;

		String desc = getStringValue();

		if (desc.equals("garbage")) {
			ret = leerGarbage(num);
		} else if (desc.equals("fuel")) {
			ret = leerFuel(num);
		} else if (desc.equals("codecard")) {
			ret = leerCodecard(num);
		}

		else
			throw new WrongCityFormatException();
		forceString();
		number = checkNumber();
		this.places.get(number).addItem(ret);
		return ret;

	}

	private Garbage leerGarbage(int num) throws WrongCityFormatException {
		String id, description;
		int recycledMaterial;
		forceNumber(num);
		id = forceString();
		description = forceString();
		recycledMaterial = forceNumber();
		return new Garbage(id, description.replace('_', ' '), recycledMaterial);

	}

	private CodeCard leerCodecard(int num) throws WrongCityFormatException {
		String id, description, code;
		forceNumber(num);
		id = forceString();
		description = forceString();
		code = forceString();
		return new CodeCard(id, description.replace('_', ' '), code);

	}

	private Fuel leerFuel(int num) throws WrongCityFormatException {
		String id, description;
		int power, times;
		forceNumber(num);
		id = forceString();
		description = forceString();
		power = forceNumber();
		times = forceNumber();
		return new Fuel(id, description.replace('_', ' '), power, times);

	}

	private void parsePlaces() throws WrongCityFormatException {
		int i = 0;
		forceString("BeginPlaces");
		try {
			stk.nextToken();
			
			while (!getStringValue().equalsIgnoreCase("EndPlaces") ) {
				
					Place p = parsePlace(i);
					places.add(p);
					stk.nextToken();
					i++;
			}
		} catch (IOException e1) {
			
		}
		forceActualString("EndPlaces");
		//if (!getStringValue().equalsIgnoreCase("EndPlaces"))
		//	throw new WrongCityFormatException();
	}

	private void parseStreets() throws WrongCityFormatException {
		int i = 0;
		forceString("BeginStreets");
		try {
			stk.nextToken();
		
			while (!getStringValue().equalsIgnoreCase("EndStreets")) {
				
					Street str = parseStreet(i);
					this.aCity.addStreet(str);
					stk.nextToken();
					i++;
	
			}
		} catch (IOException e1) {
			
		}
		forceActualString("EndStreets");
	}

	private void parseItems() throws WrongCityFormatException {
		int i = 0;
		forceString("BeginItems");
		try {
			stk.nextToken();		
			
			while (!getStringValue().equalsIgnoreCase("EndItems")) {
				parseItem(i);
				stk.nextToken();
				i++;
			}
		} catch (IOException e1) {
			
		}
		forceActualString("EndItems");

	}

	public City loadCity(java.io.InputStream file) throws WrongCityFormatException {
		stk = new StreamTokenizer(new InputStreamReader(file));
		stk.wordChars('\u0021', '\u007E');
		stk.quoteChar('"');
			forceString("BeginCity");
			parsePlaces();
			parseStreets();
			parseItems();
			forceString("EndCity");
		return aCity;

	}

	public Place getInitialPlace() {
		return places.get(0);
	}

	private StreamTokenizer stk;
	private City aCity = new City();
	private ArrayList<Place> places = new ArrayList<Place>();
}