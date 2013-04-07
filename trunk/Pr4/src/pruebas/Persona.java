package pruebas;
public class Persona {
	private long dni;
	private String nombre;

	public Persona() {
		dni = -1;
		nombre = "";
	}

	public Persona(long nuevoDni, String nuevoNombre) {
		this.dni = nuevoDni;
		this.nombre = nuevoNombre;
	}

	public void mostrar() {
		System.out.println("Nombre: " + this.nombre);
		System.out.println("DNI: " + this.dni);
	}
}