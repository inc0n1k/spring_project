package n1k.spring_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

	//************************************************
	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@NotNull
	@Column(name = "left")
	private Integer leftKey;

	@JsonIgnore
	@NotNull
	@Column(name = "right")
	private Integer rightKey;

	@JsonIgnore
	@NotNull
	private Integer level;

	@JsonProperty
	@NotNull
	@Column(name = "name")
	private String category;

	//************************************************

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Option> options;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	//************************************************


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(Integer leftKey) {
		this.leftKey = leftKey;
	}

	public Integer getRightKey() {
		return rightKey;
	}

	public void setRightKey(Integer rightKey) {
		this.rightKey = rightKey;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}//close class Category
