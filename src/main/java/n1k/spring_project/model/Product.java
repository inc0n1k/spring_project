package n1k.spring_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

	//***Fields**************************

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@NotNull(message = "Is Null")
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonProperty
	@NotNull(message = "Is Null")
	private String name;

	@JsonProperty
	@NotNull(message = "Is Null")
	private Integer price;

	//***Related fields******************

	@JsonProperty
	@OneToMany(mappedBy = "product")
	private List<Value> values;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderProduct> orderProducts;

	//***Constructors********************

	public Product() {
	}

	public Product
			(
					@NotNull(message = "Is Null") Category category,
					@NotNull(message = "Is Null") String name,
					@NotNull(message = "Is Null") Integer price
			) {
		this.category = category;
		this.name = name;
		this.price = price;
	}

	//***Getters and Setters**************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

}//close class Product
