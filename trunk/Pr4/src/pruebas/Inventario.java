package pruebas;
import java.util.Vector;

class Inventario {
	Vector<Item> v = new Vector<Item>();

	public Inventario() {

	}

	public int getNumItems() {
		return v.size();
	}

	public void addItem(Item i) {
		v.add(i);
	}

	public Item getItem(int i) {
		return v.elementAt(i);
	}
}