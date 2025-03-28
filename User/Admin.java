package User;

public class Admin extends User { // Admin class that inherits from User class
    public Admin(String id, String pass, String name){ // Constructor to set id, password, and name for Admin
     // Initialize the common properties from the User class
    super.id=id;
    super.pass=pass;
    super.name=name;
    }
}
