package n1k.spring_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import n1k.spring_project.model.Option;
import n1k.spring_project.model.Value;
import n1k.spring_project.service.*;
import n1k.spring_project.sup.FilterClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/filter")
public class FilterController {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final UserService userService;
	private final ValueService valueService;
	private final FilterService filterService;

	public FilterController(
			ProductService productService,
			CategoryService categoryService,
			UserService userService,
			ValueService valueService,
			FilterService filterService
	) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.userService = userService;
		this.valueService = valueService;
		this.filterService = filterService;
	}

	//work
	@PostMapping(path = "/getvalues")
	@ResponseBody
	public String getValues(
			@RequestParam Long id
	) throws JsonProcessingException {
		Map<String, Map<String, Long>> map = new HashMap<>();
		for (Option option : categoryService.getCategory(id).getOptions()) {
			Map<String, Long> new_map = new HashMap<>();
			List<Value> tList = valueService.getUniqueValuesByOption(option);
			if (tList.size() == 0) continue;
			for (Value value : valueService.getUniqueValuesByOption(option)) {
				new_map.put(value.getValue(), value.getOption().getId());
			}
			map.put(option.getName(), new_map);
		}
		return new ObjectMapper().writeValueAsString(map);
	}

	//work
	@PostMapping(path = "")
	@ResponseBody
	public String filterPost(
			@RequestParam(required = false) String arg
	) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		FilterClass[] filterClassList = om.readerFor(FilterClass[].class).readValue(arg);
//		return om.writeValueAsString(productService.filter(filterService.fromFilterClassListToParametersMap(filterClassList)));
		return om.writeValueAsString(productService.filter(filterClassList));
	}//close filterPost

	//work
	@GetMapping(path = "")
	public String filterGet(
			Model model,
			Principal principal
	) {
		model.addAttribute("auth_user", userService.getAuthUser(principal));
		model.addAttribute("categories", categoryService.getLastPointOfEachBranch());
		return "filter";
	}//close filterGet

}//close FilterController