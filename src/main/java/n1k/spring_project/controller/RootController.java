package n1k.spring_project.controller;

import n1k.spring_project.json.CategoryJSON;
import n1k.spring_project.model.User;
import n1k.spring_project.service.*;
import n1k.spring_project.sup.FullCartCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "")
public class RootController {

	//***Constructor********************************

	private final CartService cartService;
	private final UserService userService;
	private final OrderService orderService;
	private final CookieService cookieService;
	private final CategoryService categoryService;

	public RootController(
			CartService cartService,
			UserService userService,
			OrderService orderService,
			CookieService cookieService,
			CategoryService categoryService
	) {
		this.cartService = cartService;
		this.userService = userService;
		this.orderService = orderService;
		this.cookieService = cookieService;
		this.categoryService = categoryService;
	}

	//***Get & Post**********************************

	@GetMapping(path = "/homepage")
	public String toHomePage(
			Model model,
			Principal principal
	) {
		User user = principal != null ? (User) userService.loadUserByUsername(principal.getName()) : null;
		model.addAttribute("auth_user", user);
		return "homepage";
	}//close Get toHomePage

	@GetMapping(path = "/exit")
	public String exit() {
		return "redirect:logout";
	}//close Get exit

	@GetMapping(path = "/select")
	public String selectAction(
			Model model,
			Principal principal,
			@RequestParam(required = false) String act
	) {
		if (act != null) {
			model.addAttribute("act", act);
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			model.addAttribute("categories", categoryService.getFirstLevelCategories());
			if (act.equals("buy")) {
				return "select";
			}
			if (
					(act.equals("edit") || act.equals("addo") || act.equals("addp"))
							&&
							(principal != null && !userService.getAuthUser(principal).getRole().getName().equals("user"))
			) {
				return "select";
			}
		}
		return "redirect:/";
	}//close Get selectAction

	@PostMapping(path = "select")
	@ResponseBody
	public List<CategoryJSON> getNextCategoryList(@RequestParam Long id) {
		return categoryService.getListCategoryJSONFromListCategory(
				categoryService.getNextCategoriesByCategoryId(id)
		);
	}//close Post getNextCategoryList

	@PostMapping(path = "updatecart")
	public void updateCart(
			@CookieValue(name = "cart", required = false) String cookie_cart,
			@CookieValue(name = "count", required = false) String cookie_count,
			@RequestParam Long id,
			@RequestParam Integer count,
			HttpServletResponse httpServletResponse
	) {
		if (cookie_cart != null || cookie_count != null || id != null || count != null) {
			FullCartCookie fullCartCookie = new FullCartCookie(cookie_count, cookie_cart);
			FullCartCookie fullCartCookieResult = cartService.updateCart(fullCartCookie, id, count);
			httpServletResponse.addCookie(
					cookieService.createCookie(
							"cart",
							fullCartCookieResult.getCart(),
							5 * 60
					)
			);
			httpServletResponse.addCookie(
					cookieService.createCookie(
							"count",
							fullCartCookieResult.getCount(),
							5 * 60
					)
			);
		}
	}//close updateCart

	@PostMapping(path = "removefromcart")
	public void removeFromCart(
			@CookieValue(name = "cart", required = false) String cookie_cart,
			@CookieValue(name = "count", required = false) String cookie_count,
			@RequestParam Long id,
			HttpServletResponse httpServletResponse
	) {
		FullCartCookie fullCartCookie = new FullCartCookie(cookie_count, cookie_cart);
		FullCartCookie fullCartCookieResult = cartService.removeFromCart(fullCartCookie, id);
		if (fullCartCookieResult == null) {
			httpServletResponse.addCookie(cookieService.createNullCookie("count"));
			httpServletResponse.addCookie(cookieService.createNullCookie("cart"));

		} else {
			httpServletResponse.addCookie(cookieService.createCookie(
					"cart",
					fullCartCookieResult.getCart(),
					5 * 60)
			);
			httpServletResponse.addCookie(cookieService.createCookie(
					"count",
					fullCartCookieResult.getCount(),
					5 * 60)
			);
		}
	}//close removeFromCart

	@PostMapping(path = "/processing")

	public String toEditProduct(
			@RequestParam(required = false) String act,
			@RequestParam(required = false) Long select_category
	) {
		if (act == null || select_category == null) return "redirect:/";
		return switch (act) {
			case "buy" -> "redirect:buy?id=" + select_category;
			case "edit" -> "redirect:edit?id=" + select_category;
			case "addp" -> "redirect:addproduct?id=" + select_category;
			case "addo" -> "redirect:addoptions?id=" + select_category;
			default -> "redirect/";
		};
	}//close toEditProduct

	@PostMapping(path = "createorder")
	public String createOrder(
			Principal principal,
			HttpServletResponse httpServletResponse,
			@CookieValue(required = false) String cart,
			@RequestParam String comment
	) {
		if (cart == null) return "redirect:homepage";
		orderService.createAndSaveFullOrder(principal, cart, comment);
		httpServletResponse.addCookie(cookieService.createNullCookie("cart"));
		httpServletResponse.addCookie(cookieService.createNullCookie("count"));
		return "redirect:/homepage";
	}
}//close class RootController

 /*
		ObjectMapper om = new ObjectMapper();
        UserJSON uu = new UserJSON(userService.getUser(2L));
        System.out.println(om.writeValueAsString(uu));
        UserJSON user =  om.readerFor(UserJSON[].class).readValue(om.writeValueAsString(uu));
        System.out.println (om.readerFor(UserJSON.class).readValue(om.writeValueAsString(uu)).toString() );
        System.out.println(user.getLogin());
*/