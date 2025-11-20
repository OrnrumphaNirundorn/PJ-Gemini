package gemini.model;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String email;

    public Person(
            String firstName,
            String lastName,
            String address,
            String email
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

    public Person() {}

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
}
