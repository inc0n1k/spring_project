package n1k.spring_project.controller;

import n1k.spring_project.json.CartItemJSON;
import n1k.spring_project.model.Product;
import n1k.spring_project.service.CartService;
import n1k.spring_project.service.ProductService;
import n1k.spring_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

	private final UserService userService;
	private final CartService cartService;
	private final ProductService productService;

	public CartController(
			UserService userService,
			CartService cartService,
			ProductService productService
	) {
		this.userService = userService;
		this.cartService = cartService;
		this.productService = productService;
	}

	@GetMapping(path = "")
	public String getCartPage(
			Model model,
			Principal principal,
			@CookieValue(required = false) String cart,
			@CookieValue(required = false) String count
	) {
		if (cart == null || count == null) {
			return "redirect:/homepage";
		}
		List<CartItemJSON> cartItemJSONList = cartService.getCartItemList(cart);
		List<Product> list = productService.getListOfProductFromListOfCartItemJSON(cartItemJSONList);
		model.addAttribute("auth_user", userService.getAuthUser(principal));
		model.addAttribute("products", list);
		return "cart";
	}

}//close class CartController
