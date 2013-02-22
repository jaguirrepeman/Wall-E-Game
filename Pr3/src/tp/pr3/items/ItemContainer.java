package tp.pr3.items;

//import java.util.ArrayList;

public class ItemContainer {
	public ItemContainer() {
		this.container = new Item[1];
		counter = 0;

	}

	public int numberOfItems() {
		return counter;
	}

	public boolean addItem(Item item) {
		if(searchItem(item.getId()) != -1) return false;
		else {
			int length = container.length;
			//System.out.println(length);
			if (counter == length){
				//ok = false; // lista llena
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
			}
			counter--;
			return item;
		} else
			return null;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < counter; i++) {
			string += "   " + container[i].getId() + LINE_SEPARATOR;
		}
		return string;
	}

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	private Item[] container;
	private int counter;
}