package pruebas;
import java.util.Vector;

class Inventario {
	Vector<GlugluItem> v = new Vector<GlugluItem>();

	public Inventario() {

	}

	public int getNumItems() {
		return v.size();
	}

	public void addItem(GlugluItem i) {
		v.add(i);
	}

	public GlugluItem getItem(int i) {
		return v.elementAt(i);
	}
}