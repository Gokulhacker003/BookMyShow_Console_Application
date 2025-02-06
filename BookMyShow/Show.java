import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Screen screen;
    private int price;
    private HashMap<Character, ArrayList<String>> seat_arr = new HashMap<>();

    // Constructor for Show
    public Show(LocalTime start, LocalTime end, LocalDate date1, Screen screen1, int price, HashMap<Character, ArrayList<String>> seat_Arr) {
        this.startTime = start;
        this.endTime = end;
        this.date = date1;
        this.screen = screen1;
        this.price = price;
        this.seat_arr = seat_Arr;
    }

    // Default constructor
    public Show() {}

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
        return seat_arr;
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
        this.seat_arr = seat_arr;
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
        return String.valueOf(getStartTime());
    }
}
