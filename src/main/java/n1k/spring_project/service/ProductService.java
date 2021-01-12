package n1k.spring_project.service;

import n1k.spring_project.json.CartItemJSON;
import n1k.spring_project.model.Category;
import n1k.spring_project.model.Option;
import n1k.spring_project.model.Product;
import n1k.spring_project.model.Value;
import n1k.spring_project.repository.ProductRepository;
import n1k.spring_project.sup.FilterClass;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

	//***Constructor********************************

	private final ProductRepository productRepository;
	private final EntityManager entityManager;
	private final FilterService filterService;

	public ProductService(
			ProductRepository productRepository,
			EntityManager entityManager,
			FilterService filterService
	) {
		this.productRepository = productRepository;
		this.entityManager = entityManager;
		this.filterService = filterService;
	}

	//**********************************************

	public Product getProduct(long id) {
		return productRepository.findProductById(id);
	}

	public List<Product> getAllProductsByCategory(Category category) {
		return productRepository.findProductByCategory(category);
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void saveProductNow(Product product) {
		productRepository.saveAndFlush(product);
	}

	public List<Product> getListOfProductFromListOfCartItemJSON(List<CartItemJSON> cartItemJSONList) {
		List<Product> list = new ArrayList<>();
		for (CartItemJSON element : cartItemJSONList) {
			list.add(productRepository.findProductById(element.getId()));
		}
		return list;
	}

	public Product getMax() {
		return productRepository.findFirstByOrderByPriceDesc();
	}

	public Product getMin() {
		return productRepository.findFirstByOrderByPriceAsc();
	}

	//work
	public List<Product> filter(FilterClass[] filterClasses) {

		Map<String, List<String>> resultMap = filterService.fromFilterClassListToParametersMap(filterClasses);

		int maxPrice = resultMap.get("maxp").get(0).equals("") ?
				getMax().getPrice() : Integer.parseInt(resultMap.get("maxp").get(0));
		resultMap.remove("maxp");
		int minPrice = resultMap.get("minp").get(0).equals("") ?
				getMin().getPrice() : Integer.parseInt(resultMap.get("minp").get(0));
		resultMap.remove("minp");
		Long id = resultMap.get("category").get(0).equals("") ?
				null : Long.parseLong(resultMap.get("category").get(0));
		resultMap.remove("category");
		String name = resultMap.get("name").get(0);
		resultMap.remove("name");

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);

		List<Predicate> predicates = new ArrayList<>();

		if (!name.equals("")) {
			predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		}
		predicates.add(builder.between(root.get("price"), minPrice, maxPrice));
		if (id != null) {
			predicates.add(builder.equal(root.get("category"), entityManager.find(Category.class, id)));
		}
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(root.get("price")));

		Join<Product, Value> join;
		for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
				join = root.join("values", JoinType.INNER);
				predicates = new ArrayList<>();
				for (String line : entry.getValue()) {
					predicates.add(builder.equal(join.get("value"), line));
				}
				join.on(
						builder.equal(join.get("option"), entityManager.find(Option.class, Long.parseLong(entry.getKey()))),
						builder.or(predicates.toArray(new Predicate[0]))
				);
		}
		return entityManager.createQuery(query).getResultList();
	}//close filter

}//close ProductService
