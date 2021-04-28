package shop_product;



public class Product {
	protected int id;
	protected String product_name;
	protected int category;
	protected int available;
	protected double price;
	protected String image;
	protected String specifications;
	protected String seller;
	protected String color;
	protected String brand;
	
	
	public Product(int id, String product_name, int category, int available, double price, String image,
			 String specifications, String seller,String color,String brand) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.category = category;
		this.available = available;
		this.price = price;
		this.image = image;
		this.specifications = specifications;
		this.seller = seller;
		this.color = color;
		this.brand = brand;
	}
	
	
	public Product(int id,String product_name, int category, int available, double price, String image) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.category = category;
		this.available = available;
		this.price = price;
		this.image = image;
	}


	public Product(String product_name, int category, int available, double price, String image,
			String specifications, String seller,String color,String brand) {
		super();
		this.product_name = product_name;
		this.category = category;
		this.available = available;
		this.price = price;
		this.image = image;
		
		this.specifications = specifications;
		this.seller = seller;
		this.color = color;
		this.brand = brand;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
