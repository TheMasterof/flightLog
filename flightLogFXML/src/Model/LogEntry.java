package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogEntry {
    //region fields
    private Timestamp timeOfFlight;
    private int droneID;
    private int id;
    private String description;
    //endregion

    //region constructor
    public LogEntry(Timestamp timeOfFlight, int droneID, String description) {
        this.timeOfFlight = timeOfFlight;
        this.droneID = droneID;
        this.description = description;
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

}
