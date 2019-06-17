package Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drone {
    //region FIELDS
    private int id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleBooleanProperty available;
    //endregion

    //region CONSTRUCTOR

    public Drone(int id, String name, String description, Boolean available) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.available = new SimpleBooleanProperty(available);
    }

    public Drone(int id, String name, String description){
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }
    //endregion

    //region GetterSetter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getDescription() {
        return description.getValue();
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public String getAvailable() {
        return available.getValue() ? "yes" : "no";
    }
    public Boolean getAvailableBoolean(){
        return available.getValue();
    }

    public void setAvailable(Boolean available) {
        this.available.setValue(available);
    }

    //endregion
}
