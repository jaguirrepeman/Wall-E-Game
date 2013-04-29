package tp.pr4;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Clase que sobreescribe el OutputStream, de manera que si se ha elegido
 * ejecutar la aplicación por ventana no se escriban las salidas por consola
 * 
 * @author
 * 
 */
public class FilterOutputStream extends OutputStream {

	/**
	 * Constructor
	 * @param out: el stream de salida
	 * @param isWriting: booleano que indica si se debe escribir o no por consola
	 */
	public FilterOutputStream(OutputStream out, boolean isWriting) {
		this.out = out;
		this.isWriting = isWriting;
	}

	/**
	 * Sobreescribe el método write, si isWriting está a falso no escribe por consola
	 */
	@Override
	public void write(int b) throws IOException {
		if (this.isWriting)
			out.write(b);

	}

	private boolean isWriting;
	private OutputStream out;
	 

}
