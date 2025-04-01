package gr.ihu.flags.model;

import java.io.Serializable;



public class Photo implements Serializable {
    private String name;
    private int id;

    private String continent;

    public Photo(String name, int id, String continent) {
        this.name = name;
        this.id = id;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getContinent() {
        return continent;
    }
}

