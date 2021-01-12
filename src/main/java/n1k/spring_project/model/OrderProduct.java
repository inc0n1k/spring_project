package n1k.spring_project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_products")
public class OrderProduct {

	//***Fields**************************

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Is Null")
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@NotNull(message = "Is Null")
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@NotNull(message = "Is Null")
	private Integer count;

	//***Constructors********************

	public OrderProduct() {
	}

	public OrderProduct
			(
					@NotNull(message = "Is Null") Order order,
					@NotNull(message = "Is Null") Product product,
					@NotNull(message = "Is Null") Integer count
			) {
		this.order = order;
		this.product = product;
		this.count = count;
	}

	//***Getters and Setters**************

	public Long getId() {
		return id;
	}

//    public void setId(Long id) {
//        this.id = id;
//    }

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
