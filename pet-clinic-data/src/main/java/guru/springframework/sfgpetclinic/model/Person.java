package guru.springframework.sfgpetclinic.model;

public class Person {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}