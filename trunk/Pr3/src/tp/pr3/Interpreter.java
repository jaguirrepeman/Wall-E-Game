package tp.pr3;

import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.*;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;


public class Interpreter {
	
	public static Instruction generateInstruction (String line) throws WrongInstructionFormatException {

		for (int i= 0; i< instructions.length; i++){
			try {

				return instructions[i].parse(line);
				
			}catch (WrongInstructionFormatException exc) {
				
			}
		}throw new WrongInstructionFormatException("jfefjie");
		
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
	private static Instruction[] instructions = {
			new MoveInstruction(),
			new DropInstruction(),
			new HelpInstruction(),
			new OperateInstruction(),
			new PickInstruction(),
			new QuitInstruction(),
			new RadarInstruction(),
			new ScanInstruction(),
			new TurnInstruction()

	};
}
