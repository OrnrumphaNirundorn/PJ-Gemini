package edu.gemini.model;

public abstract class AbstractAstronomer extends Person {
    private int id;
    private String institution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
