package User;

public class User {
    private String id; // User ID
    private String pass; // User password
    private String name; // User name

    // Constructor to initialize User with id, password, and name
    protected User(String id, String pass, String name) {
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    // Default constructor
    protected User() {
    }

    // Getter method for id
    public String getId() {
        return id;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for password
    public String getPass() {
        return pass;
    }
}
