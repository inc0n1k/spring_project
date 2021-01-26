package n1k.spring_project.service;

import n1k.spring_project.model.User;
import n1k.spring_project.repository.RoleRepository;
import n1k.spring_project.repository.UserRepository;
import n1k.spring_project.sup.UserSaveMethod;
import n1k.spring_project.validator.UserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;

import java.security.Principal;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

	//***Security********************************

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

	//***Constructor********************************

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserValidator userValidator;

	public UserService(
			UserRepository userRepository,
			RoleRepository roleRepository,
			UserValidator userValidator
	) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userValidator = userValidator;
	}

	//**********************************************

	public void validationService() {
		//create user
		User user = new User();
		user.setLogin("_12345678");
		user.setPassword("123");
		user.setName("Erik");

		//validation
		DataBinder dataBinder = new DataBinder(user);
		dataBinder.addValidators(userValidator);
		dataBinder.validate(user);
		for (FieldError err : dataBinder.getBindingResult().getFieldErrors()) {
			System.out.println(err.getField());
			System.out.println(err.getRejectedValue());
			System.out.println(err.getDefaultMessage());
			System.out.println("*************");
		}
	}//close testService

	public User getAuthUser(Principal principal) {
		if (principal != null) {
			return (User) loadUserByUsername(principal.getName());
		} else {
			return null;
		}
	}

	public User getUser(long id) {
		return userRepository.findById(id);
	}

	public User getUser(String login) {
		return userRepository.findUserByLogin(login);
	}

	public User createUserFromMap(Map<String, String> map) {
		return new User(
				map.get("login"),
				map.get("password"),
				map.get("name"),
				map.get("surname"),
				roleRepository.getRoleByName("user")
		);
	}

	public void saveUser(User user, UserSaveMethod userSaveMethod) {
		if (userSaveMethod == UserSaveMethod.NEW) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		userRepository.save(user);
	}

	public void saveUserNow(User user, UserSaveMethod userSaveMethod) {
		if (userSaveMethod == UserSaveMethod.NEW) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		userRepository.saveAndFlush(user);
	}

}//close class UserService
