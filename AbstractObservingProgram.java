package edu.gemini.model;

public abstract class AbstractObservingProgram {

    private String geminiLocation;
    private String opticsPrimary;
    private double fStop;
    private double opticsSecondaryRMS;

    // Constructor
    public AbstractObservingProgram(String geminiLocation, String opticsPrimary, double fStop, double opticsSecondaryRMS) {
        this.geminiLocation = geminiLocation;
        this.opticsPrimary = opticsPrimary;
        this.fStop = fStop;
        this.opticsSecondaryRMS = opticsSecondaryRMS;
    }

    // Getters
    public String getGeminiLocation() {
        return geminiLocation;
    }  

    public String getOpticsPrimary() {
        return opticsPrimary;
    }

    public double getFStop() {
        return fStop;
    }

    public double getOpticsSecondaryRMS() {
        return opticsSecondaryRMS;
    }
    
    // Setters
    public void setGeminiLocation(String geminiLocation) {
        this.geminiLocation = geminiLocation;
    }

    public void setOpticsPrimary(String opticsPrimary) {
        this.opticsPrimary = opticsPrimary;
    }
    public void setFStop(double fStop) {
        this.fStop = fStop;
    }
    public void setOpticsSecondaryRMS(double opticsSecondaryRMS) {
        this.opticsSecondaryRMS = opticsSecondaryRMS;
    }
}

    
    


