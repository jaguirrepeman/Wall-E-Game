package tp.pr3.instructions.exceptions;

public class WrongInstructionFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongInstructionFormatException() {
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(String message) {
		super(message);
		System.out.println(message);
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WrongInstructionFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
