package n1k.spring_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "options")
public class Option {

	//***Fields**************************

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@NotNull(message = "Is Null")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonProperty
	@NotNull(message = "Is Null")
	private String name;

	//***Related fields******************

	@JsonIgnore
	@OneToMany(mappedBy = "option")
	private List<Value> values;

	//***Constructors********************

	public Option() {
	}

	public Option
			(
					@NotNull(message = "Is Null") Category category,
					@NotNull(message = "Is Null") String name
			) {
		this.category = category;
		this.name = name;
	}

	//***Getters and Setters**************

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

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

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

}//close class Option
