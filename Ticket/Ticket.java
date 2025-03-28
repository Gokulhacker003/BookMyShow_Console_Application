package Ticket;

import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket { // Define Ticket class
    private String th_name; // Declare the theater name variable
    private String mv_name; // Declare the movie name variable
    private String sc_name; // Declare the screen name variable
    private String location; // Declare the location of the theater
    private LocalTime startTime; // Declare the start time of the movie
    private ArrayList<String> booked_tickets; // Declare ArrayList to store booked tickets
    private int price; // Declare the price variable for the ticket

    public Ticket(String thname, String mvname, String scname, String loc, LocalTime time, ArrayList<String> tickets, int price) { // Constructor to initialize Ticket object with values
        this.th_name = thname; // Set the theater name
        this.mv_name = mvname; // Set the movie name
        this.sc_name = scname; // Set the screen name
        this.location = loc; // Set the location of the theater
        this.startTime = time; // Set the start time of the movie
        this.booked_tickets = tickets; // Set the list of booked tickets
        this.price = price; // Set the price of the ticket
    }

    public String getLocation() { // Getter method for location
        return location; // Return the location of the theater
    }

    public String getMv_name() { // Getter method for movie name
        return mv_name; // Return the movie name
    }

    public LocalTime getStartTime() { // Getter method for start time
        return startTime; // Return the start time of the movie
    }

    public ArrayList<String> getBooked_tickets() { // Getter method for booked tickets
        return booked_tickets; // Return the list of booked tickets
    }

    public String getSc_name() { // Getter method for screen name
        return sc_name; // Return the screen name
    }

    public int getPrice() { // Getter method for ticket price
        return price; // Return the price of the ticket
    }

    public String getTh_name() {// Getter method for ticket Theatre name
        return th_name;// Return the Theatre name of the ticket
    }
}


