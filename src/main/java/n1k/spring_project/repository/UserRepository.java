package n1k.spring_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import n1k.spring_project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByLogin(String login);

	User findById(long id);

}
