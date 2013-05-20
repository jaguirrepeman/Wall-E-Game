package tp.pr5.items;

import java.util.Arrays;
import java.util.List;



//import java.util.ArrayList;
//TODO java.util.Collections addAll;
public class ItemContainer extends tp.pr5.Observable<InventoryObserver>{
	public ItemContainer() {
		
		this.container = new Item[1];
		counter = 0;
	}
	
	public boolean containsItem(String id){
		return (searchItem(id) != -1);
	}

	public int numberOfItems() {
		return counter;
	}

	public boolean addItem(Item item) {
		if(searchItem(item.getId()) != -1) return false;
		else {
			int length = container.length;
			if (counter == length){
				Item[] auxContainer = new Item[counter];
				auxContainer = this.container;
				this.container = new Item[length * 2];
				for (int i = 0; i < length; i++)
					this.container[i] = auxContainer[i];
			}
			{
				int i = 0;
				while ((i < counter)
						&& (item.getId().compareToIgnoreCase(container[i].getId()) > -1))
					i++;
				// Insertamos en la posición i
				for (int j = counter; j > i; j--)
					// Desplazamos una posición a la derecha
					container[j] = container[j - 1];
				container[i] = item;
				counter++;
			}
			//Avisamos a los observadores de que el inventorio ha cambiado
			this.emitInventoryChange();
			return true;
		}

	}

	public int searchItem(String id) {
		int posicion = -1;
		if (! (this.counter == 0)){
			int ini = 0, fin = counter - 1, mitad = 0;
			boolean encontrado = false;
			while ((ini <= fin) && !encontrado) {
				mitad = (ini + fin) / 2; // División entera
				if (id.equalsIgnoreCase(container[mitad].getId()))
					encontrado = true;
				else if (id.compareToIgnoreCase(container[mitad].getId()) < 0)
					fin = mitad - 1;
				else
					ini = mitad + 1;
			}
			if (encontrado)
				posicion = mitad;
			
		}return posicion;
	}

	public Item getItem(String id) {
		int posicion = searchItem(id);
		if (posicion >= 0)
			return container[posicion];
		else
			return null;
	}

	public Item pickItem(String id) {
		getItem(id);
		Item item;
		int posicion = searchItem(id);
		if (posicion >= 0) {
			item = container[posicion];
			for (int i = posicion; i < counter-1; i++) {
				container[i] = container[i + 1];
			}container[counter-1] = null;
			counter--;
			//Avisamos a los observadores de que el inventorio ha cambiado
			this.emitInventoryChange();
			return item;
		} else
			return null;
	}
	
	public void requestScanCollection(){
		emitScanCollection();
	}
	
	public void requestScanItem(String id){
		//
		for(InventoryObserver o: this.observers)
			o.itemScanned(getItem(id).toString());
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < counter; i++) {
			string += "   " + container[i].getId() + LINE_SEPARATOR;
		}
		return string;
	}
	//devuelve un array de Strings con el id y la descripcion del objeto para mostrarlo en la tabla
	public String[] itemForTable(int n){
		String[] object= new String[2];
		object[0] = container[n].getId();
		object[1] = container[n].description;
		return object;
		
	}
	
	private List<Item> inventoryToList(){
		/*List<Item> returnList = new ArrayList<Item>();
		Collections.addAll(returnList, this.container);
		return returnList.subList(0, counter);
		*/
		return Arrays.asList(this.container).subList(0, counter);
		
	}
	
	public void useItem(Item item){
		
	}
	
	public void addItemContainerObserver(InventoryObserver c){
		this.addObserver(c);
	}
	
	private void emitScanCollection(){
		
		for(InventoryObserver obs: this.observers)
			obs.inventoryScanned("I am carrying the following items "
					+ LINE_SEPARATOR + this.toString());
	
	}
	
	private void emitInventoryChange(){
		
		for (InventoryObserver obs: this.observers)
			obs.inventoryChange(this.inventoryToList());
	}
	
	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	private Item[] container;
	private int counter;
	
}