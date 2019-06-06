package DB;

import Model.Drone;
import Model.LogEntry;
import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Database {
    //region Fields
    private Database instance;
    private List<LogEntry> logs;
    Connection connection;
    //endregion

    //region Constructor
    private Database(){
        logs = new LinkedList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://192.168.99.100:3306/mydb", "user", "passme");
            System.out.println("Connection OK!");
        } catch (SQLException e) {
            System.out.println("Verbindung zur DB nicht möglich");
            e.printStackTrace();
        }
    }
        public Database getInstance(){
        if(instance == null)instance = new Database();
        return instance;
    }
    //endregion


    public void addLogEntry(LogEntry le){
        //TODO
    }

    public void addUser(User u){
        //TODO
    }

    public void addDrone(Drone d){
        //TODO
    }

    public List<LogEntry> getLogEntriesOfDrone(int droneID){
        //TODO
        return null;
    }

}
