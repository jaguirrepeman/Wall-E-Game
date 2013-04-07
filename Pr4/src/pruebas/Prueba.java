package pruebas;

public class Prueba {
	public Prueba(){
		num = siguiente;
		siguiente++;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	private static int siguiente = 1;
	private int num;
	public int toNum(){
		return this.getNum();
	}
}
