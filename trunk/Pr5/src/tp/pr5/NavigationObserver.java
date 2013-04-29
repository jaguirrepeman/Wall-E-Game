package tp.pr5;

public interface NavigationObserver {
	
	void initNavigationModule(PlaceInfo initialPlace, Direction heading);
	
	void robotArrivesAtPlace(Direction heading,  PlaceInfo place);
	
	void placeScanned(PlaceInfo placeDescription);
	
	void placeHasChanged(PlaceInfo placeDescription);
	
}
