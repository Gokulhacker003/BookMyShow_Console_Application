package User;

public abstract class User {
    protected String id; // User ID
    protected String pass; // User password
    protected String name; // User name

    protected User(String iD,String PASS,String Name){
        this.id=iD;
        this.pass=PASS;
        this.name=Name;
    }
    protected User(){}

    // Getter method for id
    public String getId(){return this.id;}

    // Getter method for name
    public String getName() {
        return this.name;
    }

    // Getter method for password
    public String getPass() {
        return this.pass;
    }
}
