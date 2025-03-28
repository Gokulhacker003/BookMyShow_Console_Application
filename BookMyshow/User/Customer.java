package User;

import Ticket.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends User { // Customer class that inherit User class
    private String location; // Store customer's location
    private LocalDate date; // Store date of the customer
    ArrayList<Ticket> ticket = new ArrayList<>(); // List to store customer's tickets

    public Customer(String id, String pass, String name, String loc) { // Constructor to create Customer with id, pass, name, and location
        // Initialize common properties from the User class
       super(id,pass,name);
        location = loc; // Set the customer's location
    }

    public Customer() { // Default constructor for Customer
        super(); // Call the default constructor of User class
    }
    public String getLocation() { // Get the customer's location
        return location; // Return location
    }

    public void setLocation(String loc) { // Set the customer's location
        this.location = loc; // Assign the location
    }

    public ArrayList<Ticket> getTicket() { // Get the list of tickets for the customer
        return ticket; // Return the list of tickets
    }

    public LocalDate getDate() { // Get the customer's date
        return date; // Return the date
    }

    public void setDate(LocalDate date) { // Set the customer's date
        this.date = date; // Assign the date
    }

}
