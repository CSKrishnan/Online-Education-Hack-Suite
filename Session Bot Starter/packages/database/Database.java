package packages.database;

import java.sql.*;

public class Database {
    public String[] userData = new String[5];
    public String[][] timetable = new String[50][6];
    public Database() {                                     //Initialize Database and gets User's Details
        userData = new String[]{"", "", "", "", ""};
        timetable = new String[][] {{"", "", "", "", "", ""}};
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarp", "root", "bharath7");
            System.out.println("\tDatabase connected!");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("\tCannot connect the database!");
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarp", "root", "bharath7");

            String sql = "INSERT INTO UserInfo VALUES ('" + userData[0] + "', '" + userData[1] + "', '" + userData[2] + "', '" + userData[3] + "', '" + userData[4] + "')";
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Database updated!!");
            connection.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("\tCannot connect the database!");
            e.printStackTrace();
        }
    }

    public String[] getUserData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarp", "root", "bharath7");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserInfo");
            while (rs.next()) {
                userData[0] = rs.getString(1);
                userData[1] = rs.getString(2);
                userData[2] = rs.getString(3);
                userData[3] = rs.getString(4);
                userData[4] = rs.getString(5);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("\tCannot connect the database!");
            e.printStackTrace();
        }
        return userData;
    }

    public void setUserData(String[] userData) {
        this.userData = userData;
    }

    public String[][] getTimetable() {
        String timetable[][] = new String[50][6];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarp", "root", "bharath7");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Timetable");
            int counter = 0;
            while (rs.next()) {
                timetable[counter][0] = rs.getString(1);
                timetable[counter][1] = rs.getString(2);
                timetable[counter][2] = rs.getString(3);
                timetable[counter][3] = rs.getString(4);
                timetable[counter][4] = rs.getString(5);
                timetable[counter][5] = rs.getString(6);
                counter = counter + 1;
                System.out.println(timetable[counter][2]);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("\tCannot connect the database!");
            e.printStackTrace();
        }
        return timetable;
    }

    public void updateTimetable(String[] timetable) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarp", "root", "bharath7");

            String sql = "INSERT INTO Timetable VALUES ('" + timetable[0] + "', '" + timetable[1] + "', " + timetable[2] + ", " + timetable[3] + ", " + timetable[4] + ", " + timetable[5] + ")";
            System.out.println(sql);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Database updated!!");
            connection.close();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("\tCannot connect the database!");
            e.printStackTrace();
        }
    }
}
