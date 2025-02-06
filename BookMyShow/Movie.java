import java.time.LocalDate;
import java.time.LocalTime;

public class Movie {
    private String movie_name; // Store the movie name
    private String movie_location; // Store the movie location
    private LocalDate startDate; // Store the movie's start date
    private Show show; // Store the show details
    private LocalTime duration; // Store the duration of the movie
    private Screen screen; // Store the screen details for the movie
    private Theatre_POJO theatre; // Store the theatre details where the movie is showing

    // Constructor to initialize a Movie object with all properties
    public Movie(String name, String loc, LocalDate startDate, Show show, LocalTime duration, Screen screen1, Theatre_POJO theatre) {
        this.movie_name = name; // Set movie name
        this.movie_location = loc; // Set movie location
        this.startDate = startDate; // Set movie start date
        this.show = show; // Set the show details
        this.duration = duration; // Set movie duration
        this.screen = screen1; // Set the screen details
        this.theatre = theatre; // Set the theatre details
    }

    public String getMovie_location() {
        return movie_location; // Return the movie location
    }

    public String getMovie_name() {
        return movie_name; // Return the movie name
    }

    public Screen getScreen() {
        return screen; // Return the screen details
    }

    public LocalTime getDuration() {
        return duration; // Return the movie duration
    }

    public Show getShow() {
        return show; // Return the show details
    }

    public Theatre_POJO getTheatre() {
        return theatre; // Return the theatre details
    }

    public LocalDate getStartDate() {
        return startDate; // Return the movie's start date
    }
}