package n1k.spring_project.sup;

public class FullCartCookie {

	//Fields
	private String count;

	private String cart;

	//Constructors
	public FullCartCookie() {
	}

	public FullCartCookie(String count, String cart) {
		this.count = count == null ? "" : count;
		this.cart = cart == null ? "" : cart;
	}

	//Getters & Setters
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

}//close class FullCartCookie
