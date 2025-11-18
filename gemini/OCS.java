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
    public ObservingProgram createObservingProgram(
            AbstractSciencePlan sp,
            String opticsPrimary,
            double fStop,
            double opticsSecondaryRMS,
            double scienceFoldMirrorDegree,
            ObservingProgramConfigs.FoldMirrorType scienceFoldMirrorType,
            int moduleContent,
            ObservingProgramConfigs.CalibrationUnit calibrationUnit,
            ObservingProgramConfigs.LightType lightType,
            AbstractTelePositionPair[] telePositionPair,
            ScienceObserver so) {

        if (sp.getStatus() != AbstractSciencePlan.STATUS.VALIDATED) {
            System.out.println("Error: Observing Program creation failed. Science Plan " + sp.getPlanNo() + " must be VALIDATED first (Current status: " + sp.getStatus() + ").");
            return null;
        }

        String telescope = sp.getTelescope();
        boolean isNorth = telescope.equals("Hawaii") || telescope.equals("Gemini North");
        boolean isSouth = telescope.equals("Chile") || telescope.equals("Gemini South");
        String locationName = isNorth ? "Gemini North (Mauna Kea, Hawaii)" : "Gemini South (Cerro Pachón, Chile)";

        if (!isNorth && !isSouth) {
            System.out.println("Error: Science Plan " + sp.getPlanNo() + " has an unknown or unmapped telescope location: " + telescope);
            return null;
        }

        boolean isValid = true;
        StringBuilder validationMessages = new StringBuilder();

        String expectedOptics = isNorth ? "GNZ" : "GSZ";
        if (!opticsPrimary.equals(expectedOptics)) {
            isValid = false;
            validationMessages.append(" - Invalid Optics Primary '").append(opticsPrimary).append("'. Expected: ").append(expectedOptics).append(" for ").append(locationName).append(".\n");
        }

        double minFStop = isNorth ? 1.8 : 2.9;
        double maxFStop = isNorth ? 8.1 : 18.0;
        if (fStop < minFStop || fStop > maxFStop) {
            isValid = false;
            validationMessages.append(" - Invalid F-Stop ").append(fStop).append(". Expected range for ").append(opticsPrimary).append(": [").append(minFStop).append(", ").append(maxFStop).append("].\n");
        }

        double minRMS = 5.0;
        double maxRMS = isNorth ? 17.0 : 13.0;
        if (opticsSecondaryRMS < minRMS || opticsSecondaryRMS > maxRMS) {
            isValid = false;
            validationMessages.append(" - Invalid Optics Secondary RMS ").append(opticsSecondaryRMS).append(" nm. Expected range for ").append(locationName).append(": [").append(minRMS).append(", ").append(maxRMS).append("] nm.\n");
        }

        if (scienceFoldMirrorDegree < 30.0 || scienceFoldMirrorDegree > 45.0) {
            isValid = false;
            validationMessages.append(" - Invalid Science Fold Mirror Degree ").append(scienceFoldMirrorDegree).append(" degrees. Expected range: [30.0, 45.0].\n");
        }

        if (moduleContent < 1 || moduleContent > 4) {
            isValid = false;
            validationMessages.append(" - Invalid Module Content value ").append(moduleContent).append(". Expected values: 1, 2, 3, or 4.\n");
        }

        if (telePositionPair == null || telePositionPair.length == 0) {
            isValid = false;
            validationMessages.append(" - Tele Position Pair list is missing or empty. This is required for telescope movement.\n");
        }

        if (isValid) {
            ObservingProgramConfigs config = new ObservingProgramConfigs(scienceFoldMirrorDegree, scienceFoldMirrorType, calibrationUnit, lightType);
            TelePositionPair[] telePositionPairList = (TelePositionPair[]) telePositionPair;

            ObservingProgram op = new ObservingProgram(
                    sp.getTelescope(),
                    opticsPrimary,
                    fStop,
                    opticsSecondaryRMS,
                    config,
                    telePositionPairList);

            boolean saveSuccess = saveObservingProgram(op);

            LocalDateTime creationDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            if (saveSuccess) {
                sp.setStatus(AbstractSciencePlan.STATUS.RUNNING);
                System.out.println(formatter.format(creationDateTime) + " Observing Program successfully created and saved for Science Plan: " + sp.getPlanNo() + " by Observer: " + so.getFirstName() + " " + so.getLastName());
            } else {
                System.out.println(formatter.format(creationDateTime) + " Observing Program created but save to DB failed for Science Plan: " + sp.getPlanNo());
            }

            return op;

        } else {
            System.out.println("Observing Program creation failed for Science Plan " + sp.getPlanNo() + " due to invalid data:");
            System.out.println(validationMessages.toString());
            return null;
        }
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

        LocalDateTime validatedDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        if (!isPlanComplete || !isLocationMatch || !isDateMatch) {
            sp.setStatus(AbstractSciencePlan.STATUS.INVALIDATED);
            System.out.println("Display: Validate failed.");
            System.out.println("Saved log: " + formatter.format(validatedDateTime) + "Plan number: " + sp.getPlanNo() + " is rejected by " + so.getFirstName() + " " + so.getLastName());
            return (SciencePlan) sp;
        }

        sp.setStatus(AbstractSciencePlan.STATUS.VALIDATED);
        System.out.println("Display: " + "Validate Science Plan Succeed ID: " + sp.getPlanNo());
        System.out.println("Saved log: " + formatter.format(validatedDateTime) + " Plan number: " + sp.getPlanNo() + " is validated by " + so.getFirstName() + " " + so.getLastName());
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
