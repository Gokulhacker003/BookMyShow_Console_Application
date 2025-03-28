import Movie.Movie;
import Show.Show;
import Ticket.Ticket;
import User.*;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import Action.*;

class Customer_action implements Customer_Action{

    // Customer login method to validate and return the customer object after successful login
    @Override
    public User login(String Id) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();

        for (User user : BookMyShow_POJO.getUserList()) { // Iterating through the list of users
            if (user instanceof Customer && user.getId().equals(Id)) { // Check if the current user is a Customer && Match input ID with the stored user ID
                System.out.print("Enter Your Password:");
                String pass = scanner.nextLine(); // Get the password input
                if (user.getPass().equals(pass)) { // Verify if the entered password is correct
                    customer = (Customer) user; // Set the customer if login is successful
                } else {
                    System.out.println("Incorrect Password.");
                }
                break;
            }
        }
        return customer; // Return the logged-in customer object or null if login failed
    }

    // Method to handle customer operations after login
    @Override
    public void UserOps(User user) {
        Customer customer =(Customer)user;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Welcome %s!", customer.getName());
        boolean run = true;
        while (run) {
            movie_select( customer); // Call movie selection method
            whilex:while (true){
                System.out.println("1.Change location");
                System.out.println("2.View Booked Tickets");
                System.out.println("3.Book Tickets");
                System.out.println("4.Exit");
                int choice = 0;
                System.out.print("Enter your Choice:");
                try {
                    choice = Integer.parseInt(scanner.nextLine()); // Parse user input for operation
                } catch (Exception e) {
                    System.out.println("Invalid Choice");
                }
                switch (choice) {
                    case 1:
                        System.out.println("Changing Location/ Date.");
                        changeLocationDate(customer); // Call method to change location or date
                        break;
                    case 2:
                        System.out.println("Booked Ticket");
                        System.out.println("-------------");
                        viewTicket(customer); // Display the booked tickets for the customer
                        continue;
                    case 3:
                        System.out.println("Booking Tickets");
                        movie_select(customer); // Allow user to book a ticket
                        break;
                    case 4:
                        run = false; // Exit the loop
                        break whilex;
                    default:
                        System.out.println("Invalid Input!"); // Handle invalid input
                }
            }
        }
    }

    // Method to add a new user to the system
    @Override
    public void addUser(String id) {
        Scanner scanner = new Scanner(System.in);

        String Eid ;
        if(id==null){
            System.out.print("Enter the new ID:");
            Eid = scanner.nextLine();
        }
        else{
            Eid=id;
        }
        boolean userExists = false; // Flag to check if user already exists
        for (User customer : BookMyShow_POJO.getUserList()) { // Iterate through the existing users
            if (customer instanceof Customer) { // Check if the user is a customer
                if (customer.getId().equals(Eid)) { // Check if the ID already exists
                    userExists = true; // Set flag to true if ID exists
                    break;
                }
            }
            if (customer instanceof Admin) { // Similarly check for admin
                if (customer.getId().equals(Eid)) {
                    userExists = true; // Set flag to true if admin ID exists
                }
            }
        }
        if (userExists) { // If user exists, display message
            System.out.println("User Already Exists.");
        } else { // If user does not exist, proceed with adding new user
            System.out.print("Enter the new Password:");
            String Epass = scanner.nextLine();
            System.out.print("Enter Customer Name:");
            String Ename = scanner.nextLine();
            System.out.print("Enter Your Location:");
            String Eloc = scanner.nextLine();
            BookMyShow_POJO.getUserList().add(new Customer(Eid, Epass, Ename, Eloc)); // Add new customer to user list
        }
    }

    // Method to allow customer to select a movie
    public void movie_select(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Movie> movie = null;
         while (true) {
            boolean movie_is = movie_in_My_location(customer); // Check if movies are available in the customer's location
            if (movie_is) {
                System.out.print("Enter the movie name:");
                String movie_name = scanner.nextLine(); // Get the movie name input
                if (BookMyShow_POJO.getMovieMap().get(movie_name)!=null){
                    for (Movie movie1 : BookMyShow_POJO.getMovieMap().get(movie_name)) {
                        if (movie1.getMovie_location().equals(customer.getLocation())) {
                            movie = BookMyShow_POJO.getMovieMap().get(movie_name); // Assign the movie object based on location
                            break;
                        } else {
                            System.out.println("First Select movie");
                            // Continue if movie location does not match
                        }
                    }
                }
            } else {
                break; // Break the loop if no movies available in the location
            }
            if (movie != null) {
                BookTicket(movie, customer); // Call method to book a ticket for the selected movie
                break;
            }
        }
    }

    // Method to check if movies are available in the customer's location
    public boolean movie_in_My_location(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAvailable Movies in your location:");
        boolean check_movie = false;
        customer.setDate(LocalDate.now()); // Set the current date for movie selection
        for (String movie_has : BookMyShow_POJO.getMovieMap().keySet()) {
            for (Movie movie : BookMyShow_POJO.getMovieMap().get(movie_has)) {
                if ((customer.getLocation().equals(movie.getMovie_location())) && (movie.getStartDate().isAfter(customer.getDate()) || movie.getStartDate().isEqual(customer.getDate()))) {
                    System.out.println("\t" + movie.getMovie_name()); // Display available movies
                    check_movie = true;// Set flag if movies are available
                }
                break;
            }
        }
        if (!check_movie) { // If no movies are found in the location
            System.out.println("No Movies in Your location do you want to change location(yes/ no):");
            String check = scanner.nextLine();
            if (check.equalsIgnoreCase("yes") || check.equalsIgnoreCase("y")) {
                changeLocationDate(customer); // Allow user to change location or date
                movie_in_My_location(customer); // Retry movie selection after location change
            }
        }
        return check_movie; // Return true if movies are available, false otherwise
    }

    // Method to check and list available movies in theatres
    public void movie_in_theatres(ArrayList<Movie> movies, HashMap<String, HashSet<Show>> shows_list, Customer customer) {
        boolean check = false;
         // Iterate through all movie
            for (var movie : movies) {
                    for (var shows1 : movie.getScreen().getShows()) {
                        if (movie.getMovie_location().equals(customer.getLocation())) { // Check if theatre is in the customer's location
                            if (customer.getDate().isEqual(movie.getStartDate()) || customer.getDate().isAfter(movie.getStartDate())) {
                                if (shows_list.containsKey(movie.getTheatre().getTheatre_name())) {
                                    shows_list.get(movie.getTheatre().getTheatre_name()).add(shows1); // Add show to theatre list
                                } else {
                                    HashSet<Show> shows = new HashSet<>();
                                    shows.add(shows1);
                                    shows_list.put(movie.getTheatre().getTheatre_name(), shows); // Create a new set for shows
                                }
                                check = true;
                            }
                        }
                    }
                }
        if (check) { // If shows are found, display theatre and show details
            for (String theatreName : shows_list.keySet()) {
                for ( var show : shows_list.get(theatreName)) {
                    System.out.println("Theatre Name :" + theatreName);
                    System.out.println("Show time:" + show.toString());
                }
            }
        }
    }

    // Method to change the customer's location or date
    public void changeLocationDate(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an Location or Date(Location=1:Date=2):");
        int check = 0;
        try {
            check = Integer.parseInt(scanner.nextLine()); // Get user input for location or date change
        } catch (Exception e) {
            System.out.println("Enter the valid input");
        }
        while (true) {
            LocalDate newdate = null;
            String loc;
            if (check == 1) { // If user wants to change location
                System.out.print("Enter Your new Location:");
                loc = scanner.nextLine();
                if (loc != null && loc.equals(customer.getLocation())) {
                    System.out.println("Please Enter your new location.");
                    continue;
                }
                customer.setLocation(loc); // Set the new location for the customer
                System.out.println("Your Location is changed.");
            } else if (check == 2) { // If user wants to change date
                System.out.print("Enter the new Date(dd-MM-YYYY):");
                String dat = scanner.nextLine();
                int count=0;
                while (count<3){
                    try {
                        newdate = LocalDate.parse(dat, BookMyShow_POJO.getDateFormat()); // Parse the new date
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Date!");
                        count++;
                        if (count==3){
                            return;
                        }
                    }
                }
                if (newdate != null && !newdate.isEqual(LocalDate.now())) {
                    customer.setDate(newdate); // Set the new date
                    System.out.println("Your Date is changed.");
                }
                else{
                    System.out.println("Invalid date");
                }
            } else {
                System.out.println("Invalid input! Please enter 1 or 2.");
            }
            break; // Exit the loop after successful location or date change
        }
    }
    public void BookTicket(ArrayList<Movie> movies,Customer customer){
        Scanner scanner =new Scanner(System.in);//Create an on object for scanner class
        System.out.println("Movies in Theatre!");
        HashMap<String, HashSet<Show>> Show_List=new HashMap<>();//it is used to store a show list in hashmap

        //it repeats the loop until using if to condition the condition is false it will return of continue or break
        while (true){
            movie_in_theatres(movies,Show_List,customer);
            System.out.print("Enter the name of theatre :");
            String th_Name = scanner.nextLine();
            System.out.print("Enter the show time :");
            LocalTime show_time;
            try {
                show_time = LocalTime.parse(scanner.nextLine(), BookMyShow_POJO.getTimeFormat());
            }
            catch (Exception e){//it throws an exception it will continue
                System.out.println("Invalid Time Format");
                continue;
            }
            HashSet<Show> showset = Show_List.get(th_Name);
            if (showset==null){//if show is null return to where the function is called as void
                System.out.println("Invaild Show or Theatre !");
                return;
            }
            Show current_show=null;
            for (Show show_n:showset){//it is used to check the show is right ro wrong
                if(show_n.getStartTime().equals(show_time)) {
                    current_show=show_n;
                    break;
                }
            }
            if(current_show!=null){//if current show isn't a null it will execute this condition
                System.out.println("Screen name:"+current_show.getScreen().getScr_name());
                System.out.println("No of.seats:"+current_show.getScreen().getNo_of_seats());
                var seatGirds=current_show.getSeat_arr();//it is used to get the current show's seat
                System.out.println("Seat Arrangement :\n_________________________");
                for (var seats:seatGirds.entrySet()){// displaying available seats
                    System.out.println(seats.getKey() + " " + seats.getValue());
                }
                System.out.print("Enter the no.of seats to book:");
                int seats_counts;
                try{
                    seats_counts = Integer.parseInt(scanner.nextLine());//get the input from user and it is used to no of seats
                }
                catch (Exception e){
                    System.out.println("Invalid input!");
                    continue;
                }
                int price=seats_counts* current_show.getPrice();//it is used to calculate the total price of seats
                var Bookedtickets=select_seats(seats_counts,current_show);//it is used to store a booked tickets
                for(var movie:movies){//it used to display the total price and booked seats
                    if (Bookedtickets != null) {
                        System.out.println("Ticket Amount paying Rs.:" + price);
                        Ticket ticket = new Ticket(th_Name, movie.getMovie_name(), movie.getScreen().getScr_name(), movie.getMovie_location(), show_time, Bookedtickets, price);
                        customer.getTicket().add(ticket);
                        System.out.println("Tickets booked Successfully !");
                        System.out.println(Bookedtickets);
                        break;
                    } else {
                        System.out.println("Ticket is Canceled !");
                        return;
                    }
                }
            }
            break;
        }
    }
    
    //This method is used of select seats
    public ArrayList<String> select_seats(int counts, Show show) {
        Scanner scanner = new Scanner(System.in);
        // Create a copy of the seat arrangement
        var seatGrid = show.getSeat_arr();
        HashMap<Character, ArrayList<String>> tempSeatGrid = new HashMap<>();

        for (var entry : seatGrid.entrySet()) {
            char rowKey = entry.getKey();
            ArrayList<String> seatList = entry.getValue();
            tempSeatGrid.put(rowKey, new ArrayList<>(seatList)); // Duplicate the seat arrangement
        }

        ArrayList<String> bookedSeats = new ArrayList<>();

        while (counts > 0) {
            System.out.println("Enter the Seat number to book:");
            String seat_area = scanner.nextLine();
            char row = seat_area.charAt(0); // Extract row
            int seat = Integer.parseInt(seat_area.substring(1)); // Extract seat number

            // Validate seat range
            String[] grid = show.getScreen().getGrid().split("\\*");
            int totalSeats = 0;
            for (String g : grid) {
                totalSeats += Integer.parseInt(g);
            }

            if (seat <= 0 || seat > totalSeats) {
                System.out.println("Invalid seat number. Please try again.");
                continue;
            }
            String selectedSeat;
            // Determine index in the row

            int index = calculateSeatIndex(seat,grid,totalSeats);

                try {
                    selectedSeat = tempSeatGrid.get(row).get(index);
                } catch (Exception e) {
                    System.out.println("Enter the valid input");
                    continue;
                }

            // Check if the seat is already booked
            if (selectedSeat != null){
                if (selectedSeat.equals("[X ]")) {
                    System.out.println("This seat is already booked!");
                }
                // Mark the seat as booked temporarily
                else {
                    if (selectedSeat.equalsIgnoreCase(" <==> ")) {
                        continue;
                    }
                    tempSeatGrid.get(row).set(index, "[X ]");// set as temp seat grid
                    bookedSeats.add(seat_area);//to add booked tickets
                    --counts;// it reduces the count

                }
            }
        }

        // Display the updated seat arrangement
        for (var seatEntry : tempSeatGrid.entrySet()) {
            System.out.println(seatEntry.getKey() + " " + seatEntry.getValue());
        }

        // Confirm booking
        System.out.println("Confirm your seat booking! [yes or no]");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
            show.setSeat_arr(tempSeatGrid); // Commit the changes
        } else {
            System.out.println("Booking has been cancelled.");
            return null;
        }
        return bookedSeats;
    }
    
    // It is used to calculate the seat index
    public int calculateSeatIndex(int seat, String[] grid, int totalSeats) {
        int lowerLimit = Integer.parseInt(grid[0]); // Convert the first element of grid to integer for lower limit
        int upperLimit = (totalSeats+1) - Integer.parseInt(grid[grid.length-1]); // Calculate the upper limit based on totalSeats and last grid element
        if (seat <= lowerLimit) { // If seat is less than or equal to the lower limit
            return seat - 1; // Return seat index decreased by 1
        } else if (seat >= upperLimit) { // If seat is greater than or equal to the upper limit
            return seat ; // Return seat index increased by 1
        } else {
            return seat; // Return the seat index as is
        }
    }
    
    // Method to view the tickets that the customer has booked
    public void viewTicket(Customer customer) {
        int k = 1;
        if (customer.getTicket().isEmpty()) {
            System.out.println("No tickets are available."); // No tickets found
        } else {
            for (Ticket ticket : customer.getTicket()) {// Check if the ticket belongs to the customer
                System.out.println(k++ + ". Movie Name: " + ticket.getMv_name()); // Display ticket details
                System.out.println("Screen Name: " + ticket.getSc_name());
                System.out.println("Theatre Name: "+ ticket.getTh_name());
                System.out.println("\tLocation: " + ticket.getLocation());
                System.out.println("\tBooking Date: " + customer.getDate());
                System.out.println("\tBooking Time: " + ticket.getStartTime());
                System.out.println("\tTicket Price: " + ticket.getPrice());
                System.out.println("\tSeat No: " + ticket.getBooked_tickets());
            }
        }
    }
}