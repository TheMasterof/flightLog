package DB;

import Model.LogEntry;

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


    public void addLogEntry(){
        //TODO
    }

    public List<LogEntry> getLogEntriesOfDrone(int droneID){
        //TODO
        return null;
    }

}
