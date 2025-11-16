package gemini;

import gemini.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OCS implements GeminiAPI<SciencePlan, ObservingProgram, ObservingProgramConfigs,
        AstronomicalData, Astronomer, ScienceObserver> {

    @Override
    public ArrayList<SciencePlan> getAllSciencePlans() { return null; }

    @Override
    public SciencePlan getSciencePlanByNo(int planNo) { return null; }

    @Override
    public String createSciencePlan(SciencePlan sciencePlan, Astronomer an) {
        return null;
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
