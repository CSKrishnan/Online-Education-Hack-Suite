import packages.database.*;
import packages.user.*;

import static java.lang.Thread.sleep;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Database db = new Database();                       //Initialize Database
        String [] userData = db.getUserData();
        String [][] timetable = db.getTimetable();
        System.out.println(userData[0] + "gg");

        if(userData[1] == "") {
            NewUser newUser = new NewUser();
            newUser.registrationScreen();
            while(newUser.userData[0] == "") {
                sleep(2000);
            }
            db.setUserData(newUser.userData);
            db.update();
            userData = db.getUserData();
        }
        if(timetable[0][0] == "") {
            NewUser newUser = new NewUser();
            newUser.timetableScreen();
        }

        OldUser oldUser = new OldUser(userData);
        oldUser.welcomeScreen(userData);

    }
}
