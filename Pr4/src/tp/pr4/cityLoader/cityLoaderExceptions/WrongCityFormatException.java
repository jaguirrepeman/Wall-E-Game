package tp.pr4.cityLoader.cityLoaderExceptions;

import java.io.IOException;

public class WrongCityFormatException extends IOException {

	private static final long serialVersionUID = 1L;

	public WrongCityFormatException() {
		// TODO Auto-generated constructor stub
	}

	public WrongCityFormatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WrongCityFormatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WrongCityFormatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
