package Action;
import Show.*;
import Screen.Screen;
import java.time.LocalDate;
import java.util.Scanner;

public interface Admin_Action extends Action{
    void addTheatre();
    void viewAdmins();
    void viewTheatres();
    void addMovie();
    void viewMovies();
    Show anothershow(LocalDate date, Scanner sc, Screen screen, int price, int duration);
}
