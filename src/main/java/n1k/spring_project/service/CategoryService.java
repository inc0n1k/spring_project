package n1k.spring_project.service;

import n1k.spring_project.json.CategoryJSON;
import n1k.spring_project.model.Category;
import n1k.spring_project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

	//***Constructor********************************

	private final CategoryRepository categoryRepository;

	public CategoryService(
			CategoryRepository categoryRepository
	) {
		this.categoryRepository = categoryRepository;
	}

	//**********************************************

	public Category getCategory(long id) {
		return categoryRepository.findCategoryById(id);
	}

	//work
	public List<Category> getNextCategoriesByCategoryId(long id) {
		if (id == 0) {
			return categoryRepository.findCategoriesByLevel(1);
		} else {
			Category category = categoryRepository.findCategoryById(id);
			return categoryRepository.getCategoryLVL(
					category.getLevel() + 1,
					category.getLeftKey(),
					category.getRightKey()
			);
		}
	}//close getNextCategoriesByCategoryId

	//work
	public List<Category> getFirstLevelCategories() {
		return categoryRepository.findCategoriesByLevel(1);
	}

	//work
	public List<CategoryJSON> getListCategoryJSONFromListCategory(List<Category> categories) {
		List<CategoryJSON> categoryJSONList = new ArrayList<>();
		for (Category category : categories) {
			categoryJSONList.add(new CategoryJSON(category));
		}
		return categoryJSONList;
	}//close getListCategoryJSONFromListCategory

	//work
	public List<Category> getLastPointOfEachBranch() {
		List<Category> categoryList = categoryRepository.getAllByOrderByLeftKey();
		List<Category> LastPointOfEachBranch = new ArrayList<>();
		for (int i = 0; i < categoryList.size() - 1; i++) {
			//***
			if (categoryList.get(i).getLevel() > categoryList.get(i + 1).getLevel()) {
				LastPointOfEachBranch.add(categoryList.get(i));
				if ((i + 2) == categoryList.size()) {
					LastPointOfEachBranch.add(categoryList.get(i + 1));
				}
			}
			//***
			if (categoryList.get(i).getLevel() == categoryList.get(i + 1).getLevel()) {
				LastPointOfEachBranch.add(categoryList.get(i));
				if ((i + 2) == categoryList.size()) {
					LastPointOfEachBranch.add(categoryList.get(i + 1));
				}
			}
			//***
			if ((categoryList.get(i).getLevel() < categoryList.get(i + 1).getLevel()) && ((i + 2) == categoryList.size())) {
				LastPointOfEachBranch.add(categoryList.get(i + 1));
			}
		}
		return LastPointOfEachBranch;
	}

}//close CategoryService
