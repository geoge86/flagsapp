package gr.ihu.flags.model;

import java.io.Serializable;



public class Photo implements Serializable {
    private String name;
    private int id;

    private String type;

    public Photo(String name, int id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}

