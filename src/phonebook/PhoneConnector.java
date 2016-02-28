package phonebook;

import com.sun.istack.internal.Nullable;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ������� �����-������ on 02.06.2015.
 */
public class PhoneConnector {

    private static final String IP = "localhost";
    private static final String PORT = "3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + IP + ":" + PORT + "/test",
                USERNAME, PASSWORD);
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void insert(String name, String phone, @Nullable String email) throws SQLException {
        String query = "INSERT INTO fuckingtable" + "(name,phone,email) VALUES ('" + name + "', '" + phone + "', '" + email + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        String date = new java.util.Date().toString();
        String query2 = "INSERT INTO log" + "(date,message) VALUE ('" + date + "','Insert')";

        Statement statement2 = connection.createStatement();
        statement2.executeUpdate(query2);
        statement.close();
        statement2.close();
    }

    public String selectByPhone(String phone) throws SQLException {
        String query = "SELECT * FROM fuckingtable WHERE phone ='" + phone + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        result.next();
        String thing = result.getString("name") + " " + result.getString("phone") + " " + result.getString("email");
        statement.close();

        return thing;

    }

    public ArrayList<String> selectByName(String name) throws SQLException {
        String query = "SELECT * FROM fuckingtable WHERE name ='" + name + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        ArrayList<String> arrayList = new ArrayList<>();
        String text = "";
        while (result.next()) {
            text = result.getString("name") + " " + result.getString("phone") + " " + result.getString("email");
            arrayList.add(text);
        }
        statement.close();
        return arrayList;
    }

    public ArrayList<String> getAll() throws SQLException {

        String query = "SELECT * FROM fuckingtable";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        ArrayList<String> arrayList = new ArrayList<>();
        String text;
        while (result.next()) {
            text = result.getString("name") + " " + result.getString("phone") + " " + result.getString("email");
            arrayList.add(text);
        }
        statement.close();
        return arrayList;

    }

    public boolean getLogin(String login, String password) throws SQLException {
        String query = "SELECT login AND password FROM users WHERE login ='" + login + "' AND password='" + password + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {

            statement.close();
            return true;
        } else statement.close();
        return false;

    }

    public void remove(String phone, String name, String email) throws SQLException {
        String query = "DELETE FROM fuckingtable WHERE";
        if (!phone.equals("")) {
            query += " phone='" + phone + "'";
        }
        if (!name.equals("")) {
            query += " name = '" + name + "'";
        }
        if (!email.equals("")) {
            query += " email = '" + email + "'";
        }
        Statement statement = connection.createStatement();
        statement.execute(query);

        statement.close();

    }

    public void updateByPhone(String newPhone, String newName, String newEmail) throws SQLException {
        String queryHEAD = "UPDATE fuckingtable";

        String queryEnd = " WHERE phone = '" + newPhone + "'";
        String queryMid = "";
        if (newName != null) {

            queryMid += " name = '" + newName + "'";
        }
        if (newEmail != null) {
            queryHEAD += " SET ";
            queryMid += ", email = '" + newEmail + "'";

        }
        Statement statement = connection.createStatement();
        statement.execute(queryHEAD + queryMid + queryEnd);
    }
}

