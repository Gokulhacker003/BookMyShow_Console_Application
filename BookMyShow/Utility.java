import java.util.ArrayList;
import java.util.HashMap;

public class Utility {
    // Method to generate the seating pattern based on input number of seats and screen grid layout
    public static HashMap<Character, ArrayList<String>> genSeatPattern(int noSeats, String screenGrid) {
        int remainingSeats = noSeats; // Track remaining seats to be assigned
        String[] splitGrid = screenGrid.split("\\*"); // Split the grid layout by the '*' symbol

        // Calculate the total number of seats per row from the grid layout
        int seatsPerRow = 0;
        for (String grid : splitGrid) {
            seatsPerRow += Integer.parseInt(grid); // Add seats per block
        }

        // Check if the total number of seats is evenly divisible by seatsPerRow
        if (remainingSeats % seatsPerRow != 0) {
            System.out.println("Invalid Grid"); // If not, print an error and return null
            return null;
        }

        // Create the seating arrangement map with row labels and seat numbers
        var seatArrangement = new HashMap<Character, ArrayList<String>>();
        char ch = 'A'; // Start from row 'A'

        // Loop until all seats are assigned
        while (remainingSeats > 0) {
            ArrayList<String> rowSeats = new ArrayList<>();
            int n = 1; // Start numbering seats from 1 in each row

            // Check if row label has gone beyond 'Z' and switch to lowercase
            if ((int) ch > 90 && (int) ch < 97) {
                ch = 'a';
            }

            // Add seats to the row based on the grid layout
            for (int i = 0; i < splitGrid.length; i++) {
                int seatsInBlock = Integer.parseInt(splitGrid[i]); // Get the number of seats in this block

                for (int j = 0; j < seatsInBlock; j++) {
                    rowSeats.add(String.format("[%c%d]", ch, n)); // Add seat label in the format [A1]
                    n++; // Increment seat number
                }

                // Add separator between blocks, except for the last block
                if (i < splitGrid.length - 1) {
                    rowSeats.add(" <==> ");
                }
            }

            // Add the row seats to the seat arrangement map
            seatArrangement.put(ch, rowSeats);
            ch++; // Move to the next row

            remainingSeats -= seatsPerRow; // Subtract seats assigned in this row from remaining seats
        }

        return seatArrangement; // Return the final seating arrangement
    }
}
