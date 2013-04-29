package tp.pr5.items;

public interface InventoryObserver {
	
	void inventoryChange(java.util.List<Item> inventory);
	
	void inventoryScanned(java.lang.String inventoryDescription);
	
	void itemScanned(java.lang.String description);
	
	void itemEmpty(java.lang.String itemName);

}
