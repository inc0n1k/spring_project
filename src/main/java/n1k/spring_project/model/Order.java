package n1k.spring_project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

	//***Fields**************************

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull(message = "Not null")
	private Date order_date;

	@NotNull(message = "Not null")
	private Boolean active;

	private Date date_complit;

	@NotNull(message = "Not null")
	private Boolean complited;

	private String comment;

	//***Related fields******************

	@OneToMany(mappedBy = "order")
	private List<OrderProduct> orderProducts;

	//***Constructors********************

	public Order() {
		this.order_date = new Date();
		this.active = true;
		this.complited = false;
	}

	public Order(User user) {
		this();
		this.user = user;
	}

	public Order(String comment) {
		this();
		this.comment = comment;
	}

	public Order(User user, String comment) {
		this();
		this.user = user;
		this.comment = comment;
	}

	//***Getters and Setters**************

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getDate_complit() {
		return date_complit;
	}

	public void setDate_complit(Date date_complit) {
		this.date_complit = date_complit;
	}

	public Boolean getComplited() {
		return complited;
	}

	public void setComplited(Boolean complited) {
		this.complited = complited;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

//	public void setOrderProducts(List<OrderProduct> orderProducts) {
//		this.orderProducts = orderProducts;
//	}

}//close class Order
