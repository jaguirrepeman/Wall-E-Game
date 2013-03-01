package tp.pr3;

public class City {

	public City() {
	}

	public City(Street[] cityMap) {
		this.cityMap = cityMap;

	}

	public void addStreet(Street street) {
		if (this.cityMap == null){
			this.cityMap = new Street[1];
			this.cityMap[0] = street;
		}else{
			Street[] aux = new Street[this.cityMap.length + 1];
			for (int i=0; i< this.cityMap.length; i++)
				aux[i] = this.cityMap[i];
			aux[this.cityMap.length] = street;
			this.cityMap = aux;
		}
	
	}

	public Street lookForStreet(Place currentPlace, Direction currentHeading) {
		if (!(this.cityMap == null)) {
			for (int i = 0; i < cityMap.length; i++) {
				if ((cityMap[i].comeOutFrom(currentPlace, currentHeading))
						&& (!(cityMap[i] == null)))
					return cityMap[i];
			}
		}
		return null;
	}

	private Street[] cityMap = null;

}
