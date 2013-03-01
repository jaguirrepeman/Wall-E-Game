package tp.pr3.cityLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import tp.pr3.City;
import tp.pr3.Direction;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.*;

public class CityLoaderFromTxtFile {
    public CityLoaderFromTxtFile(){
        
    }
    private void forceActualString(String expected) throws IOException{
    	try{
    		String value = stk.sval;
	        if ((value == null) || (!value.equals(expected))){
	            throw new IOException("Error, se esperaba " +  expected + " en la línea "  + stk.lineno() +  " y se encontró " + stk.sval); 
        }
    	}catch(IOException e){
    		throw e;
    	}
    }
   private String forceString() throws IOException{
	   stk.nextToken();
    	if (stk.sval != null) return stk.sval;
    	else throw new IOException();
        
    }
   
   private String getStringValue() throws IOException {
	   if (stk.sval != null)
		   return stk.sval;
	   throw new IOException();
   }
    private int forceNumber()throws IOException{
    	stk.nextToken();
    	if (stk.ttype == StreamTokenizer.TT_NUMBER) return (int) stk.nval;
    	else throw new IOException();
        
    }
	private Direction forceDirection() throws IOException {
		stk.nextToken();
		String value = stk.sval;
	
		//if ((stk.nextToken() != StreamTokenizer.TT_WORD) || (!((value.equals("EAST")) ||(value.equals("NORTH"))|| (value.equals("SOUTH")) || (value.equals("UNKNOWN")) || (value.equals("WEST"))))){
		//	throw new IOException();
		//}
		if (value == null) throw new IOException();		
		else if (value.equalsIgnoreCase("EAST")) return Direction.EAST;
		else if (value.equalsIgnoreCase("NORTH")) return Direction.NORTH;
		else if (value.equalsIgnoreCase("SOUTH")) return Direction.SOUTH;
		else if (value.equalsIgnoreCase("UNKNOWN")) return Direction.UNKNOWN;
		else if (value.equalsIgnoreCase("WEST")) return Direction.WEST;
		else throw new IOException();
		//else return null;
		 
		
	}
    private boolean forceString (String ex1, String ex2) throws IOException{
    	String ex;
    	stk.nextToken();
    	ex = stk.sval;
    	if (ex.equalsIgnoreCase(ex1)) return false;
    	else if (ex.equalsIgnoreCase(ex2)) return true;
    	else throw new IOException();
    }
    private void forceString(String expected) throws IOException{
    	try{
    		stk.nextToken();
    		String value = stk.sval;
	        if ((value == null) || (!value.equals(expected))){
	            throw new IOException("Error, se esperaba " +  expected + " en la línea "  + stk.lineno() +  " y se encontró " + stk.sval); 
        }
    	}catch(IOException e){
    		throw e;
    	}
    }
    private void forceNumber(int expected) throws IOException{
    	stk.nextToken();
        if ((stk.ttype != StreamTokenizer.TT_NUMBER) || ((int)stk.nval != expected)){
            throw new IOException("Error, se esperaba " +  expected + " en la línea "  + stk.lineno() +  " y se encontró " + stk.sval); 
        }
    }
    private int checkNumber() throws IOException{ //FALTA IMPLEMENTAR
    	stk.nextToken();
    	int num = (int) stk.nval;
    	if ((num >= places.size()) || num < 0) throw new IOException();
		//el numero siguiente debe ser el numero de un place
    	return num;
		
	}
    private Place parsePlace(int num) throws IOException{
        boolean spaceship;
        //try{
	        //forceString("place");
        	if (!getStringValue().equals("place"))
        		throw new IOException();
	        forceNumber (num);
	        String name = forceString();
	        String desc = forceString();
	        spaceship = forceString ("noSpaceShip", "SpaceShip");       
	        Place ret = new Place(name, spaceship, desc.replace('_', ' '));
	        return ret;
       // } catch(IOException e){
        	
        //}throw new IOException();
        

    }
    private Street parseStreet(int num) throws IOException{
        boolean open = true;
        String key = null;
        int initPlace, targetPlace;
        try{
	        Direction dir;
	        forceActualString("street");
	        forceNumber (num);
	        forceString("place");
	        initPlace = checkNumber();
	        dir = forceDirection();//
	        forceString("place");
	        targetPlace = checkNumber();
	        open = forceString ("closed", "open");
	        if (!open) key = forceString();
	        Street ret = new Street(places.get(initPlace), dir, places.get(targetPlace), open, key);
	        return ret;
        } catch(IOException e){
        	
        }throw new IOException();
        

    }

