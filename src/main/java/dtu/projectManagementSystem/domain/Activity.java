package dtu.projectManagementSystem.domain;


public abstract class Activity { //Max-Peter Schrøder (s214238)
    private String name;
    private Integer id;

    public Activity(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public abstract String getTypeName() ;

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }


}
