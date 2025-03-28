package Action;
import Movie.*;
import Show.Show;
import User.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface Customer_Action extends Action{
    void movie_select(Customer customer);
    boolean movie_in_My_location(Customer customer);
    void movie_in_theatres(ArrayList<Movie> movies, HashMap<String, HashSet<Show>> shows_list, Customer customer);
    void changeLocationDate(Customer customer);
    void BookTicket(ArrayList<Movie> movies,Customer customer);
    ArrayList<String> select_seats(int counts, Show show);
    int calculateSeatIndex(int seat, String[] grid, int totalSeats);
    void viewTicket(Customer customer);
}
