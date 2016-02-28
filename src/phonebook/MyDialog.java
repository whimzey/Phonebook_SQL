package phonebook;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Госпожа Пресс-Служба on 26.02.2016.
 */
public class MyDialog extends JDialog {
    private JPanel panel;
    private PhoneConnector connector;

    public MyDialog(JFrame parent, String title) throws SQLException, ClassNotFoundException {
        super(parent, title);
        connector = new PhoneConnector();
        connector.connect();
        setLocation(500, 200);
        setMinimumSize(new Dimension(200,200));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
setResizable(true);
        panel = new JPanel();

        switch (title) {
            case "Create":
                create();
                break;
            case "Change":
                change();
                break;
            case "Remove":
                remove();
                break;
            case "Search by phone":
                searchPhone();
                break;
            case "Search by name":
                searchName();
                break;
            case "Show all":
                showAll();
                break;
        }

    }

    private void showAll() throws SQLException {
        panel.setLayout(new GridLayout(1, 1));

        JLabel text = new JLabel();

        ArrayList<String> array = connector.getAll();
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");

        for (String anArray : array) {
            builder.append(anArray).append("<br>");
        }
        builder.append("</html>");
        text.setText(builder.toString());
        text.setVisible(true);
        panel.add(text);
        panel.setVisible(true);

        getContentPane().add(panel);
        pack();
    }

    private void searchName() {
        panel.setLayout(new GridLayout(5, 1));
        panel.add(new JLabel("Set name"));
        TextArea name = new TextArea();
        panel.add(name);
        JLabel text = new JLabel("");
        text.setVisible(true);
        panel.add(text);
        JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            try {
                ArrayList<String> array = connector.selectByName(name.getText());
                String anArrays = "";
                for (String anArray : array) {
                    anArrays += anArray + "\n";
                }
                text.setText(anArrays);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(button);
        panel.setVisible(true);
        setSize(300,300);
        add(panel);
    }

    private void searchPhone() {
        panel.setLayout(new GridLayout(5, 1));
        panel.add(new JLabel("Set phone"));
        TextArea phone = new TextArea();
        panel.add(phone);
        JLabel text = new JLabel("");
        text.setVisible(true);
        panel.add(text);
        JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            try {
                String anArrays = connector.selectByPhone(phone.getText());
                text.setText(anArrays);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(button);
        panel.setVisible(true);
        setSize(300,300);
        add(panel);

    }

    private void remove() {
        panel.setLayout(new GridLayout(7, 1));
        panel.add(new JLabel("Set name"));
        TextArea name = new TextArea();
        panel.add(name);
        panel.add(new JLabel("OR Set phone"));
        TextArea phone = new TextArea();
        panel.add(phone);
        panel.add(new JLabel("OR Set email"));
        TextArea email = new TextArea();
        panel.add(email);
        JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            try {
                connector.remove(phone.getText(), name.getText(), email.getText());
                connector.disconnect();
                dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(button);
        panel.setVisible(true);
        setSize(300,300);
        add(panel);
    }

    private void create() {
        panel.setLayout(new GridLayout(7, 1));
        panel.add(new JLabel("Set name"));
        TextArea name = new TextArea();
        panel.add(name);
        panel.add(new JLabel("Set phone"));
        TextArea phone = new TextArea();
        panel.add(phone);
        panel.add(new JLabel("Set email"));
        TextArea email = new TextArea();
        panel.add(email);
        JButton button = new JButton("Ok");

        button.addActionListener(e -> {
            try {
                connector.insert(name.getText(), phone.getText(), email.getText());
                connector.disconnect();
                dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(button);
        panel.setVisible(true);
        setSize(300,300);
        add(panel);
    }

    private void change() {
        panel.setLayout(new GridLayout(7, 1));

        panel.add(new JLabel("Set phone of contact, you want to change"));
        TextArea phone = new TextArea();
        panel.add(phone);
        panel.add(new JLabel("Set new name. Won't change if empty"));
        TextArea name = new TextArea();
        panel.add(name);
        panel.add(new JLabel("Set new email. Won't change if empty"));
        TextArea email = new TextArea();
        panel.add(email);
        JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            try {
                connector.updateByPhone(phone.getText(), name.getText(), email.getText());
                connector.disconnect();
                dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panel.add(button);
        panel.setVisible(true);
        setSize(300,300);
        add(panel);
    }

}
