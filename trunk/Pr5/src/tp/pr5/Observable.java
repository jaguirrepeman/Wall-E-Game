package tp.pr5;

import java.util.Vector;

public class Observable<T> {
	
	public void addObserver(T t){
		this.observers.add(t);
	}
	
	public void removeObserver (T t){
		this.observers.remove(t);
	}
	
	
	protected Vector<T> observers = new Vector<T>();

}
