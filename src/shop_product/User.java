package shop_product;

public class User {
	private int id;
	private String username;
	private String email;
	private Long mobile_number;
	private String address1;
	private String address2;
	private String image;
	private String password;
	
	
	public User(String username, String email, Long mobile_number, String address1, String address2, String image) {
		super();
		this.username = username;
		this.email = email;
		this.mobile_number = mobile_number;
		this.address1 = address1;
		this.address2 = address2;
		this.image = image;
		
	}
	public User(int id, String username, String email, Long mobile_number, String address1, String address2,
			String image, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.mobile_number = mobile_number;
		this.address1 = address1;
		this.address2 = address2;
		this.image = image;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
