package tp.pr4;

import java.io.IOException;
import java.io.OutputStream;

public class FilterOutputStream extends OutputStream {

	 
	  public FilterOutputStream(OutputStream out, boolean isWriting){
		  this.out = out; 
		  this.isWriting = isWriting;
	  }
	 @Override 
	 public void write(int b)throws IOException{
	 if (isWriting) out.write(b);
	 
	 
	 }
	 private boolean isWriting;
	 private OutputStream out;
	 

}
