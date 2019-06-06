package DB;

import Model.Drone;
import Model.LogEntry;
import Model.User;

import java.util.LinkedList;
import java.util.List;

public class Database {
    //region Fields
    private Database instance;
    private List<LogEntry> logs;
    //endregion

    //region Constructor
    private Database(){
        logs = new LinkedList<>();
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
