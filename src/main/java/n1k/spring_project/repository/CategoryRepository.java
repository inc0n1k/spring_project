package n1k.spring_project.repository;

import n1k.spring_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findCategoryById(long id);

	List<Category> findCategoriesByLevel(int level);

	@Query(value = "select c from  Category c where c.level = ?1 and c.leftKey > ?2 and c.rightKey < ?3")
	List<Category> getCategoryLVL(int level, int lkey, int rkey);

	List<Category> getAllByOrderByLeftKey();

}//close CategoryRepository
