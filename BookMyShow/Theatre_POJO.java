import java.util.HashMap;

public class Theatre_POJO {
    private String theatre_name;
    private String theatre_location;
    private HashMap<String, Screen> scr_st = new HashMap<>();

    // Constructor for Theatre_POJO
    public Theatre_POJO(String th_name, String th_location, HashMap<String, Screen> no_screen) {
        this.theatre_name = th_name;
        this.theatre_location = th_location;
        this.scr_st = no_screen;
    }

    // Getter for theatre name
    public String getTheatre_name() {
        return theatre_name;
    }

    // Getter for theatre location
    public String getTheatre_location() {
        return theatre_location;
    }

    // Getter for screen map
    public HashMap<String, Screen> getscr_st() {
        return scr_st;
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format("Theatre Name:%s Theatre Location:%s", this.theatre_name, this.theatre_location);
    }
}
