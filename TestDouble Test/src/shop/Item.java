package shop;

public class Item {
	private int price;
	private String category;
	
	public Item(String itemName, String category, int price) {
		this.price = price;
		this.category = category;
	}

	public int getPrice() {
		return this.price;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}

}
