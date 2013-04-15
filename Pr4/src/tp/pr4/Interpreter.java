package tp.pr4;

import tp.pr4.instructions.*;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;

public class Interpreter {

	public static Instruction generateInstruction(String line)
			throws WrongInstructionFormatException {

		for (int i = 0; i < instructions.length; i++) {
			try {
				return instructions[i].parse(line);
			} catch (WrongInstructionFormatException exc) {

			}
		}
		throw new WrongInstructionFormatException(
				"I do not understand. Please repeat.");

	}

	public static String interpreterHelp() {
		String help = "The valid instructions for Wall·E are: "
				+ LINE_SEPARATOR;
		for (int i = 0; i < instructions.length; i++)
			help = help + instructions[i].getHelp() + LINE_SEPARATOR;
		return help;
	}

	static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static Instruction[] instructions = { new MoveInstruction(),
			new DropInstruction(), new HelpInstruction(),
			new OperateInstruction(), new PickInstruction(),
			new QuitInstruction(), new RadarInstruction(),
			new ScanInstruction(), new TurnInstruction(),
			new UndoInstruction()

	};
}
