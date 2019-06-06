package Model;

import javafx.beans.InvalidationListener;

import java.sql.*;
import java.util.Observable;

public class LoginThread extends Observable implements Runnable {
    //region FIELDS
    private static LoginThread instance;
    private Connection connection;
    private String username;
    private String password;
    //endregion

    private LoginThread(){
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://192.168.99.100:3306/mydb", "user", "passme");
            System.out.println("Connection OK!");
        } catch (SQLException e) {
            System.out.println("Verbindung zur DB nicht möglich");
            setChanged();
            notifyObservers(false);
            e.printStackTrace();
        }
    }

    public static LoginThread getInstance(){
        if(instance == null) instance = new LoginThread();
        return instance;
    }

    @Override
    public void run() {

        String sql = "SELECT COUNT(*) FROM user WHERE id LIKE '" + username + "'";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if(rs.getInt(1) != 0){
                setChanged();
                notifyObservers(true);
            }
            else{
                setChanged();
                notifyObservers(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLoginData(String username, String password){
        this.username = username;
        this.password = password;
    }
}
