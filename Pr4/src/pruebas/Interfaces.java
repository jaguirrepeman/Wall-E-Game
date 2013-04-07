package pruebas;
interface Padre {
	public void mostrar();
}

interface Padre1 extends Padre {
	public void mostrar();
}

interface Padre2 extends Padre {
	public void mostrar();
}

interface Hija extends Padre1, Padre2 {
	public void mostrar();
}

class Ejemplo implements Hija {
	public void mostrar() {
		System.out.println("Ejemplo");
	}

	public static void main(String args[]) {
		Hija hija = new Ejemplo();
		Padre2 padre2 = hija;
		Padre1 padre1 = hija;
		Padre padre = hija;
		hija.mostrar();
		padre2.mostrar();
		padre1.mostrar();
		padre.mostrar();
	}
}