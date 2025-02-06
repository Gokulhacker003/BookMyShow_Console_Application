import User.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BMS_POJO {

    // Stores all users (Admins and others)
    private static ArrayList<User> userList = new ArrayList<>();

    // Maps theatre names to Theatre objects
    private static HashMap<String, Theatre_POJO> theatreMap = new HashMap<>();

    // Maps movie names to lists of Movie objects
    private static HashMap<String, ArrayList<Movie>> movieMap = new HashMap<>();

    // Date format: DD-MM-YYYY
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Time format: HH:mm (24-hour format)
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    // Static block to initialize some default values
    static {
        // Add a default admin user
        userList.add(new Admin("admin", "admin123", "Gokul"));
    }

    // Getters for the static fields

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static HashMap<String, Theatre_POJO> getTheatreMap() {
        return theatreMap;
    }

    public static HashMap<String, ArrayList<Movie>> getMovieMap() {
        return movieMap;
    }

    public static DateTimeFormatter getDateFormat() {
        return dateFormat;
    }

    public static DateTimeFormatter getTimeFormat() {
        return timeFormat;
    }

}
