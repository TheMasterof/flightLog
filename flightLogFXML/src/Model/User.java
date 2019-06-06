package Model;

public class User {
    //region FIELDS
    private String id;
    private String name;
    private String description;
    //endregion

    //region CONSTRUCTOR
    public User(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    //endregion

    //region GetterSetter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion
}
