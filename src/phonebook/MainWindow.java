package phonebook;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Госпожа Пресс-Служба on 26.02.2016.
 */
public class MainWindow extends JFrame {
    JFrame frame;
    MyDialog mydialog;

    public MainWindow() throws SQLException, ClassNotFoundException {
        frame = this;
        setName("Phonebook");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 150, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.setVisible(true);

        panel.add(new JLabel("What should I do?"));
        panel.add(new MyButton("Create"));
        panel.add(new MyButton("Remove"));
        panel.add(new MyButton("Show all"));
        panel.add(new MyButton("Search by phone"));
        panel.add(new MyButton("Search by name"));
        panel.add(new MyButton("Change"));
        panel.add(new MyButton("Exit"));

        add(panel);

        setVisible(true);
    }

    class MyButton extends JButton {
        public MyButton(String name) {
            setText(name);
            addActionListener(e -> {
                if (name.equals("Exit")) {
                    System.exit(0);
                } else {
                    try {
                        mydialog = new MyDialog(frame, name);
                    } catch (SQLException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }
}


