package edu.gemini.model;


public abstract class AbstractAstronomicalData {
    private String fileType;
    private String fileQuality;
    private String colorType;
    private double contrast;
    private double brightness;
    private double saturation;
    private double highlights;
    private double exposure;
    private double shadows;
    private double whites;
    private double blacks;
    private double luminance;

    //Constructors
    public AbstractAstronomicalData(
    String fileType,
    String fileQuality,
    String colorType,
    double contrast,
    double brightness,
    double saturation,
    double highlights,
    double exposure,
    double shadows,
    double whites,
    double blacks,
    double luminance 
    ){
        this.fileType = fileType;
        this.fileQuality = fileQuality;
        this.colorType = colorType; 
        this.contrast = contrast;
        this.brightness = brightness;
        this.saturation = saturation;
        this.highlights = highlights;
        this.exposure = exposure;
        this.shadows = shadows;
        this.whites = whites;
        this.blacks = blacks;
        this.luminance = luminance;
    }

    //Getters
    public String getfileType(){return fileType;}
    public String getfileQuality(){return fileQuality;}
    public String getColorType(){return colorType;}
    public double getContrast(){return contrast;}
    public double getBrightness(){return brightness;}
    public double getSaturation(){return saturation;}
    public double getHighlights(){return highlights;}
    public double getExposure(){return exposure;}
    public double getShadows(){return shadows;}
    public double getWhites(){return whites;}
    public double getBlacks(){return blacks;}
    public double getLuminance(){return luminance;}

    public void setfileType(String fileType) { this.fileType = fileType; }
    public void setfileQuality(String fileQuality) { this.fileQuality = fileQuality; }
    public void setColorType(String colorType) { this.colorType = colorType; }
    public void setContrast(double contrast) { this.contrast = contrast; }
    public void setBrightness(double brightness) { this.brightness = brightness; }
    public void setSaturation(double saturation) { this.saturation = saturation; }
    public void setHighlights(double highlights) { this.highlights = highlights; }
    public void setExposure(double exposure) { this.exposure = exposure; }
    public void setShadows(double shadows) { this.shadows = shadows; }
    public void setWhites(double whites) { this.whites = whites; }
    public void setBlacks(double blacks) { this.blacks = blacks; }
    public void setLuminance(double luminance) { this.luminance = luminance; }

}
