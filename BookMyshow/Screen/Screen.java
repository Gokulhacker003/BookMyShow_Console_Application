package Screen;

import Show.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {
    private String scr_name;// Store the screen name
    private int no_of_seats;// Store the no of seats
    // Maps using row character to lists of seat arrangement objects
    private HashMap<Character, ArrayList<String>> seat_range = new HashMap<>();
    private HashSet<Show> shows = new HashSet<>();// List to store shows in screen
    private String seat_grid; // Store the seat space as a grid

    // Constructor for Screen
    public Screen(String sc_name, int n_seats, HashMap<Character, ArrayList<String>> seats_arr, String grid) {
        this.scr_name = sc_name; // Set screen name
        this.no_of_seats = n_seats;// Set no of seats
        this.seat_range = seats_arr;// Set seats arrangement
        this.seat_grid = grid;// Set grids
    }

    // Getter for seat arrangement
    public HashMap<Character, ArrayList<String>> getSeat_range() {
        return seat_range;
    }

    // Getter for number of seats
    public int getNo_of_seats() {
        return no_of_seats;
    }

    // Getter for seat grid
    public String getGrid() {
        return seat_grid;
    }

    // Getter for shows
    public HashSet<Show> getShows() {
        return shows;
    }

    // Getter for screen name
    public String getScr_name() {
        return scr_name;
    }

}