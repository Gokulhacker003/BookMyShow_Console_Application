import Action.*;
import Movie.Movie;
import Screen.Screen;
import Show.Show;
import Theatre.Theatre_POJO;
import User.*;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Admin_action implements Admin_Action  {

    @Override
    public User login(String Id) {
        Scanner scanner = new Scanner(System.in);
        User loggedInAdmin = null; // Variable to store the logged-in admin

        // Loop through all users to find the admin with the matching ID
        for (User user : BookMyShow_POJO.getUserList()) {
            if (user instanceof Admin && user.getId().equals(Id)) {

                // Allow the admin to try 3 times to enter the correct password
                    System.out.print("Enter Your Password: ");
                    String password = scanner.nextLine();

                    // Check if the entered password matches
                    if (user.getPass().equals(password)) {
                        loggedInAdmin = user; // Set the logged-in admin
                        
                    } else {
                        System.out.println("Incorrect Password.");
                    }
                    break; // Exit the loop if password is correct
            }
        }
        return loggedInAdmin; // Return the logged-in admin (or null if not found)
    }

    @Override
    public void UserOps(User admin) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true; // Flag to control the loop

        while (run) {
            // Display menu options for the admin
            System.out.println("1. Add Admin");
            System.out.println("2. Add Theatres");
            System.out.println("3. View Admins");
            System.out.println("4. View Theatres");
            System.out.println("5. Add Movie");
            System.out.println("6. View Movies");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                // Read user input and convert it to an integer
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                // Handle invalid input (non-integer values)
                System.out.println("Invalid input! Please enter a valid number.");
                continue; // Skip to the next iteration of the loop
            }

            // Execute actions based on user choice
            switch (choice) {
                case 1:
                    System.out.print("Enter the new Admin ID: ");
                    String adminId = scanner.nextLine();
                    addUser(adminId); // Call method to add a new admin
                    break;
                case 2:
                    addTheatre(); // Call method to add a new theatre
                    break;
                case 3:
                    viewAdmins(); // Call method to display all admins
                    break;
                case 4:
                    viewTheatres(); // Call method to display all theatres
                    break;
                case 5:
                    addMovie(); // Call method to add a new movie
                    break;
                case 6:
                    viewMovies(); // Call method to display all movies
                    break;
                case 7:
                    System.out.println("Exiting..."); // Exit message
                    run = false; // Set flag to false for exit the loop
                    break;
                default:
                    // Handle invalid choices outside the expected range
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void addUser(String adminId) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the new Admin ID

        boolean adminExists = false; // Flag to check if the admin already exists

        // Iterate through the list of users to check if the admin ID already exists
        for (User user : BookMyShow_POJO.getUserList()) {
            if (user instanceof Admin && user.getId().equals(adminId)) {
                adminExists = true; // If found, set flag to true
                break; // Exit loop as admin already exists
            }
        }

        // Check if the admin ID already exists
        if (adminExists) {
            System.out.println("Admin already exists.");
        } else {
            // If admin does not exist, prompt for password and name
            System.out.print("Enter the new Admin Password: ");
            String adminPass = scanner.nextLine();
            System.out.print("Enter the Admin Name: ");
            String adminName = scanner.nextLine();

            // Create a new Admin object and add it to the user list
            BookMyShow_POJO.getUserList().add(new Admin(adminId, adminPass, adminName));
            System.out.println("Admin added successfully!");
        }
    }

    public void addTheatre() {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the Theatre Name
        System.out.print("Enter the Theatre Name: ");
        String theatreName = scanner.nextLine();

        // Prompt the user to enter the Theatre Location
        System.out.print("Enter the Theatre Location: ");
        String theatreLocation = scanner.nextLine();

        // Check if the theatre already exists in the theatre map
        for (var temp : BookMyShow_POJO.getTheatreMap().keySet()) {
            var theatre = BookMyShow_POJO.getTheatreMap().get(temp);
            if (theatre.getTheatre_name().equals(theatreName) &&
                    theatre.getTheatre_location().equals(theatreLocation)) {
                System.out.println("Theatre already exists.");
                return; // Exit the method if the theatre already exists
            }
        }

        // Prompt the user to enter the number of screens in the theatre
        int numScreens=0;
        int count=0;
        while (count<3){
            try{
                System.out.print("Enter the number of Screens: ");
                numScreens = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (Exception e){
                System.out.println("Invaild input!");
                count++;
            }
        }

        // Create a map to store screen details
        HashMap<String, Screen> screenMap = new HashMap<>();

        // Loop to collect screen details
        while (numScreens > 0) {
            System.out.print("\nEnter the Screen Name: ");
            String screenName = scanner.nextLine();

            // Prompt for the number of seats in the screen

            int numSeats ;
            try {
                System.out.print("Enter the number of Seats: ");
                numSeats = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e){
                System.out.println("Invaild input!");
                continue;
            }

            // Prompt for the seating grid format
            System.out.print("Enter the grid (e.g., 2*4*2): ");
            String grid = scanner.nextLine();

            // Generate the seat arrangement based on user input
            Utility utility=new Utility();
            var seatArrangement = utility.genSeatPattern(numSeats, grid);

            // Validate the seat arrangement
            if (seatArrangement == null) {
                System.out.println("Invalid Grid. Screen not added.");
                continue; // Skip to the next iteration if the grid is invalid
            }

            // Create a new Screen object and add it to the screen map
            Screen screen = new Screen(screenName, numSeats, seatArrangement, grid);
            screenMap.put(screenName, screen);
            numScreens--; // Decrement the screen count
        }

        // Check if at least one screen is added before adding the theatre
        if (!screenMap.isEmpty()) {
            BookMyShow_POJO.getTheatreMap().put(theatreName,
                    new Theatre_POJO(theatreName, theatreLocation, screenMap));
            System.out.println("Theatre added successfully!");
        } else {
            System.out.println("No valid screens added. Theatre not created.");
        }
    }

    public void viewAdmins() {
        for (User user : BookMyShow_POJO.getUserList()) {// Display the userlist
            if (user instanceof Admin) {//Check if the user's object is Admin it displays the Admin id and name
                System.out.println("Admin ID: " + user.getId());
                System.out.println("Admin Name: " + user.getName());
            }
        }
    }

    public void viewTheatres() {
        // Check if there are no theatres available
        if (BookMyShow_POJO.getTheatreMap().isEmpty()) {
            System.out.println("No theatres available.");
            return;
        }

        // Loop through each theatre in the theatre map
        for (var theatre : BookMyShow_POJO.getTheatreMap().values()) {
            System.out.println("Theatre Name: " + theatre.getTheatre_name());
            System.out.println("Location: " + theatre.getTheatre_location());
            System.out.println("------------------------");

            // Loop through each screen in the theatre
            for (var screen : theatre.getscr_st().values()) {
                System.out.println("Screen Name: " + screen.getScr_name());
                System.out.println("Seats: " + screen.getNo_of_seats());
                System.out.println("Seat Arrangement:");
                System.out.println("_________________");

                // Display seat arrangement if available
                if (screen.getSeat_range() != null) {
                    for (var rowEntry : screen.getSeat_range().entrySet()) {
                        System.out.println(rowEntry.getValue());
                    }
                } else {
                    System.out.println("No seat arrangement available.");
                }
            }
            System.out.println("------------------------");
        }
    }


    public void addMovie() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Get movie details from the user
            System.out.print("Enter the Movie name: ");
            String movie_name = scanner.nextLine();
            System.out.print("Enter the Movie Location: ");
            String movie_loc = scanner.nextLine();

            // Check if a theatre exists in the given location
            boolean theatre_available = false;
            for (var temp : BookMyShow_POJO.getTheatreMap().values()) {
                if (temp.getTheatre_location().equals(movie_loc)) {
                    theatre_available = true;
                    break;
                }
            }

            // If no theatre is found, display available locations and exit
            if (!theatre_available) {
                for (var temp : BookMyShow_POJO.getTheatreMap().values()) {
                    System.out.println(temp.getTheatre_location());
                }
                System.out.println("\t There is no Theatre in that location.!");
                return;
            }

            // Get movie date and validate the format
            LocalDate date = null;
            int i = 0;
            while (i < 3) {
                i++;
                try {
                    System.out.print("Enter the Date (dd-MM-YYYY): ");
                    date = LocalDate.parse(scanner.nextLine(), BookMyShow_POJO.getDateFormat());
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid Date format");
                }
                if (i == 3) {
                    System.out.println("Please try again!");
                }
            }
            if (date == null) {
                return;
            }

            // Get movie duration and price per seat
            int duration;
                try {
                    System.out.print("Enter the Duration: ");
                    duration = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    return;
                }

            int pr_amount;
            try {
                System.out.print("Enter the price per seat: ");
                pr_amount = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e){
                System.out.println("Invalid input!");
                return;
            }

            // Display available theatres in the specified location
            System.out.println("Available Theatres in Location:");
            for (var temp : BookMyShow_POJO.getTheatreMap().values()) {
                if (temp.getTheatre_location().equals(movie_loc)) {
                    System.out.println("\t " + temp.getTheatre_name());
                }
            }

            // Get theatre details
            System.out.print("Enter the Theatre name: ");
            String theatre_name = scanner.nextLine();
            Theatre_POJO theatre = BookMyShow_POJO.getTheatreMap().get(theatre_name);

            if (theatre == null) {
                System.out.println("Invalid theatre.!");
                continue;
            }

            // Display available screens in the selected theatre
            System.out.println("Available Screen:");
            for (Screen screen : theatre.getscr_st().values()) {
                System.out.println("\t" + screen.getScr_name());
            }

            // Get screen details
            System.out.print("Enter the screen name: ");
            String scr_name = scanner.nextLine();
            Screen screen = theatre.getscr_st().get(scr_name);

            if (screen == null) {
                System.out.println("Invalid Screen.!");
                continue;
            }

            // Get the start time of the movie and validate format
            System.out.print("Enter the Starting time (HH:mm): ");
            LocalTime startTime = null;
            int j = 0;
            while (j < 3) {
                j++;
                try {
                    startTime = LocalTime.parse(scanner.nextLine(), BookMyShow_POJO.getTimeFormat());
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid time format");
                }
                if (j == 3) {
                    System.out.println("Please try again!");
                    return;
                }
            }

            // Calculate the end time of the movie
            LocalTime endTime = null;
            if (startTime != null) {
                endTime = startTime.plusMinutes(duration + 30);
            }

            // Check if there are overlapping show timings
            if (BookMyShow_POJO.getMovieMap() != null) {
                if (startTime != null) {
                    boolean overlap = overlapshow(startTime, endTime, screen, date);
                    if (overlap) return;
                }
            }

            // Create a new show and add it to the screen
            LocalTime hour = LocalTime.of(0, 0);
            Show show = new Show(startTime, endTime, date, screen, pr_amount, theatre.getscr_st().get(scr_name).getSeat_range());
            screen.getShows().add(show);

            // Create a new movie object and store it in the movie map
            Movie c_movie = new Movie(movie_name, movie_loc, date, show, hour.plusMinutes(duration), screen, theatre);
            if (!BookMyShow_POJO.getMovieMap().containsKey(movie_name)) {
                BookMyShow_POJO.getMovieMap().put(movie_name, new ArrayList<>());
            }
            BookMyShow_POJO.getMovieMap().get(movie_name).add(c_movie);

            System.out.println("Movie is added!");

            // Ask if the user wants to add another show for the same movie
            System.out.println("Do you want to add another show on the same day? (Yes/No): ");
            String another_show = scanner.nextLine();
            Show show1 = null;

            if (another_show.equalsIgnoreCase("yes") || another_show.equalsIgnoreCase("y")) {
                show1 = anothershow(date, scanner, c_movie.getScreen(), pr_amount, duration);
            }

            // If a new show is added, update the movie details
            if (show1 != null) {
                screen.getShows().add(show1);
                Movie c_movies = new Movie(movie_name, movie_loc, date, show1, hour.plusMinutes(duration), screen, theatre);
                BookMyShow_POJO.getMovieMap().get(movie_name).add(c_movies);
                System.out.println("Movie was added to another show!");
            }
            break;
        }
    }

    public void viewMovies() {
        // Check if there are no movies available
        if (BookMyShow_POJO.getMovieMap().isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        // Loop through all movies in the movie map
        for (var movieKey : BookMyShow_POJO.getMovieMap().keySet()) {
            var movieList = BookMyShow_POJO.getMovieMap().get(movieKey);

            // Loop through each movie in the list
            for (Movie movie : movieList) {
                System.out.println("___________________________");
                System.out.println("-> Movie      :" + movie.getMovie_name()); // Display movie name
                System.out.println("===========================");
                System.out.println("-> Theatre      :" + movie.getTheatre().getTheatre_name()); // Display theatre name
                System.out.println("-> Screen       :" + movie.getScreen().getScr_name()); // Display screen name
                System.out.println("-> Location     :" + movie.getTheatre().getTheatre_location()); // Display theatre location
                System.out.println("-> Date         :" + movie.getStartDate().format((BookMyShow_POJO.getDateFormat()))); // Display movie date
                System.out.println("-> Duration hour: " + movie.getDuration().toString() + " Hours"); // Display duration
                System.out.println("-> Start Time   :" + movie.getShow().getStartTime().format((BookMyShow_POJO.getTimeFormat()))); // Display start time
                System.out.println("-> End Time     :" + movie.getShow().getEndTime().format((BookMyShow_POJO.getTimeFormat()))); // Display end time
                System.out.println("===========================");
            }
        }
    }

    public Show anothershow(LocalDate date, Scanner sc, Screen screen, int price, int duration) {
        // Method to add another show

        LocalTime startTime = null; // Variable to store the start time
        int j = 0;

        while (j < 3) { // Allow up to 3 attempts for correct input
            j++;
            System.out.println("Enter the start time of the show (HH:mm)");

            try {
                // Read and parse the start time input
                startTime = LocalTime.parse(sc.nextLine(), BookMyShow_POJO.getTimeFormat());
                break; // Exit loop if input is valid
            } catch (Exception e) {
                System.out.println("Invalid time format");
                if (j == 3) { // If input is wrong 3 times, exit the function
                    System.out.println("Please try again!");
                    return null;
                }
            }
        }
        // Calculate the end time by adding the movie duration plus a 30-minute break
        LocalTime endTime = startTime != null ? startTime.plusMinutes(duration + 30) : null;

        boolean overlap;
        if (endTime != null) {
            // Check if the new show overlaps with an existing one
            overlap = overlapshow(startTime, endTime, screen, date);
            if (overlap) return null; // Exit if there is an overlap
        }

        // Get the seating arrangement for the screen
        HashMap<Character, ArrayList<String>> duplicateseatarrangement = screen.getSeat_range();

        // Create and return a new Show instance
        return new Show(startTime, endTime, date, screen, price, duplicateseatarrangement);
    }


    public boolean overlapshow(LocalTime startTime, LocalTime endTime, Screen screen, LocalDate date) {
        boolean hitting = false; // Flag to check if there is an overlap

        for (Show shows : screen.getShows()) {
            if (date.isEqual(shows.getDate()) && !(endTime.isBefore(shows.getStartTime()) || startTime.isAfter(shows.getEndTime()) || startTime.equals(shows.getStartTime()))) {
                hitting = true;
                break;
            }
        }
        //this block for checking all the show timings are matching or not.
        for (String movie : BookMyShow_POJO.getMovieMap().keySet()) {
            for (Movie movieobj : BookMyShow_POJO.getMovieMap().get(movie)) {
                if (movieobj.getShow().getStartTime().equals(startTime)) {
                    hitting = true;
                    break;
                }
            }
        }
        if (hitting) {//if both movies are hitting
            System.out.println("Show overlaps with an existing one.");
        }
        return hitting; // Return true if an overlap exists, false otherwise
    }
}