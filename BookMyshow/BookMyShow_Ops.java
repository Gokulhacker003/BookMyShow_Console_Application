import java.util.Scanner;
import User.*;

public class BookMyShow_Ops {

    public static void start() { // Start the BookMyShow application
        Customer_action customer_action=new Customer_action();
        boolean run = true; // Control the main loop
        Scanner scanner = new Scanner(System.in); // Create scanner for user input
        System.out.println("--------------------------");
        System.out.println("| Welcome to Book My Show |");
        System.out.println("--------------------------");
        String no_id=null;
        while (run) { // Main loop for the application
            System.out.println("1. Login");
            System.out.println("2. Sign in");
            System.out.println("3. Exit");
            System.out.print("Enter your choice:");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Get the user's choice
            } catch (Exception e) {
                System.out.println("Invalid Choice");
                continue; // Continue the loop if input is invalid
            }
            switch (choice) { // Handle the user's choice
                case 1: // If user chooses Login
                    System.out.print("Enter your id: ");
                    String id = scanner.nextLine(); // Get the user's ID
                    int i = checkUser(id); // Check if user is found
                    if (i == 1) { // If user is a Customer

                        Customer customer = (Customer) customer_action.login(id); // Log in as Customer
                        if (BookMyShow_POJO.getUserList().contains(customer)) { // If customer exists
                            customer_action.UserOps(customer); // Perform customer operations
                        }
                        break;
                    } else if (i == 2) { // If user is an Admin
                        Admin_action admin_action=new Admin_action();
                        Admin admin = (Admin) admin_action.login(id); // Log in as Admin
                        if (BookMyShow_POJO.getUserList().contains(admin)) { // If admin exists
                            System.out.printf("Welcome %s! \n", admin.getName()); // Welcome the admin
                            admin_action.UserOps(admin); // Perform admin operations
                        }
                        break;
                    } else { // If ID is not found
                        System.out.println("Id not Found");
                        System.out.println("Do You Want to exit (Yes or y):");
                        String s = scanner.nextLine(); // Ask if the user wants to exit
                        if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y")) { // Exit if yes
                            run = false;
                            break;
                        }
                        no_id=id;
                    }
                case 2: // If user chooses Sign in
                    System.out.println("Creating new User....");
                    customer_action.addUser(no_id); // Create a new user
                    break;
                case 3: // If user chooses Exit
                    System.out.println("Exiting.....");
                    run = false; // Exit the loop
                    break;
                default: // If user enters an invalid option
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static int checkUser(String id) { // Method to check if the user is a Customer or Admin
        for (User user : BookMyShow_POJO.getUserList()) { // Iterate over all users
            if (user instanceof Customer) { // If user is a Customer
                if (user.getId().equals(id)) { // Check if IDs match
                    return 1; // Return 1 for Customer
                }
            } else if (user instanceof Admin) { // If user is an Admin
                if (user.getId().equals(id)) { // Check if IDs match
                    return 2; // Return 2 for Admin
                }
            }
        }
        return 0; // Return 0 if user is not found
    }
}
