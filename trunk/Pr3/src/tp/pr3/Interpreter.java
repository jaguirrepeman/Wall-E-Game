package tp.pr3;


public class Interpreter {
	
	
	public static Instruction generateInstruction (String line) {
		String[] comando = line.split(" ");
		Instruction instruction;
		if(comando[0].equalsIgnoreCase("TURN")){
			if (comando.length == 2){
				if (comando[1].equalsIgnoreCase("RIGHT")){
					instruction = new Instruction(Action.TURN,Rotation.RIGHT );
				}
				else if (comando[1].equalsIgnoreCase("LEFT")){
					instruction = new Instruction(Action.TURN,Rotation.LEFT );
				}
				else{
					instruction = new Instruction(Action.TURN, Rotation.UNKNOWN);
				}
			}else instruction = new Instruction(Action.TURN, Rotation.UNKNOWN);
		}
		else if(comando[0].equalsIgnoreCase("QUIT")){
			instruction = new Instruction(Action.QUIT);
		}
		else if(comando[0].equalsIgnoreCase("MOVE")  && comando.length == 1) {
			instruction = new Instruction(Action.MOVE);
		}
		else if(comando[0].equalsIgnoreCase("HELP")) {
			instruction = new Instruction(Action.HELP);
		}
		else if(comando[0].equalsIgnoreCase("SCAN")) {
			if (comando.length > 1)
				instruction = new Instruction(Action.SCAN, comando[1]);
			else instruction = new Instruction(Action.SCAN);
		}
		else if((comando[0].equalsIgnoreCase("OPERATE")) && (comando.length > 1)) {
			instruction = new Instruction(Action.OPERATE, comando[1]);
		}
		else if((comando[0].equalsIgnoreCase("PICK")) && (comando.length > 1)){
			instruction = new Instruction(Action.PICK, comando[1]);
			}
		else instruction = new Instruction();
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
