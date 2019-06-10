package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogEntry {
    //region fields
    private int id;
    private int droneID;
    private String userID;
    private String description;
    private Timestamp timeOfFlight;
    //endregion

    //region constructor
    public LogEntry(int droneID, String userID, String description, Timestamp timeOfFlight) {
        this.userID = userID;
        this.timeOfFlight = timeOfFlight;
        this.droneID = droneID;
        this.description = description;
    }
    public LogEntry(int id, int droneID, String userID, String description, Timestamp timeOfFlight){
        this.timeOfFlight = timeOfFlight;
        this.userID = userID;
        this.droneID = droneID;
        this.description = description;
        this.id = id;
    }
    //endregion

    //region GetterSetter
    public Timestamp getTimeOfFlight() {
        return timeOfFlight;
    }

    public void setTimeOfFlight(Timestamp timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
    }

    public int getDroneID() {
        return droneID;
    }

    public void setDroneID(int droneID) {
        this.droneID = droneID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    //endregion


    @Override
    public String toString() {
        return "LogEntry{" +
                "timeOfFlight=" + timeOfFlight +
                ", droneID=" + droneID +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
