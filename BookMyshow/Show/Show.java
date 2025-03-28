package Show;

import Screen.Screen;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {
    private LocalTime startTime;// Store the show start time
    private LocalTime endTime;// Store the show end time
    private LocalDate date;// Store the start date
    private Screen screen;// Store the show on screen
    private int price;// Store the seats price
    // Maps using row character to lists of seat arrangement objects
    private HashMap<Character, ArrayList<String>> seat_Arrangement;

    // Constructor for Show
    public Show(LocalTime start, LocalTime end, LocalDate date1, Screen screen1, int price, HashMap<Character, ArrayList<String>> seat_Arr) {
        this.startTime = start;// Set start time of show
        this.endTime = end;// Set end time of show
        this.date = date1;// Set date of show
        this.screen = screen1;// Set screen of show
        this.price = price;// Set price of show
        this.seat_Arrangement = seat_Arr;// Set seat arrangement of show
    }

    // Getter for price
    public int getPrice() {
        return price;
    }

    // Getter for date
    public LocalDate getDate() {
        return date;
    }

    // Getter for seat arrangement
    public HashMap<Character, ArrayList<String>> getSeat_arr() {
        return seat_Arrangement;
    }

    // Getter for end time
    public LocalTime getEndTime() {
        return endTime;
    }

    // Getter for screen
    public Screen getScreen() {
        return screen;
    }

    // Setter for seat arrangement
    public void setSeat_arr(HashMap<Character, ArrayList<String>> seat_arr) {
        this.seat_Arrangement = seat_arr;
    }

    // Getter for start time
    public LocalTime getStartTime() {
        return startTime;
    }

    // Override equals method
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Show show = (Show) object;
        return Objects.equals(this.startTime, show.startTime) && Objects.equals(this.endTime, show.endTime);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, date);
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format(String.valueOf(getStartTime()),String.format("\n Screen : %s",screen.getScr_name()));
    }
}
