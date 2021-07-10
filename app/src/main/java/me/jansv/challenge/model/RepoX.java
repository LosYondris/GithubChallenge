package me.jansv.challenge.model;

public class RepoX {

    private String name;
    private String full_name;

    public RepoX(String name, String full_name) {
        this.name = name;
        this.full_name = full_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
