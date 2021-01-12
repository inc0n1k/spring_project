package n1k.spring_project.controller;

import n1k.spring_project.model.Category;
import n1k.spring_project.model.Product;
import n1k.spring_project.model.Value;
import n1k.spring_project.service.*;
import n1k.spring_project.sup.FullCartCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final CookieService cookieService;
    private final OptionService optionService;
    private final ValueService valueService;
    private final CartService cartService;
    private final UserService userService;

    public ProductController(
            CategoryService categoryService,
            ProductService productService,
            CookieService cookieService,
            OptionService optionService,
            ValueService valueService,
            CartService cartService,
            UserService userService
    ) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.cookieService = cookieService;
        this.optionService = optionService;
        this.valueService = valueService;
        this.cartService = cartService;
        this.userService = userService;
    }

    //***buy product***begin***

    @GetMapping(path = "/buy")
    public String buyProduct(
            Model model,
            Principal principal,
            @RequestParam(required = false) String id,
            HttpServletRequest httpServletRequest
    ) {
        if (!id.trim().matches("\\d+")) return "redirect:homepage";
        Category category = categoryService.getCategory(Long.parseLong(id.trim()));
        List<Product> products = productService.getAllProductsByCategory(category);
        if (category == null || products.size() == 0) return "redirect:homepage";
        model.addAttribute("auth_user", userService.getAuthUser(principal));
        model.addAttribute("products", products);
        return "buy";
    }

    @PostMapping(path = "addtocart")
    @ResponseBody
    public String addToCart(
            @CookieValue(name = "cart", required = false) String cart,
            @CookieValue(name = "count", required = false) String count,
            @RequestParam Long id,
            HttpServletResponse httpServletResponse
    ) {
        FullCartCookie fullCartCookie = new FullCartCookie(count, cart);
        FullCartCookie fullCartCookieResult = cartService.addToCart(fullCartCookie, id);
        httpServletResponse.addCookie(cookieService.createCookie(
                "cart",
                fullCartCookieResult.getCart(),
                5 * 60));
        httpServletResponse.addCookie(cookieService.createCookie(
                "count",
                fullCartCookieResult.getCount(),
                5 * 60));
        return "";
    }

    //***buy product***end***
    //***edit products***begin***

    @GetMapping(path = "/edit")
    public String toEditProductsPage(
            Model model,
            Principal principal,
            @RequestParam(required = false) String id
    ) {
        if(principal == null || userService.getAuthUser(principal).getRole().getName().equals("user")) return "redirect:homepage";
        if (!id.trim().matches("\\d+")) return "redirect:homepage";
        Category category = categoryService.getCategory(Long.parseLong(id.trim()));
        List<Product> products = productService.getAllProductsByCategory(category);
        if (category == null || products.size() == 0) return "redirect:homepage";
        model.addAttribute("auth_user", userService.getAuthUser(principal));
        model.addAttribute("products", products);
        return "edit";
    }

    @PostMapping(path = "edit")
    public String saveEditProduct(
            @RequestParam Map<String, String> allParameters
    ) {
        Product product = productService.getProduct(Long.parseLong(allParameters.get("id")));
        if (!allParameters.get("name").isBlank()) product.setName(allParameters.get("name").trim());
        if (!allParameters.get("price").isBlank())
            product.setPrice(Integer.parseInt(allParameters.get("price").trim()));
        productService.saveProductNow(product);
        for (Map.Entry<String, String> entry : allParameters.entrySet()) {
            if (!entry.getKey().equals("id") && !entry.getKey().equals("name") && !entry.getKey().equals("price")) {
                if (entry.getKey().matches("\\d+_val") && !entry.getValue().isBlank()) {
                    Value value = valueService.getValue(Long.parseLong(entry.getKey().trim().replace("_val", "")));
                    value.setValue(entry.getValue());
                    valueService.saveValueNow(value);
                }
            }
        }
        return "redirect:edit?id=" + product.getCategory().getId();
    }

    @PostMapping(path = "getproduct")
    @ResponseBody
    public Product getProduct(@RequestParam Long id) {
        return productService.getProduct(id);
    }

    //***edit products***end***
    //***add product***begin***

    @GetMapping(path = "/addproduct")
    public String toAddProductPage(
            Model model,
            Principal principal,
            @RequestParam(required = false) String id
    ) {
        if (principal == null || !id.trim().matches("\\d+")) return "redirect:homepage";
        Category category = categoryService.getCategory(Long.parseLong(id.trim()));
        if (category == null) return "redirect:homepage";
        model.addAttribute("auth_user", userService.getAuthUser(principal));
        model.addAttribute("category", category);
        return "addproduct";
    }

    @PostMapping(path = "addproduct")
    public String saveProduct(
            @RequestParam Map<String, String> allParameters
    ) {
        Category category = categoryService.getCategory(Long.parseLong(allParameters.get("id")));
        Product new_product = new Product(
                category,
                allParameters.get("name"),
                Integer.parseInt(allParameters.get("price"))
        );
        productService.saveProductNow(new_product);
        Value new_value;
        for (Map.Entry<String, String> entry : allParameters.entrySet()) {
            if (entry.getKey().matches("\\d+_opt")) {
                new_value = new Value(
                        new_product,
                        optionService.getOption(Long.parseLong(entry.getKey().replace("_opt", ""))),
                        entry.getValue()
                );
                valueService.saveValueNow(new_value);
            }
        }
        return "redirect:/homepage";
    }

    //***add product***end***

}//close class ProductController
