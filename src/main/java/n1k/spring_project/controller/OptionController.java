package n1k.spring_project.controller;

import n1k.spring_project.model.Category;
import n1k.spring_project.model.Option;
import n1k.spring_project.service.CategoryService;
import n1k.spring_project.service.OptionService;
import n1k.spring_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "")
public class OptionController {

	private final CategoryService categoryService;
	private final OptionService optionService;
	private final UserService userService;

	public OptionController(
			CategoryService categoryService,
			OptionService optionService, UserService userService
	) {
		this.categoryService = categoryService;
		this.optionService = optionService;
		this.userService = userService;
	}

	//***Add options***begin***

	@GetMapping(path = "/addoptions")
	public String getOptionsForCategory(
			Model model,
			Principal principal,
			@RequestParam Long id
	) {
		Category category = categoryService.getCategory(id);
		if (category != null) {
			model.addAttribute("auth_user", userService.getAuthUser(principal));
			model.addAttribute("category", category);
			return "addoptions";
		}
		return "redirect:select?act=addo";
	}

	@PostMapping(path = "addoptions")
	public String saveNewOptions(
			@RequestParam Long id,
			@RequestParam List<String> options
	) {
		Category category = categoryService.getCategory(id);
		Option option;
		for (String str : options) {
			option = new Option(category, str);
			optionService.saveOptionNow(option);
		}
		return "return:/homepage";
	}

	//***Add options***end***

}//close OptionController
