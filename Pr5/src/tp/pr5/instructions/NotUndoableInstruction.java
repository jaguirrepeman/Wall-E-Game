package tp.pr5.instructions;

public abstract class NotUndoableInstruction implements Instruction {

	
	@Override
	public boolean isUndoableInstruction() {
		return false;
	}
	
	public void undo(){
		
	}

}
