package n1k.spring_project.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    //***Fields**************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Is Null")
    private String name;

    @Column(name = "display_name")
    @NotNull(message = "Is Null")
    private String displayName;

    //***Related fields******************

    @OneToMany(mappedBy = "role")
    private List<User> users;

    //***Constructors********************

    public Role() {
    }

    public Role(
            @NotNull(message = "Is Null") String name,
            @NotNull(message = "Is Null") String displayName
    ) {
        this.name = name;
        this.displayName = displayName;
    }

    //***Getters and Setters**************

    @Override
    public String getAuthority() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}//close class Role
