package tp.pr3;

public class City{
	
	public City(){		
	}
	public City(Street[] cityMap){	
		this.cityMap  = cityMap;
		
	}
	public Street lookForStreet(Place currentPlace, Direction currentHeading){
		if (!(this.cityMap == null)){
			for (int i = 0; i < cityMap.length; i++){
				if((cityMap[i].comeOutFrom(currentPlace, currentHeading))  && (!(cityMap[i] == null))) return cityMap[i];	
			}
		}
		return null;
	}
	private Street[] cityMap;
	
}

/* Apuntes de Marco
 *  bool escribe (String a){
 * 		System.out.println(a);
 * 		return true;
 * 	}
 *  if (escribe("p") || escribe ("y")){
 * 
 *  }
 */
