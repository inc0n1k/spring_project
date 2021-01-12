package n1k.spring_project.repository;

import n1k.spring_project.model.Option;
import n1k.spring_project.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {

    Value findValueById(long id);

//    @Query(value = "select v.value from Value v where v.option = ?1 group by v.value")
//    List<String> getUniqueValuesByOption(Option option);

    @Query(value = "select v from Value v where v.option = ?1 group by v.value")
    List<Value> getUniqueValuesByOption(Option option);

}
