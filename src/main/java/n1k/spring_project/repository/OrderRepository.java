package n1k.spring_project.repository;

import n1k.spring_project.model.Order;
import n1k.spring_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findOrderByUser(User user);

	Order findOrderById(Long id);

	List<Order> findAllByActiveTrue();


}
