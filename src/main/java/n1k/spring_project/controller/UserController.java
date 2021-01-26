package n1k.spring_project.controller;

import n1k.spring_project.model.User;
import n1k.spring_project.service.OrderService;
import n1k.spring_project.service.UserService;
import n1k.spring_project.sup.UserSaveMethod;
import n1k.spring_project.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping(path = "")
public class UserController {

	//***Constructor********************************

	private final UserService userService;
	private final OrderService orderService;
	private final UserValidator userValidator;

	public UserController(
			UserService userService,
			OrderService orderService,
			UserValidator userValidator
	) {
		this.userService = userService;
		this.orderService = orderService;
		this.userValidator = userValidator;
	}

	//***Get & Post**********************************

	@GetMapping(path = "/registration")
	public String getRegistrationPage(
			Principal principal
	) {
		if (principal != null) {
			return "redirect:/homepage";
		}
		return "registration";
	}//close getRegistrationPage

	@PostMapping(path = "registration")
	public String registration(
			@RequestParam Map<String, String> map,
			HttpServletRequest httpServletRequest
	) {
		if (userService.getUser(map.get("login")) != null) {
			httpServletRequest.setAttribute("errlog", "Login exist...");
		} else {
			if (map.get("password").equals(map.get("password_confirm"))) {
				map.remove("password_confirm");
				User user = userService.createUserFromMap(map);
				DataBinder dataBinder = new DataBinder(user);
				dataBinder.addValidators(userValidator);
				dataBinder.validate(user);
				if (dataBinder.getBindingResult().getFieldErrorCount() == 0) {
					userService.saveUserNow(user, UserSaveMethod.NEW);
					return "redirect:login";
				}
			}
		}
		return "registration";
	}//close registration

	//work
	@GetMapping(path = "/user")
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
	@GetMapping(path = "/user/{id}")
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

	@GetMapping(path = "/user/orders")
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
