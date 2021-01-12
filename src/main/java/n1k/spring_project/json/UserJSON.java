package n1k.spring_project.json;

import n1k.spring_project.model.User;

public class UserJSON {

    //Fields
    private String name;

    private String login;

    //Constructors
    public UserJSON() {
        //
    }

    public UserJSON(User user) {
        this.name = user.getName();
        this.login = user.getLogin();
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}//close class UserJSON
