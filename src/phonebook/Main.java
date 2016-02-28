package phonebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Created by ����� ������ on 18.04.2015.
 */
public class Main {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

    PhoneConnector connector = new PhoneConnector();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Main program = new Main();
        program.start();
    }

    public void start() throws SQLException, ClassNotFoundException {


       LoginForm login = new LoginForm();
      login.buildGUI();







    }
}
