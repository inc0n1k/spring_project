package n1k.spring_project.controller;

import n1k.spring_project.model.Order;
import n1k.spring_project.service.OrderService;
import n1k.spring_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

	private final OrderService orderService;
	private final UserService userService;

	public OrderController(
			OrderService orderService,
			UserService userService
	) {
		this.orderService = orderService;
		this.userService = userService;
	}

	@GetMapping(path = "")
	public String goToHomepage() {
		return "redirect:/homepage";
	}

	@GetMapping(path = "/{id}")
	public String getOrder(
			Model model,
			Principal principal,
			HttpServletRequest httpServletRequest,
			@PathVariable(required = false) String id
	) {
		if (id == null || id.isBlank() || (!id.matches("\\d+") && !id.equals("all"))) return "redirect:/homepage";
		if (id.equals("all")) {
			model.addAttribute("orders", orderService.getAllActiveOrders());
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			return "orders";
		}
		Order order = orderService.getOrderById(Long.parseLong(id.trim()));
		if (order != null) {
			if (httpServletRequest.getHeader("referer") != null) {
				model.addAttribute("back", httpServletRequest.getHeader("referer").replace("http://127.0.0.1:8080", ""));
			}
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			model.addAttribute("order", order);
			return "order";
		}
		return "redirect:/homepage";
	}
}