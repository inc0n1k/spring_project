package n1k.spring_project.json;

public class CartItemJSON {

	//Fields
	private Long id;

	private Integer count;

	//Constructors
	public CartItemJSON() {
		//
	}

	public CartItemJSON(Long id, Integer count) {
		this.id = id;
		this.count = count;
	}

	//Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}//close  class CartItemJSON
