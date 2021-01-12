package n1k.spring_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "values")
public class Value {

	//***Fields**************************

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@NotNull(message = "Is Null")
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@JsonProperty
	@NotNull(message = "Is Null")
	@ManyToOne
	@JoinColumn(name = "option_id")
	private Option option;

	@JsonProperty
	@NotNull(message = "Is Null")
	private String value;

	//***Constructors********************

	public Value() {
	}

	public Value
			(
					@NotNull(message = "Is Null") Product product,
					@NotNull(message = "Is Null") Option option,
					@NotNull(message = "Is Null") String value
			) {
		this.product = product;
		this.option = option;
		this.value = value;
	}

	//***Getters and Setters**************

	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}//close class Value
