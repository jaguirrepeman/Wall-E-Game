package pruebas;
public class Ejemplo1 {
	public void metodoQueLanzaExcepcion() {
		
		throw new RuntimeException("Esto no se tiene por qué declarar");
	}

	public static void main(String args[]) {
		new Ejemplo1().metodoQueLanzaExcepcion();
	}
}
/*public class MiExcepcion extends Exception
{
   public MiExcepcion() {
  }
   public void miPropioMetodo() {
      
   }
}

public class MiClase{
   public int miMetodoQueLanzaExcepcionSiHayFallo (...) throws MiExcepcion
   {
      //ejecutoMiCodigo();
      // si hay fallo, se lanza la excepcion.
      if (/*hayFallo()*//*true)
         throw new MiExcepcion();
      return resultado;
   }*/
//S}