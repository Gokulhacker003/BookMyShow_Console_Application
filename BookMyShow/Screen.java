import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {
    private String scr_name;
    private int no_of_seats;
    private HashMap<Character, ArrayList<String>> seat_range = new HashMap<>();
    private HashSet<Show> shows = new HashSet<>();
    private String seat_grid;

    // Constructor for Screen
    public Screen(String sc_name, int n_seats, HashMap<Character, ArrayList<String>> seats_arr, String grid) {
        this.scr_name = sc_name;
        this.no_of_seats = n_seats;
        this.seat_range = seats_arr;
        this.seat_grid = grid;
    }

    // Default constructor
    public Screen() {}

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

    // Setter for seat arrangement
    public void setSeat_range(HashMap<Character, ArrayList<String>> seat_range) {
        this.seat_range = seat_range;
    }
}