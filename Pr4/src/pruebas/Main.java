package pruebas;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
int[] uno_diez = new int [10];
		
		try{
			uno_diez[12] = 10;
			
		}catch (java.lang.ArrayIndexOutOfBoundsException excepcion){
			System.err.println("Te pille");
			String command = new String();
			Scanner comando = new Scanner(System.in);
			command = comando.nextLine();

			
		}
		
	}
}