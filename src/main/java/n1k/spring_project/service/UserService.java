package n1k.spring_project.service;

import n1k.spring_project.model.User;
import n1k.spring_project.repository.UserRepository;
import n1k.spring_project.validator.UserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpSession;
import java.security.Principal;

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

//    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//    static {
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setBasename("message");
//    }

    //***Constructor********************************

    private final UserRepository userRepository;
    private final HttpSession session;
    private final UserValidator userValidator;

    public UserService(
            UserRepository userRepository,
            HttpSession session,
            UserValidator userValidator
    ) {
        this.userRepository = userRepository;
        this.session = session;
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
//        if (session.getAttribute("id") != null) {
//            return userRepository.getOne(Long.parseLong(session.getAttribute("id").toString()));
//        } else {
//            return null;
//        }
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

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveUserNow(User user) {
        userRepository.saveAndFlush(user);
    }

}//close class UserService
