package n1k.spring_project.validator;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import n1k.spring_project.model.User;

import javax.validation.ConstraintViolation;

@Service
public class UserValidator implements Validator {
	private final javax.validation.Validator validator;

	public UserValidator(
			javax.validation.Validator validator
	) {
		this.validator = validator;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}//close supports

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		for (ConstraintViolation<User> err : validator.validate(user)) {
			errors.rejectValue(
					err.getPropertyPath().toString(),
					"",
					err.getMessage());
		}
	}//close validate

}
