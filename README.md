# BookMyShow Console Application

## Overview
BookMyShow Console Application is a command-line-based ticket booking system developed in **Java** that allows users to browse, select, and book movie tickets. This project demonstrates core Java programming concepts, including file handling, object-oriented programming, and user authentication. **This version of the application does not use a database and relies solely on file handling for data storage.**

## Features
- **User Authentication**: Register and login functionality with password validation.
- **Movie Listings**: View available movies with show timings.
- **Seat Selection**: Choose seats based on real-time availability.
- **Ticket Booking**: Reserve tickets and generate a booking confirmation.
- **Payment Simulation**: Basic payment validation (mock implementation).
- **Admin Panel**: Manage movies, schedules, and bookings.
- **Data Persistence**: Uses file handling and serialization for storing data.

## Technologies Used
- **Programming Language**: Java
- **File Handling**: Used for storing user data and booking details.
- **Java I/O Streams**: For efficient data management.
- **OOP Principles**: Follows Object-Oriented Programming best practices.

## Installation & Setup
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git (for cloning the repository)

### Steps to Install
1. Clone the repository:
   ```sh
   git clone git@github.com:Gokulhacker003/BookMyShow_Console_Application.git
   ```
2. Navigate to the project folder:
   ```sh
   cd BookMyShow_Console_Application
   ```
3. Compile the Java files:
   ```sh
   javac -d bin src/*.java
   ```
4. Run the application:
   ```sh
   java -cp bin Main
   ```

## Usage
1. Run the application from the terminal.
2. Choose an option from the menu (Login/Register/View Movies/etc.).
3. Browse available movies and select one.
4. Choose seats and confirm your booking.
5. Receive a booking confirmation with ticket details.
6. Exit the application or continue browsing.

## Project Structure
```
BookMyShow_Console_Application/
│── src/
│   ├── Main.java
│   ├── User.java
│   ├── Movie.java
│   ├── Booking.java
│   ├── Payment.java
│   ├── Admin.java
│   └── utils/
│── bin/ (Compiled files)
│── README.md
│── LICENSE
```

## Future Enhancements
- Integrate a real-time database (MySQL) for better data management.
- Implement an online payment gateway.
- Add a graphical user interface (GUI) using JavaFX or Swing.
- Enhance security measures with encryption for user authentication.
- Improve reporting and analytics for admins.

## Contributing
Contributions are welcome! Feel free to fork the repository, create a feature branch, and submit a pull request.

## Contact
For any queries, reach out to [Gokulhacker003](https://github.com/Gokulhacker003).

