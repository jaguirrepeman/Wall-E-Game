package tp.pr4.instructions;


public abstract class UndoableInstruction implements Instruction {
	
	public abstract void undo();
	
	public boolean isUndoableInstruction(){
		return true;
	}
	
}
