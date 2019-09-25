package com.buildweek4.watermyplants.models;

public class ReturnPlant {

    private long id;
    private String species;
    private String name;
    private String location;
    private int schedule;


    public ReturnPlant(long id, String species, String name, String location, int schedule) {
        this.id = id;
        this.species = species;
        this.name = name;
        this.location = location;
        this.schedule = schedule;
    }

    public long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getSchedule() {
        return schedule;
    }

    public ReturnPlant() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }
}
