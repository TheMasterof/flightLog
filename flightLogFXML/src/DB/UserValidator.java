package DB;

import java.sql.*;

public class UserValidator {
    //region FIELDS
    private static UserValidator instance;
    private Connection connection;
    //endregion

    private UserValidator(){
        instance = new UserValidator();
    }
    public static UserValidator getInstance(){
        if(instance == null)instance = new UserValidator();
        return instance;
    }

    public Boolean checkUser(String username, String password){
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://192.168.99.100:3306/mydb", "user", "passme");
            System.out.println("Connection OK!");
        } catch (SQLException e) {
            System.out.println("Verbindung zur DB nicht möglich");
            e.printStackTrace();
        }
        String sql = "SELECT COUNT(*) FROM user WHERE id = username";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
