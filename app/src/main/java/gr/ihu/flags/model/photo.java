package gr.ihu.flags.model;

public class photo {
    private String name;
    private int id;

    private String continent;

    public photo( String name,int id, String continent ) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }
    public int getId() {
        return id;
    }
}
