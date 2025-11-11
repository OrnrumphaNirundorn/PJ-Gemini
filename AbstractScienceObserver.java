package edu.gemini.model;

public abstract class AbstractScienceObserver extends Person {
    private int id;
    private String department;
    
    
    // Constructor
    public AbstractScienceObserver(
        String firstName, 
        String lastName, 
        String address, 
        String email, 
        int id, 
        String department
    ) {
       //call person class
        super(firstName, lastName, address, email); 
        
    
        this.id = id;
        this.department = department;
    }

    //Getters
    public int getId() { return id; }
    public String getDepartment() { return department; }

    //Setters
    public void setId(int id) { this.id = id; }
    public void setDepartment(String department) { this.department = department; }

}