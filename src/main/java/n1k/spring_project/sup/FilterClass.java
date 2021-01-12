package n1k.spring_project.sup;

public class FilterClass {
    private String name;
    private String value;

    public FilterClass() {
    }

    public FilterClass(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
