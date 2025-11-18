package edu.gemini;

import edu.gemini.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OCS implements GeminiAPI<SciencePlan, ObservingProgram, ObservingProgramConfigs,
        AstronomicalData, Astronomer, ScienceObserver> {

    private final ArrayList<SciencePlan> sciencePlans = new ArrayList<>();
    private static int planIdCounter = 1000;

    @Override
    public ArrayList<SciencePlan> getAllSciencePlans() { return sciencePlans; }

    @Override
    public SciencePlan getSciencePlanByNo(int planNo) { 
        for (SciencePlan plan : sciencePlans) {
            if (plan.getPlanNo() == planNo) {
                return plan;
            }
        }
        return null;
    }

    @Override
    public String createSciencePlan(SciencePlan sciencePlan, Astronomer an) {
        // Validate that astronomer is set
        if (an == null) {
            return "Error: Astronomer information is required.";
        }

        // Validate required fields
        if (sciencePlan.getPlanName() == null || sciencePlan.getPlanName().trim().isEmpty()) {
            return "Please complete all required fields before saving the science plan.";
        }

        if (sciencePlan.getObjective() == null || sciencePlan.getObjective().trim().isEmpty()) {
            return "Please complete all required fields before saving the science plan.";
        }

        if (sciencePlan.getFunding() < 0) {
            return "Funding amount must be a positive numerical value.";
        }

        if (sciencePlan.getStartDate() == null || sciencePlan.getEndDate() == null) {
            return "Please complete all required fields before saving the science plan.";
        }

        // Validate start date is not after end date
        if (sciencePlan.getStartDate().isAfter(sciencePlan.getEndDate())) {
            return "Start date cannot be after the end date.";
        }

        // Validate telescope assignment
        if (sciencePlan.getTelescope() == null || sciencePlan.getTelescope().trim().isEmpty()) {
            return "Please complete all required fields before saving the science plan.";
        }

        // Validate telescope
        String telescope = sciencePlan.getTelescope();
        if (telescope != null) {
            String t = telescope.trim().toLowerCase();
            boolean validTelescope = t.equals("hawaii") || t.equals("gemini north") ||
                t.equals("gemini-north") || t.equals("gemininorth") ||
                t.equals("chile") || t.equals("gemini south") ||
                t.equals("gemini-south") || t.equals("geminisouth") ||
                t.equals("vietnam");
            if (!validTelescope) {
                return "Invalid telescope selected. Must be Hawaii or Chile.";
            }
        } else {
            return "Please complete all required fields before saving the science plan.";
        }

        // Validate target is available
        if (sciencePlan.getTarget() == null) {
            return "Selected target not found in the star catalogue. Please choose a valid target.";
        }

        // Check for duplicate plan name
        boolean nameExists = false;
        for (SciencePlan plan : sciencePlans) {
            if (plan.getPlanName().equalsIgnoreCase(sciencePlan.getPlanName())) {
                nameExists = true;
                break;
            }
        }
        if (nameExists) {
            return "A science plan with this name already exists. Please use a different plan name.";
        }

        // Check for schedule conflicts
        boolean conflict = false;
        for (SciencePlan existingPlan : sciencePlans) {
            if (existingPlan.getTelescope().equalsIgnoreCase(sciencePlan.getTelescope())) {
                // Check if date ranges overlap
                if (!(sciencePlan.getEndDate().isBefore(existingPlan.getStartDate()) || 
                      sciencePlan.getStartDate().isAfter(existingPlan.getEndDate()))) {
                    conflict = true;
                    break;
                }
            }
        }
        if (conflict) {
            return "Selected date range conflicts with another scheduled plan for this telescope.";
        }

        // Set plan ID if not already set
        if (sciencePlan.getPlanNo() <= 0) {
            sciencePlan.setPlanNo(planIdCounter++);
        }

        // Set creator if not already set
        if (sciencePlan.getCreator() == null) {
            sciencePlan.setCreator(an);
        }

        // Set default status
        if (sciencePlan.getStatus() == null) {
            sciencePlan.setStatus(AbstractSciencePlan.STATUS.SAVED);
        }

        // Add to collection
        sciencePlans.add(sciencePlan);

        return "Science plan created successfully with Plan ID: " + sciencePlan.getPlanNo();
    }

    @Override
    public String submitSciencePlan(SciencePlan sciencePlan, Astronomer an) { return null; }

    @Override
    public boolean updateSciencePlanStatus(int planno, AbstractSciencePlan.STATUS stssp) {
        return false;
    }

    @Override
    public String testSciencePlan(SciencePlan sciencePlan) {
        return null;
    }

    @Override
    public void deleteAllSciencePlans() {

    }

    @Override
    public boolean deleteSciencePlanByNo(int planNo) {
        return false;
    }

    @Override
    public String addUnavailableDate(Date datevalue) {
        return null;
    }

    @Override
    public String deleteUnavailableDate(Date datevalue) {
        return null;
    }

    @Override
    public ArrayList<Date> getAllObservationSchedule() {
        return null;
    }

    @Override
    public AstronomicalData getAstronomicalData(SciencePlan sciencePlan) throws IOException {
        return null;
    }

    @Override
    public AstronomicalData removeAstronomicalData(SciencePlan sciencePlan, int index) throws IOException {
        return null;
    }

    @Override
    public String accessTelescopeLiveView() throws IOException {
        return null;
    }

    @Override
    public String executeCommand(String command) {
        return null;
    }

    @Override
    public String getConfigurations() {
        return null;
    }

    @Override
    public boolean addConfiguration(String confFilePath) {
        return false;
    }

    @Override
    public boolean removeConfiguration(int confNo) {
        return false;
    }

    @Override
    public AbstractObservingProgramConfigs.FoldMirrorType[] getFoldMirrorTypes() {
        return new AbstractObservingProgramConfigs.FoldMirrorType[0];
    }

    @Override
    public AbstractObservingProgramConfigs.CalibrationUnit[] getCalibrationUnits() {
        return new AbstractObservingProgramConfigs.CalibrationUnit[0];
    }

    @Override
    public AbstractObservingProgramConfigs.LightType[] getLightTypes() {
        return new AbstractObservingProgramConfigs.LightType[0];
    }

    @Override
    public ObservingProgram createObservingProgram(AbstractSciencePlan sp, String opticsPrimary, double fStop, double opticsSecondaryRMS, double scienceFoldMirrorDegree, ObservingProgramConfigs.FoldMirrorType scienceFoldMirrorType, int moduleContent, ObservingProgramConfigs.CalibrationUnit calibrationUnit, ObservingProgramConfigs.LightType lightType, AbstractTelePositionPair[] telePositionPair, ScienceObserver so) {
        return null;
    }

    @Override
    public SciencePlan validateSciencePlan(AbstractSciencePlan sp, ScienceObserver so) {
        boolean isPlanComplete = sp.getPlanNo() > 0
                && sp.getPlanName() != null
                && sp.getCreator() != null
                && sp.getFunding() != 0
                && sp.getObjective() != null;

        boolean isLocationMatch = switch (sp.getTarget().getQuadrant()) {
        
            case NQ1, NQ2, NQ3, NQ4 -> sp.getTelescope().equals("Hawaii") || sp.getTelescope().equals("Gemini North");
            case SQ1, SQ2, SQ3, SQ4 -> sp.getTelescope().equals("Chile") || sp.getTelescope().equals("Gemini South");
            
            
            default -> false;
        };

        boolean isDateMatch = sp.getStartDate().getMonthValue() <= sp.getTarget().getmonth()
                && sp.getEndDate().getMonthValue() >= sp.getTarget().getmonth();

        if (!isPlanComplete || !isLocationMatch || !isDateMatch) {
            sp.setStatus(AbstractSciencePlan.STATUS.INVALIDATED);
            System.out.println("Plan number: " + sp.getPlanNo() + " is rejected by " + so.getFirstName() + " " + so.getLastName());
            return (SciencePlan) sp;
        }

        LocalDateTime validatedDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        sp.setStatus(AbstractSciencePlan.STATUS.VALIDATED);
        System.out.println(formatter.format(validatedDateTime) + " Plan number: " + sp.getPlanNo() + " is validated by " + so.getFirstName() + " " + so.getLastName());
        return (SciencePlan) sp;
    }

    @Override
    public boolean saveObservingProgram(AbstractObservingProgram op) {
        return false;
    }

    @Override
    public ObservingProgram getObservingProgramBySciencePlan(AbstractSciencePlan sp) {
        return null;
    }

    @Override
    public void getDefaultConfiguration() throws IOException {

    }

    @Override
    public void getCurrentConfiguration() throws IOException {

    }

    @Override
    public String updateConfiguration() throws FileNotFoundException {
        return null;
    }
}
