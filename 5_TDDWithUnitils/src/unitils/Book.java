package unitils;

import java.util.Date;

public class Book {

	private String name;
	private String author;
	private int price;
	private Date salesDate;
	
	public Book(String name, String author, int price) {
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public Book(String name, String author, int price, Date salesDate) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.salesDate = salesDate;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public String getAuthor() {
		// TODO Auto-generated method stub
		return this.author;
	}

	public int getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

}
