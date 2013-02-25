package tp.pr3;

import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.*;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;


public class Interpreter {
	
	
	public static Instruction generateInstruction (String line)throws WrongInstructionFormatException {
		String[] comando = line.split(" ");
		Instruction instruction = null;
		boolean b = true;
		while(b){
			instruction = new MoveInstruction();
			instruction.parse(line);
			instruction = new QuitInstruction();
			instruction.parse(line);
			
		}
		return instruction;
	}
	
	public static String interpreterHelp (){
		return "The valid instructions for WALL-E are:"
				+LINE_SEPARATOR+
				"     MOVE"
				+LINE_SEPARATOR+
				"     TURN <LEFT | RIGHT>"
				+LINE_SEPARATOR+
				"     PICK <id>"
				+LINE_SEPARATOR+
				"     SCAN [ <id> ]"
				+LINE_SEPARATOR+
				"     OPERATE <id>"
				+LINE_SEPARATOR+
				"     HELP"
				+LINE_SEPARATOR+
				"     QUIT"
				+LINE_SEPARATOR;
	}
	static final String LINE_SEPARATOR = System.getProperty("line.separator");
}