	private Item parseItem(int num) throws IOException{
		Item ret;
		int number;
		//try{
        //forceString("item");
        //forceNumber (num);
        
        String desc = getStringValue();

        if (desc.equals("garbage")){
        	ret = leerGarbage(num);
        }
        else if (desc.equals("fuel")){
        	ret = leerFuel(num);
        }
        else if (desc.equals("codecard")){
        	ret = leerCodecard(num);
        }
      
        else throw new IOException();
        forceString();
        number = checkNumber();
        this.places.get(number).addItem(ret);
        return ret;   
		
   
	}
    private Garbage leerGarbage(int num) throws IOException {
		String id, description;
		int recycledMaterial;
		forceNumber(num);
		id = forceString();
		description = forceString();
		recycledMaterial = forceNumber();
		return new Garbage(id, description.replace('_', ' '), recycledMaterial);
        
    }
    private CodeCard leerCodecard(int num) throws IOException {
    	String id, description, code;
    	forceNumber(num);
		id = forceString();
		description = forceString();
		code = forceString();
		return new CodeCard(id, description.replace('_', ' '), code);
		
	}
	private Fuel leerFuel(int num) throws IOException {
		String id, description;
		int power, times;
		forceNumber(num);
		id = forceString();
		description = forceString();
		power = forceNumber();
		times = forceNumber();
		return new Fuel(id, description.replace('_', ' '), power, times);
		
	}
	private void parsePlaces() throws IOException{
        int i = 0;
        boolean ok = true;
        forceString("BeginPlaces");
        stk.nextToken();
        while (!getStringValue().equalsIgnoreCase("EndPlaces") && ok){
        	try{
        		//get
	            Place p = parsePlace(i);
	            places.add(p);
//<<<<<<< .mine
	            stk.nextToken();
//=======
//	            if (i == 0) this.initialPlace = p;
//>>>>>>> .r86
	            i++;
        	}catch (IOException e){
        		ok = false;
        	}
                      
        }  if (!getStringValue().equalsIgnoreCase("EndPlaces"))
        	throw new IOException();
        //forceString ("EndPlaces");
    }
    private void parseStreets() throws IOException{
        int i = 0;
        boolean ok = true;
        forceString("BeginStreets");
        stk.nextToken();
        while (!getStringValue().equalsIgnoreCase("EndStreets") && ok){
        	try{
	            Street str = parseStreet(i);
	            this.aCity.addStreet(str);
	            stk.nextToken();
	            i++;
	            
        	}catch(IOException e){
        		ok = false;
        	}
                  
        }    
        forceActualString ("EndStreets");
    }
    private void parseItems() throws IOException{
    	int i = 0;
        boolean ok = true;
        forceString("BeginItems");
        stk.nextToken();
        while (!getStringValue().equalsIgnoreCase("EndItems") && ok){
        	try{
	            parseItem(i);	
	            stk.nextToken();
	            i++;
        	}catch (IOException e){
        		ok = false;
        	}
               
        }    
        forceActualString ("EndItems");
                              
    }

    public City loadCity(java.io.InputStream file) throws /*java.io.IOException*/ WrongCityFormatException/**/{
        stk = new StreamTokenizer(new InputStreamReader(file));
        stk.wordChars('\u0021', '\u007E');
        stk.quoteChar('"');
        try{
			forceString ("BeginCity");
			parsePlaces();
	        parseStreets();
	        parseItems();        
	        forceString("EndCity");
        
        }catch (IOException exc){
        	throw new WrongCityFormatException();
        }return aCity;
        
    }
    public Place getInitialPlace(){
        return places.get(0);
    }
    private Place initialPlace; // hay que ponerlo en los bucles!!!
    private StreamTokenizer stk;
    private City aCity = new City();
    private ArrayList<Place> places = new ArrayList<Place>();
    //private String filename;
}