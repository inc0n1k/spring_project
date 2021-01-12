package n1k.spring_project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	//***Fields**************************

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Login is not Null")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z_\\d]+$", message = "Login is not valid (Only latin< number and _)")
	@Size(min = 5, max = 15, message = "Login length is not valid (5-15)")
	private String login;

	@NotNull(message = "Password is not Null")
	@Size(min = 8, message = "Min login length 8 digits")
	private String password;

	@NotNull(message = "Name is not Null")
	private String name;

	@NotNull(message = "Surname is not Null")
	private String surname;

	@NotNull(message = "Role is not Null")
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	//***Constructors********************

	public User() {
	}

	public User
			(
					@NotNull(message = "Is Null") String login,
					@NotNull(message = "Is Null") String password,
					@NotNull(message = "Is Null") String name,
					@NotNull(message = "Is Null") String surname,
					@NotNull(message = "Is Null") Role role
			) {
		this.login = login;
//		this.password = password.length() > 5 ? new BCryptPasswordEncoder().encode(password) : password;
		DigestUtils.md5DigestAsHex(password.getBytes());
		this.name = name;
		this.surname = surname;
		this.role = role;
	}

	//***Getters and Setters**************

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(role);
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password.length() > 5 ? DigestUtils.md5DigestAsHex(password.getBytes()) : password;
//		this.password = password.length() > 5 ? new BCryptPasswordEncoder().encode(password) : password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}//close class User
