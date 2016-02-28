package phonebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by ������� �����-������ on 09.06.2015.
 */
public class LoginForm extends JFrame{
    PhoneConnector connector = new PhoneConnector();
    public void buildGUI() throws SQLException, ClassNotFoundException {
        connector.connect();
        setTitle("Login, please");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 200);
        setLayout(new GridLayout(4,1));
        TextField login = new TextField("Login");
        TextField password = new TextField("Password");
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            try {
                if (connector.getLogin(login.getText(), password.getText())) {
                    connector.disconnect();
                    new MainWindow();
                    dispose();
                } else {
                    getContentPane().add(new JLabel("Incorrect. Try again"));
                }
            } catch (SQLException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        getContentPane().add(login);
        getContentPane().add(password);
        getContentPane().add(button);
pack();
        setVisible(true);


    }



}
