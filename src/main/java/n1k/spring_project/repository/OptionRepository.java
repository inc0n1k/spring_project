package n1k.spring_project.repository;

import n1k.spring_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import n1k.spring_project.model.Option;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

	Option findOptionsById(long id);

	List<Option> findOptionsByCategory(Category category);
}
