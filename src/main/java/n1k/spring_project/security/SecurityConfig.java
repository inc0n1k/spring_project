package n1k.spring_project.security;

import n1k.spring_project.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//***Constructor********************************

	private final UserService userService;

	public SecurityConfig(UserService userService) {
		this.userService = userService;
	}

	//***Configuration********************************

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf()
				.disable()
				.authorizeRequests()

				//Доступ для всех
				.antMatchers(
						"/",
						"/favicon.ico",
						"/homepage",
						"/buy",
						"/select",
						"/processing",
						"/addtocart",
						"/cart",
						"/updatecart",
						"/removefromcart",
						"/createorder",
						"/filter/**"
				).permitAll()

				//Доступ for all к таблицам стилей, изображениям и скриптам
				.antMatchers(
						"/css/**",
						"/images/**",
						"/js/**"
				).permitAll()

				//еще не разобрался
				.antMatchers("/edit*").not().hasRole("user")

				//Все остальные страницы требуют аутентификации
				.anyRequest().authenticated()
				.and()

				//Настройка для входа в систему
				.formLogin().loginPage("/login").loginPage("/")
				.successForwardUrl("/homepage")
				//Перенарпавление на главную страницу после успешного входа
				.defaultSuccessUrl("/homepage").permitAll()
				.and()

				//При выходе перенаправление настраницу авторизации
				.logout()
				.permitAll()
				.logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

}//close SecurityConfig
