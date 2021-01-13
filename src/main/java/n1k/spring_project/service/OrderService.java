package n1k.spring_project.service;

import n1k.spring_project.json.CartItemJSON;
import n1k.spring_project.model.Order;
import n1k.spring_project.model.OrderProduct;
import n1k.spring_project.model.User;
import n1k.spring_project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final CartService cartService;
	private final UserService userService;
	private final ProductService productService;
	private final OrderProductService orderProductService;

	public OrderService(
			OrderRepository orderRepository,
			CartService cartService,
			UserService userService,
			ProductService productService,
			OrderProductService orderProductService
	) {
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.userService = userService;
		this.productService = productService;
		this.orderProductService = orderProductService;
	}

	public List<Order> getOrdersByUser(User user) {
		return orderRepository.findOrderByUser(user);
	}

	public Order getOrderById(Long id) {
		return orderRepository.findOrderById(id);
	}

	public List<Order> getAllActiveOrders() {
		return orderRepository.findAllByActiveTrue();
	}

	public void createAndSaveFullOrder(Principal principal, String cookie_cart, String comment) {
		List<CartItemJSON> cartItemJSONList = cartService.getCartItemList(cookie_cart);
		Order order;
		if (principal != null) {
			order = new Order((User) userService.loadUserByUsername(principal.getName()), comment);
		} else {
			order = new Order(comment);
		}
		saveOrderNow(order);
		OrderProduct orderProduct;
		for (CartItemJSON element : cartItemJSONList) {
			orderProduct = new OrderProduct(order, productService.getProduct(element.getId()), element.getCount());
			orderProductService.saveOrderProductNow(orderProduct);
		}
	}

	public void closeOrder(long id, boolean complite, String comment) {
		Order order = getOrderById(id);
		order.setComplited(complite);
		order.setDate_complit(new Date());
		if (comment != null) {
			order.setComment(order.getComment() + " (Don't complited because: " + comment+")");
		}
		order.setActive(false);
		saveOrderNow(order);
	}//close closeOrder

	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	public void saveOrderNow(Order order) {
		orderRepository.saveAndFlush(order);
	}

}//close class OrderService
