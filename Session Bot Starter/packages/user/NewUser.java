package packages.user;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import packages.database.Database;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewUser extends Applet implements ActionListener {

    public String[] userData = new String[5];
    public String[] timetable = new String[6];

    JFrame regScreen, timetableScreen;
    Label heading, nameLabel, userTypeLabel, sessionPlatformLabel, usernameLabel, passwordLabel, subjectLabel, dayLabel, startTimeLabel, endTimeLabel, temp;
    CheckboxGroup userType, sessionType;
    Checkbox teacher, student, msteams, gmeet;
    TextField name, username, password, subject, startTime, endTime;
    Button buttonVitStudent, buttonGmail, buttonCreate, buttonTimetable, buttonClose;
    Choice day;

    public NewUser() {
        timetable = new String[]{"", "", "", "", "", ""};
        userData = new String[]{"", "", "", "", ""};
    }

    public void registrationScreen() {
        System.out.println("\tNo user found. Creating a new one...");
        regScreen = new JFrame("Online Education Hack Suite");

        heading = new Label("Welcome to Online Education Hack Suite\");\n");
        heading.setFont(new Font("Lucida",Font.BOLD,30));
        heading.setBounds(100,50,500,30);

        nameLabel = new Label("\t\t\t\t\t\tName :");
        nameLabel.setFont(new Font("Lucida",Font.BOLD,20));
        nameLabel.setBounds(140,100,80,30);

        name = new TextField(120);
        name.setFont(new Font("Lucida",Font.BOLD,20));
        name.setBounds(220,100,300,30);

        userTypeLabel = new Label("\t\t\t\t\t\tUser Type :");
        userTypeLabel.setFont(new Font("Lucida",Font.BOLD,20));
        userTypeLabel.setBounds(140,150,120,30);

        userType = new CheckboxGroup();

        teacher = new Checkbox("Teacher", userType, false);
        teacher.setFont(new Font("Lucida",Font.BOLD,20));
        teacher.setBounds(270,150,120,30);

        student = new Checkbox("Student", userType, false);
        student.setFont(new Font("Lucida",Font.BOLD,20));
        student.setBounds(400,150,120,30);

        sessionPlatformLabel = new Label("Session Platform :");
        sessionPlatformLabel.setFont(new Font("Lucida",Font.BOLD,20));
        sessionPlatformLabel.setBounds(100,200,200,30);

        sessionType = new CheckboxGroup();

        msteams = new Checkbox("MS Teams", sessionType, false);
        msteams.setFont(new Font("Lucida",Font.BOLD,20));
        msteams.setBounds(300,200,120,30);

        gmeet = new Checkbox("GMeet", sessionType, false);
        gmeet.setFont(new Font("Lucida",Font.BOLD,20));
        gmeet.setBounds(450,200,120,30);


        usernameLabel = new Label("Email ID :");
        usernameLabel.setFont(new Font("Lucida",Font.BOLD,20));
        usernameLabel.setBounds(100,250,110,30);

        username = new TextField(120);
        username.setFont(new Font("Lucida",Font.BOLD,20));
        username.setBounds(210,250,300,30);

        buttonVitStudent = new Button("@vitstudent.ac.in");
        buttonVitStudent.setFont(new Font("Lucida",Font.BOLD,20));
        buttonVitStudent.setBounds(100,300,220,30);
        buttonVitStudent.addActionListener(this);

        buttonGmail = new Button("@gmail.com");
        buttonGmail.setFont(new Font("Lucida",Font.BOLD,20));
        buttonGmail.setBounds(350,300,150,30);
        buttonGmail.addActionListener(this);

        passwordLabel = new Label("Password :");
        passwordLabel.setFont(new Font("Lucida",Font.BOLD,20));
        passwordLabel.setBounds(100,350,110,30);

        password = new TextField(120);
        password.setFont(new Font("Lucida",Font.BOLD,20));
        password.setBounds(210,350,300,30);


        buttonCreate = new Button("Create New Account");
        buttonCreate.setFont(new Font("Lucida",Font.BOLD,20));
        buttonCreate.setBounds(220,400,250,30);
        buttonCreate.addActionListener(this);

        temp = new Label("");

        regScreen.add(heading);
        regScreen.add(nameLabel);
        regScreen.add(name);
        regScreen.add(userTypeLabel);
        regScreen.add(teacher);
        regScreen.add(student);
        regScreen.add(sessionPlatformLabel);
        regScreen.add(msteams);
        regScreen.add(gmeet);
        regScreen.add(usernameLabel);
        regScreen.add(username);
        regScreen.add(buttonVitStudent);
        regScreen.add(buttonGmail);
        regScreen.add(passwordLabel);
        regScreen.add(password);
        regScreen.add(buttonCreate);
        regScreen.add(temp);
        regScreen.setVisible(true);
        regScreen.setSize(700, 500);

        regScreen.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                regScreen.dispose();
            }
        });
    }

    public void timetableScreen() {
        System.out.println("\tNo Timetable found...");
        timetableScreen = new JFrame("Online Education Hack Suite");

        heading = new Label("Timetable");
        heading.setFont(new Font("Lucida",Font.BOLD,30));
        heading.setBounds(250,50,500,30);

        subjectLabel = new Label("Subject :");
        subjectLabel.setFont(new Font("Lucida",Font.BOLD,20));
        subjectLabel.setBounds(20,100,130,30);

        subject = new TextField(120);
        subject.setText("Subject 1");
        subject.setFont(new Font("Lucida",Font.BOLD,20));
        subject.setBounds(150,100,300,30);

        dayLabel = new Label("Day :");
        dayLabel.setFont(new Font("Lucida",Font.BOLD,20));
        dayLabel.setBounds(20,150,130,30);

        day = new Choice();
        day.add("Sunday");
        day.add("Monday");
        day.add("Tuesday");
        day.add("Wednesday");
        day.add("Thursday");
        day.add("Friday");
        day.add("Saturday");
        day.setFont(new Font("Lucida",Font.BOLD,20));
        day.setBounds(150,150,130,30);

        startTimeLabel = new Label("Start Time:");
        startTimeLabel.setFont(new Font("Lucida",Font.BOLD,20));
        startTimeLabel.setBounds(20,200,130,30);

        startTime = new TextField(120);
        startTime.setText("13:00");
        startTime.setFont(new Font("Lucida",Font.BOLD,20));
        startTime.setBounds(150,200,300,30);

        endTimeLabel = new Label("End Time:");
        endTimeLabel.setFont(new Font("Lucida",Font.BOLD,20));
        endTimeLabel.setBounds(20,250,130,30);

        endTime = new TextField(120);
        endTime.setText("16:00");
        endTime.setFont(new Font("Lucida",Font.BOLD,20));
        endTime.setBounds(150,250,300,30);

        buttonTimetable = new Button("Add");
        buttonTimetable.setFont(new Font("Lucida",Font.BOLD,20));
        buttonTimetable.setBounds(150,300,80,30);
        buttonTimetable.addActionListener(this);

        buttonClose = new Button("Close");
        buttonClose.setFont(new Font("Lucida",Font.BOLD,20));
        buttonClose.setBounds(250,300,80,30);
        buttonClose.addActionListener(this);

        temp = new Label("");

        timetableScreen.add(heading);
        timetableScreen.add(subjectLabel);
        timetableScreen.add(subject);
        timetableScreen.add(dayLabel);
        timetableScreen.add(day);
        timetableScreen.add(startTimeLabel);
        timetableScreen.add(startTime);
        timetableScreen.add(endTimeLabel);
        timetableScreen.add(endTime);
        timetableScreen.add(buttonTimetable);
        timetableScreen.add(buttonClose);
        timetableScreen.add(temp);

        timetableScreen.setVisible(true);
        timetableScreen.setSize(700, 500);
        timetableScreen.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                timetableScreen.dispose();
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonVitStudent)
            username.setText(username.getText() + "@vitstudent.ac.in");
        if (e.getSource() == buttonGmail)
            username.setText(username.getText() + "@gmail.com");
        if (e.getSource() == buttonCreate) {
            userData[0] = name.getText();
            userData[1] = username.getText();
            userData[2] = password.getText();
            userData[3] = "Teacher";
            if(student.getState())      userData[3] = "Student";
            userData[4] = "MS Teams";
            if(gmeet.getState())      userData[4] = "GMeet";

            System.out.println("\tNew Account Created");
            regScreen.dispose();
        }
        if (e.getSource() == buttonTimetable) {
            timetable[0] = subject.getText();
            timetable[1] = String.valueOf(day.getSelectedIndex());
            String startTimeText = startTime.getText();
            String[] startTimeTemp = startTimeText.split(":");
            timetable[2] = startTimeTemp[0];
            timetable[3] = startTimeTemp[1];
            String endTimeText = endTime.getText();
            String[] endTimeTemp = endTimeText.split(":");
            timetable[4] = endTimeTemp[0];
            timetable[5] = endTimeTemp[1];

            for (int i=0; i<6; i++) {
                System.out.println(timetable[i]);
            }
            subject.setText("");
            startTime.setText("");
            endTime.setText("");

            Database db = new Database();
            db.updateTimetable(timetable);
        }
    }
}
