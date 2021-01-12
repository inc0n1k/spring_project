package n1k.spring_project.repository;

import n1k.spring_project.model.Category;
import n1k.spring_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findProductById(long id);

	List<Product> findProductByCategory(Category category);

	Product findFirstByOrderByPriceDesc();

	Product findFirstByOrderByPriceAsc();

	//Filter
	List<Product> findAllByPriceBetweenOrderByPrice(
			@NotNull(message = "Is Null") Integer minPrice,
			@NotNull(message = "Is Null") Integer maxPrice
	);

	List<Product> findAllByNameContainsAndPriceBetweenOrderByPrice(
			@NotNull(message = "Is Null") String name,
			@NotNull(message = "Is Null") Integer minPrice,
			@NotNull(message = "Is Null") Integer maxPrice
	);

	List<Product>findAllByCategoryAndPriceBetweenOrderByPrice(
			@NotNull(message = "Is Null") Category category,
			@NotNull(message = "Is Null") Integer minPrice,
			@NotNull(message = "Is Null") Integer maxPrice
	);

	List<Product> findAllByNameContainsAndCategoryAndPriceBetweenOrderByPrice(
			@NotNull(message = "Is Null") String name,
			@NotNull(message = "Is Null") Category category,
			@NotNull(message = "Is Null") Integer minPrice,
			@NotNull(message = "Is Null") Integer maxPrice
	);

}//close ProductRepository
