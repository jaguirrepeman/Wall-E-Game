/*package tp.pr5.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.pr5.RobotEngine;
import tp.pr5.items.Item;

@SuppressWarnings("serial")
public class Table extends AbstractTableModel {

	
	public Table (String[] colNames){
		this.columnNames = new String[colNames.length];
		for (int i=0; i< colNames.length; i++)
			this.columnNames[i] = colNames[i];
		this.data = new String[0][colNames.length];
		this.numElems = 0;
	}
	
	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}
	public void removeRow(int row) {
        //data.
        //data.
        //fireTableRowsDeleted(row, row);
    
	}
	public void changeInventory() {
		int rows = this.getRowCount();
		for (int i = 0; i < rows; i++)
			this.removeRow(0);
		RobotEngine engine;
		//TODO cambiar a los métodos del interfaz
		for (int i = 0; i < engine.numberOfItems(); i++)
			this.addRow(engine.getItemsFromContainer(i) objeto del array );

	}
	
	private void addRow(Item item) {
		// TODO Auto-generated method stub
		if(getRowCount() == numElems)
			ampliaTabla();
		setValueAt(item.getId(), numElems, 0);
		setValueAt(item.getDescription(), numElems, 1);
		numElems++;
	}

	public void change(List<Item> inventory){
		int rows = this.getRowCount();
		for (int i = 0; i < rows; i++)
			this.removeRow(0);
		//TODO cambiar a los métodos del interfaz
		for (int i = 0; i < inventory.size(); i++)
			this.addRow(inventory.get(i) objeto del array );
	}
	private String[] columnNames;
	private String[][] data;
	private int numElems;

	
	

}
*/