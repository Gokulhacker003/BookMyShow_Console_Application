package Theatre;

import Screen.Screen;

import java.util.HashMap;

public class Theatre_POJO {
    private String theatre_name; // Store the theatre name
    private String theatre_location;// Store the theatre location
    // Maps screen names to Screen objects
    private HashMap<String, Screen> screenObject = new HashMap<>();

    // Constructor for Theatre_POJO
    public Theatre_POJO(String th_name, String th_location, HashMap<String, Screen> no_screen) {
        this.theatre_name = th_name;// Set the theatre name
        this.theatre_location = th_location;// Set the theatre location
        this.screenObject = no_screen;// Set map hashmap for screen
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
        return screenObject;
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format("Theatre Name:%s Theatre Location:%s", this.theatre_name, this.theatre_location);
    }
}
