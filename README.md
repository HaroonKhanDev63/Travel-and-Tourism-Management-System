✈️ Travel and Tourism Management System

A Java desktop application integrated with MySQL database to manage travel and tourism services like customer records, bookings, tour packages, and reports. The project features a user-friendly GUI with custom icons for better navigation and usability.

🚀 Features

👤 Customer Management – Add, edit, and view customer details.

🧳 Tour Packages – Manage available travel packages.

💳 Booking System – Book tours, handle invoices, and payments.

🏨 Hotel & Transport Management – Manage hotels, vehicles, and itineraries.

📊 Reports – Generate booking and customer reports.

🎨 User-Friendly UI – Designed with custom icons for a professional look.

🛠️ Technologies Used

Language: Java (Swing / AWT for GUI)

Database: MySQL

Database Connectivity: JDBC

IDE/Tools: NetBeans / Eclipse / IntelliJ

🗄️ Database Setup

Open phpMyAdmin or MySQL CLI.

Create a new database:

CREATE DATABASE travel_db;


Import the provided database.sql file into travel_db.

mysql -u root -p travel_db < database.sql


Update database credentials in the Java code if needed:

String url = "jdbc:mysql://localhost:3306/travel_db";
String user = "root";
String password = "your_password";

📂 Project Structure
Travel-and-Tourism-Management-System/
├── src/              # Java source code
├── icons/            # Icons used in the project
├── database.sql      # MySQL database export
├── README.md         # Project documentation

▶️ How to Run

Clone the repository:

git clone https://github.com/YOUR-USERNAME/Travel-and-Tourism-Management-System.git


Open the project in your Java IDE (NetBeans/Eclipse/IntelliJ).

Import the database.sql file into MySQL.

Update DB credentials in the Java code if necessary.

Run the application and explore the features 🚀

🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.

📜 License

This project is for educational purposes. You are free to use and modify it for learning.