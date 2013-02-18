package tp.pr3;

import tp.pr3.items.Item;

public class NavigationModule {
	
	
	public NavigationModule(City aCity, Place initialPlace){
		
		this.city = aCity;
		this.place = initialPlace;
	}
	
	public boolean	atSpaceship(){
		
		return place.isSpaceship();
		
	}
	
	public void	dropItemAtCurrentPlace(Item it){
		
		
	}
	
	public boolean	findItemAtCurrentPlace(java.lang.String id){
		return false;
		
	}
	
	public Direction getCurrentHeading(){
		return null;
	}
	
	public Place getCurrentPlace(){
		return place;
		
	}
	
	public Street getHeadingStreet(){
		return null;
		
	}
	
	public void	initHeading(Direction heading){
		
	}
	
	public void	move(){
		
	}
	
	public Item	pickItemFromCurrentPlace(String id){
		return null;
		
	}
	
	public void	rotate(Rotation rotation){
		
	}
	
	public void	scanCurrentPlace(){
		
	}
	
	private City city;
	private Place place;
}
