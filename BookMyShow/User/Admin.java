package User; // Package for User classes

public class Admin extends User { // Admin class that inherits from User class
    public Admin(String id, String pass, String name){ // Constructor to set id, password, and name for Admin
        super(id, pass, name); // Initialize the common properties from the User class
    }

    public Admin(){ // Default constructor for Admin
        super(); // Call the default constructor of the User class
    }
}
