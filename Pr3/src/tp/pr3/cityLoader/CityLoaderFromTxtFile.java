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
   private String forceString()throws IOException{
    	if ((stk.nextToken() != StreamTokenizer.TT_WORD)) return stk.sval;
    	else throw new IOException();
        
    }
    private int forceNumber()throws IOException{
    	if ((stk.nextToken() != StreamTokenizer.TT_WORD)) return (int) stk.nval;
    	else throw new IOException();
        
    }
	private Direction forceDirection() throws IOException {
		String value = stk.sval;
	
		if ((stk.nextToken() != StreamTokenizer.TT_WORD) || (!((value.equals("EAST")) ||(value.equals("NORTH"))|| (value.equals("SOUTH")) || (value.equals("UNKNOWN")) || (value.equals("WEST"))))){
			throw new IOException();
		}
				
		else if (value.equals("EAST")) return Direction.EAST;
		else if (value.equals("NORTH")) return Direction.NORTH;
		else if (value.equals("SOUTH")) return Direction.SOUTH;
		else if (value.equals("UNKNOWN")) return Direction.UNKNOWN;
		else if (value.equals("WEST")) return Direction.WEST;
		else return null;
		 
		
	}
    private boolean forceString (String ex1, String ex2) throws IOException{
    	String ex;
    	ex = stk.sval;
    	if (ex.compareTo(ex1) == 1) return false;
    	else if (ex.compareTo(ex2) == 1) return true;
    	else throw new IOException();
    }
    private void forceString(String expected) throws IOException{
    	try{
        if ((stk.nextToken() != StreamTokenizer.TT_WORD) || (!stk.sval.equals(expected))){
            throw new IOException("Error, se esperaba " +  expected + " en la línea "  + stk.lineno() +  " y se encontró " + stk.sval); 
        }
    	}catch(IOException e){
    		throw e;
    	}
    }
    private void forceNumber(int expected) throws IOException{
        if ((stk.nextToken() != StreamTokenizer.TT_NUMBER) || (stk.nval != expected)){
            throw new IOException("Error, se esperaba " +  expected + " en la línea "  + stk.lineno() +  " y se encontró " + stk.sval); 
        }
    }
    private int checkNumber() throws IOException{ //FALTA IMPLEMENTAR
    	int num = (int) stk.nval;
    	if (num >= places.size()) throw new IOException();
		//el numero siguiente debe ser el numero de un place
    	return num;
		
	}
    private Place parsePlace(int num) throws IOException{
        boolean spaceship;
        //try{
	        forceString("place");
	        forceNumber (num);
	        String name = forceString();
	        String desc = forceString();
	        spaceship = forceString ("noSpaceShip", "SpaceShip");       
	        Place ret = new Place(name, spaceship, desc);
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
        forceString("street");
        forceNumber (num);
        forceString("place");
        initPlace = checkNumber();
        dir = forceDirection();
        forceString("place");
        targetPlace = checkNumber();
        open = forceString ("open", "closed");
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
        forceString("item");
        forceNumber (num);
        
        String desc = forceString();

        if (desc.equals("Garbage")){
        	ret = leerGarbage(num);
        }
        else if (desc.equals("Fuel")){
        	ret = leerFuel(num);
        }
        else if (desc.equals("Codecard")){
        	ret = leerCodecard(num);
        }
      
        else throw new IOException();
        forceString();
        number = checkNumber();
        this.places.get(number).addItem(ret);
        return ret;   
		//}catch(IOException e){
			
		//}throw new IOException();
   
	}
    private Garbage leerGarbage(int num) throws IOException {
		String id, description;
		int recycledMaterial;
		id = forceString();
		description = forceString();
		recycledMaterial = forceNumber();
		return new Garbage(id, description, recycledMaterial);
        
    }
    private CodeCard leerCodecard(int num) throws IOException {
    	String id, description, code;
		id = forceString();
		description = forceString();
		code = forceString();
		return new CodeCard(id, description, code);
		
	}
	private Fuel leerFuel(int num) throws IOException {
		String id, description;
		int power, times;
		id = forceString();
		description = forceString();
		power = forceNumber();
		times = forceNumber();
		return new Fuel(id, description, power, times);
		
	}
	private void parsePlaces() throws IOException{
        int i = 1;
        boolean ok = true;
        forceString("BeginPlaces");
        while (ok){
        	try{
	            Place p = parsePlace(i);
	            places.add(p);
	            i++;
        	}catch (IOException e){
        		ok = false;
        	}
                      
        }    
        forceString ("EndPlaces");
    }
    private void parseStreets() throws IOException{
        int i = 1;
        boolean ok = true;
        forceString("BeginStreets");
        while (ok){
        	try{
	            Street str = parseStreet(i);
	            this.aCity.addStreet(str);
	            i++;
        	}catch(IOException e){
        		ok = false;
        	}
                  
        }    
        forceString ("EndStreets");
    }
    private void parseItems() throws IOException{
    	int i = 1;
        boolean ok = true;
        forceString("BeginItems");
        while (ok){
        	try{
	            parseItem(i);	
	            
	            i++;
        	}catch (IOException e){
        		ok = false;
        	}
            
            //FALTA EL add en el array        
        }    
        forceString ("EndItems");
                              
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
        return this.initialPlace;
    }
    private Place initialPlace; // hay que ponerlo en los bucles!!!
    private StreamTokenizer stk;
    private City aCity;
    private ArrayList<Place> places;
    //private String filename;
}