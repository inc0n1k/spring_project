package n1k.spring_project.controller;

import n1k.spring_project.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import n1k.spring_project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	//***Constructor********************************

	private final UserService userService;
	private final OrderService orderService;

	public UserController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	//***Get & Post**********************************

	//work
	@GetMapping(path = "")
	public String authUserProfile(
			Model model,
			Principal principal,
			HttpServletRequest httpServletRequest
	) {
		if (principal != null) {
			model.addAttribute("back", httpServletRequest.getHeader("referer").replace("http://127.0.0.1:8080", ""));
			model.addAttribute("user", userService.getAuthUser(principal));
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			return "userprofile";
		} else {
			return "redirect:/homepage";
		}
	}//close authUserProfile

	//work
	@GetMapping(path = "/{id}")
	public String userProfile(
			Model model,
			Principal principal,
			HttpServletRequest httpServletRequest,
			@PathVariable(required = false) Long id
	) {
		if (
				id != null &&
						principal != null &&
						!userService.getAuthUser(principal).getRole().getName().equals("user") &&
						userService.getUser(id) != null
		) {
			if (httpServletRequest.getHeader("referer") != null) {
				model.addAttribute("back", httpServletRequest.getHeader("referer").replace("http://127.0.0.1:8080", ""));
			}
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			model.addAttribute("user", userService.getUser(id));
			return "userprofile";
		} else {
			return "redirect:/homepage";
		}
	}//close userProfile

	@GetMapping(path = "/orders")
	public String getAuthUserOrders(
			Model model,
			Principal principal
	) {
		if (principal != null) {
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			model.addAttribute("orders", orderService.getOrdersByUser(userService.getAuthUser(principal)));
			return "orders";
		}
		return "redirect:/homepage";
	}

	/*@GetMapping(path = "/{id}/orders")
	public String getOrdersByUserId(
			Model model,
			Principal principal,
			@PathVariable(required = false) String id
	) {
		if (id == null || id.isBlank() || !id.matches("\\d+")) return "redirect:/homepage";
		if (
				principal != null
						&&
						(userService.getAuthUser(principal).getId().equals(Long.parseLong(id.strip()))
								|| !userService.getAuthUser(principal).getRole().getName().equals("user"))
		) {
			model.addAttribute("orders", orderService.getOrdersByUser(userService.getUser(Long.parseLong(id.strip()))));
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			return "orders";
		}
		return "redirect:/homepage";
	}*/

}//close class UserController
