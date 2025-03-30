package gr.ihu.flags.model;

public class photo {
    private String name;
    private int id;

    public photo( String name,int id ) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
