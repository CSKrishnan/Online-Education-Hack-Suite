package packages.user;

import packages.database.Database;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OldUser extends Applet implements ActionListener  {
    public String[] userData;
    public String[][] timetable;

    JFrame welcomeFrame, modifyUserDataFrame;
    Label heading, welcomeLabel, nameLabel, usernameLabel, passwordLabel, userTypeLabel, platformLabel, temp;
    TextField name, username, password;
    Button modifyUserDataButton, modifyButton, timetableButton, addTimetableButton;
    CheckboxGroup userType, platform;
    Checkbox teacher, student, msteams, gmeet;
    JTable timetableTable;



    public OldUser(String[] userData) {
        this.userData = userData;
    }

    public void welcomeScreen(String[] userData) {
        welcomeFrame = new JFrame("Online Education Suite");

        heading = new Label("Welcome to Online Education Hack Suite");
        heading.setFont(new Font("Lucida", Font.BOLD, 30));
        heading.setBounds(40, 50, 700, 30);

        welcomeLabel = new Label("Hi, " + userData[0]);
        welcomeLabel.setFont(new Font("Lucida", Font.BOLD, 25));
        welcomeLabel.setBounds(20, 150, 500, 30);

        usernameLabel = new Label("Username : " + userData[1]);
        usernameLabel.setFont(new Font("Lucida", Font.BOLD, 20));
        usernameLabel.setBounds(20, 200, 700, 30);

        userTypeLabel = new Label("User Type : " + userData[3]);
        userTypeLabel.setFont(new Font("Lucida", Font.BOLD, 20));
        userTypeLabel.setBounds(20, 250, 200, 30);

        platformLabel = new Label("Platform : " + userData[4]);
        platformLabel.setFont(new Font("Lucida", Font.BOLD, 20));
        platformLabel.setBounds(350, 250, 300, 30);

        modifyUserDataButton = new Button("Modify User Data");
        modifyUserDataButton.setFont(new Font("Lucida", Font.BOLD, 20));
        modifyUserDataButton.setBounds(200, 300, 200, 30);
        modifyUserDataButton.addActionListener(this);

        timetableButton = new Button("Show TimeTable");
        timetableButton.setFont(new Font("Lucida", Font.BOLD, 20));
        timetableButton.setBounds(50, 400, 200, 30);
        timetableButton.addActionListener(this);

        addTimetableButton = new Button("Add New Subject");
        addTimetableButton.setFont(new Font("Lucida", Font.BOLD, 20));
        addTimetableButton.setBounds(300, 400, 200, 30);
        addTimetableButton.addActionListener(this);

        temp = new Label("");

        welcomeFrame.add(heading);
        welcomeFrame.add(welcomeLabel);
        welcomeFrame.add(usernameLabel);
        welcomeFrame.add(userTypeLabel);
        welcomeFrame.add(platformLabel);
        welcomeFrame.add(modifyUserDataButton);
        welcomeFrame.add(timetableButton);
        welcomeFrame.add(addTimetableButton);
        welcomeFrame.add(temp);

        welcomeFrame.setVisible(true);
        welcomeFrame.setSize(700, 500);
        welcomeFrame.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                welcomeFrame.dispose();
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            userData[0] = name.getText();
            userData[1] = username.getText();
            userData[2] = password.getText();
            userData[3] = "Teacher";
            if(student.getState())      userData[3] = "Student";
            else userData[4] = "MS Teams";
            if(gmeet.getState())      userData[4] = "GMeet";
            Database db = new Database();
            db.setUserData(userData);
            modifyUserDataFrame.dispose();
            welcomeFrame.dispose();
            welcomeScreen(userData);
        }

        if (e.getSource() == modifyUserDataButton) {
            modifyUserDataFrame = new JFrame("Online Education Suite");

            welcomeLabel = new Label("Hi, " + userData[0]);
            welcomeLabel.setFont(new Font("Lucida", Font.BOLD, 25));
            welcomeLabel.setBounds(20, 50, 500, 30);

            nameLabel = new Label("Name : ");
            nameLabel.setFont(new Font("Lucida", Font.BOLD, 20));
            nameLabel.setBounds(20, 150, 200, 30);

            name = new TextField();
            name.setText(userData[0]);
            name.setFont(new Font("Lucida", Font.BOLD, 20));
            name.setBounds(220, 150, 200, 30);

            usernameLabel = new Label("Username : ");
            usernameLabel.setFont(new Font("Lucida", Font.BOLD, 20));
            usernameLabel.setBounds(20, 200, 200, 30);

            username = new TextField();
            username.setText(userData[1]);
            username.setFont(new Font("Lucida", Font.BOLD, 20));
            username.setBounds(220, 200, 200, 30);

            passwordLabel = new Label("Password : ");
            passwordLabel.setFont(new Font("Lucida", Font.BOLD, 20));
            passwordLabel.setBounds(20, 250, 200, 30);

            password = new TextField();
            password.setText(userData[2]);
            password.setFont(new Font("Lucida", Font.BOLD, 20));
            password.setBounds(220, 250, 200, 30);

            userTypeLabel = new Label("UserType : ");
            userTypeLabel.setFont(new Font("Lucida", Font.BOLD, 20));
            userTypeLabel.setBounds(20, 300, 200, 30);

            userType = new CheckboxGroup();

            teacher = new Checkbox("Teacher", userType, false);
            teacher.setFont(new Font("Lucida",Font.BOLD,20));
            teacher.setBounds(220,300,120,30);

            student = new Checkbox("Student", userType, false);
            student.setFont(new Font("Lucida",Font.BOLD,20));
            student.setBounds(350,300,120,30);

            if(userData[3].equals("Student"))       student.setState(true);
            else                                    teacher.setState(true);

            platformLabel = new Label("UserType : ");
            platformLabel.setFont(new Font("Lucida", Font.BOLD, 20));
            platformLabel.setBounds(20, 350, 200, 30);

            platform = new CheckboxGroup();

            msteams = new Checkbox("MS Teams", platform, false);
            msteams.setFont(new Font("Lucida",Font.BOLD,20));
            msteams.setBounds(220,350,120,30);

            gmeet = new Checkbox("GMeet", platform, false);
            gmeet.setFont(new Font("Lucida",Font.BOLD,20));
            gmeet.setBounds(350,350,120,30);

            if(userData[4].equals("MS Teams"))       msteams.setState(true);
            else                                     gmeet.setState(true);

            modifyButton = new Button("Update");
            modifyButton.setFont(new Font("Lucida", Font.BOLD, 20));
            modifyButton.setBounds(200, 400, 300, 30);
            modifyButton.addActionListener(this);

            temp = new Label("");

            modifyUserDataFrame.add(welcomeLabel);
            modifyUserDataFrame.add(nameLabel);
            modifyUserDataFrame.add(name);
            modifyUserDataFrame.add(usernameLabel);
            modifyUserDataFrame.add(username);
            modifyUserDataFrame.add(passwordLabel);
            modifyUserDataFrame.add(password);
            modifyUserDataFrame.add(userTypeLabel);
            modifyUserDataFrame.add(teacher);
            modifyUserDataFrame.add(student);
            modifyUserDataFrame.add(platformLabel);
            modifyUserDataFrame.add(msteams);
            modifyUserDataFrame.add(gmeet);
            modifyUserDataFrame.add(modifyButton);
            modifyUserDataFrame.add(temp);

            modifyUserDataFrame.setVisible(true);
            modifyUserDataFrame.setSize(700, 500);
            modifyUserDataFrame.addWindowListener (new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    modifyUserDataFrame.dispose();
                }
            });
        }
        if (e.getSource() == timetableButton) {
            JFrame timetableFrame = new JFrame("Online Education Suite");
            timetable = new Database().getTimetable();
            String[] columnName = new String[] {"Subject", "Day", "Start Time Hour", "Start Time Minute", "End Time Hour", "End Time Minute"};
            timetableTable = new JTable(timetable, columnName);
            timetableTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane sp=new JScrollPane(timetableTable);
            //timetableTable.setFont(new Font("Lucida", Font.BOLD, 20));
            //timetableTable.setBounds(0, 500, 700, 300);
            timetableFrame.add(sp);
            timetableFrame.setVisible(true);
            timetableFrame.setSize(700, 500);
        }
        if (e.getSource() == addTimetableButton) {
            new NewUser().timetableScreen();
        }
    }
}
