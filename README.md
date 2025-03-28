# BookMyShow Console Application

## Overview
BookMyShow Console Application is a command-line-based ticket booking system developed in **Java** that allows users to browse, select, and book movie tickets. This project demonstrates core Java programming concepts, including file handling, object-oriented programming, and user authentication. **This version of the application does not use a database and relies solely on file handling for data storage.**

## Class Diagram
![Untitled-2025-01-30-1203](https://github.com/user-attachments/assets/cc75b63d-f89f-4310-8149-06e0e30a9b49)
## Features
- View available movies and show timings
- Book movie tickets
- Cancel bookings
- Display booking details
- User-friendly CLI interface
- Seat selection functionality
- Admin panel for managing movies and shows

## Technologies Used
- Programming Language: Java
- Data Storage: File-based (TXT/JSON/CSV)
- Development Environment: Windows/Linux

## Project Structure
```
BookMyShow_Console_Application/
│── src/
│   ├── BookMyshow/
│   │   ├── Admin_action.java
│   │   ├── BookMyShow_Main.java
│   │   ├── BookMyShow_Ops.java
│   │   ├── BookMyShow_POJO.java
│   │   ├── Customer_action.java
│   │   ├── Utilities.java
│   │   ├── Utility.java
│   │
│   ├── BookMyshow/Action/
│   │   ├── Action.java
│   │   ├── Admin_Action.java
│   │   ├── Customer_Action.java
│   │
│   ├── BookMyshow/Movie/
│   │   ├── Movie.java
│   │
│   ├── BookMyshow/Screen/
│   │   ├── Screen.java
│   │
│   ├── BookMyshow/Show/
│   │   ├── Show.java
│   │
│   ├── BookMyshow/Theatre/
│   │   ├── Theatre_POJO.java
│   │
│   ├── BookMyshow/Ticket/
│   │   ├── Ticket.java
│   │
│   ├── BookMyshow/User/
│   │   ├── Admin.java
│   │   ├── Customer.java
│   │   ├── User.java
│── data/
│── README.md
│── LICENSE
│── .gitignore
```

## Class Diagram
![Class Diagram](https://github.com/user-attachments/assets/cc75b63d-f89f-4310-8149-06e0e30a9b49)

## Installation & Usage
1. Clone the repository:
   ```sh
   git clone https://github.com/Gokulhacker003/BookMyShow_Console_Application.git
   ```
2. Navigate to the project folder:
   ```sh
   cd BookMyShow_Console_Application
   ```
3. Compile the source code:
   ```sh
   javac -d bin src/**/*.java
   ```
4. Run the application:
   ```sh
   java -cp bin BookMyshow.BookMyShow_Main
   ```

## Future Enhancements
- Implement a database for better data management
- Add user authentication
- Integrate payment gateway
- Improve UI/UX for better user experience
- Add real-time seat availability tracking

## Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests.

## Contact
For any queries, reach out to [Gokul R.](gokulgokul6547@gmail.com).

