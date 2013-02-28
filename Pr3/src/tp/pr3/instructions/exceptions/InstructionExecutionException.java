package tp.pr3.instructions.exceptions;

public class InstructionExecutionException extends Exception {

	private static final long serialVersionUID = 1L;

	public InstructionExecutionException() {
		// TODO Auto-generated constructor stub
	}

	public InstructionExecutionException(String message) {
		//super(message);
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	public InstructionExecutionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InstructionExecutionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InstructionExecutionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return message;
	}

	
	private String message;
}
