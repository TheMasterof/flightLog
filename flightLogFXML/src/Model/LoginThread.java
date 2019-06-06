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
        System.out.println("Login Thread started");

        String sql = "SELECT COUNT(*) FROM user WHERE id = username";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                setChanged();
                notifyObservers(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(false);
    }

    public void setLoginData(String username, String password){
        this.username = username;
        this.password = password;
    }
}
