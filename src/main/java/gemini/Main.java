package gemini;

import gemini.*;
import gemini.model.AbstractObservingProgramConfigs;
import gemini.model.AbstractSciencePlan;
import gemini.model.AbstractTelePositionPair;
import gemini.model.StarSystem;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Create Science Plan ---");
        testCreateSciencePlanNormalFlow();
        testCreateSciencePlanInvalidDateRange();
        testCreateSciencePlanMissingPlanName();
        testCreateSciencePlanMissingObjective();
        testCreateSciencePlanNegativeFunding();
        testCreateSciencePlanDuplicateName();
        testCreateSciencePlanScheduleConflict();
        testCreateMultiplePlansForDifferentTelescopes();
        testCreateSciencePlanMissingAstronomer();
        testCreateSciencePlanInvalidTelescope();
        testCreateSciencePlanAutomaticPlanID();

        System.out.println();

        System.out.println("--- Validate Science Plan ---");
        testValidateSciencePlanNormal();
        testValidateMissingFields();
        testValidateWrongLocation();
        testValidateWrongDateRange();

        System.out.println();

        System.out.println("--- Create Observing Program ---");
        testCreateObservingProgramSuccess();
        testCreateObservingProgramFailure1();
        testCreateObservingProgramFailure2();
        testCreateObservingProgramFailure3();
        testCreateObservingProgramFailure4();
        testCreateObservingProgramFailure5();

    }

    private static Astronomer sampleAstronomer() {
        Astronomer a = new Astronomer();
        a.setId(1);
        a.setFirstName("Tanyarat");
        a.setLastName("Suksuwan");
        a.setInstitution("Mahidol University");
        a.setAddress("Bangkok, Thailand");
        a.setEmail("@mahidol.ac.th");
        return a;
    }

    private static ScienceObserver sampleObserver() {
        ScienceObserver so = new ScienceObserver() {};
        so.setId(1);
        so.setFirstName("Thanakit");
        so.setLastName("Liu");
        return so;
    }

    /* =========================================================
       =============== CREATE SCIENCE PLAN TESTS ==============
       ========================================================= */
    private static void testCreateSciencePlanNormalFlow() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Vietnam Test1");
        plan.setCreator(astronomer);
        plan.setFunding(5000.50);
        plan.setObjective("Test1");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Carina);

        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("1. Test Normal Flow: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanInvalidDateRange() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Invalid Date Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("Test");
        plan.setStartDate("2024-03-15");
        plan.setEndDate("2024-01-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("2. Test Invalid Date: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanMissingPlanName() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("Missing Plan Name");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Vietnam");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("3. Test Missing Plan Name: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanMissingObjective() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Miss Objective Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective(null);
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Vietnam");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("4. Test Missing Objective: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanNegativeFunding() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Negative Funding");
        plan.setCreator(astronomer);
        plan.setFunding(-1000.00);
        plan.setObjective("Test");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Vietnam");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("5. Test Negative Funding: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanDuplicateName() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan p1 = new SciencePlan();
        p1.setPlanName("Duplicate Test Plan");
        p1.setCreator(astronomer);
        p1.setFunding(3000.00);
        p1.setObjective("Test1");
        p1.setStartDate("2024-01-15");
        p1.setEndDate("2024-02-15");
        p1.setTelescope("Hawaii");
        p1.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        ocs.createSciencePlan(p1, astronomer);

        SciencePlan p2 = new SciencePlan();
        p2.setPlanName("Duplicate Test Plan");
        p2.setCreator(astronomer);
        p2.setFunding(2000.00);
        p2.setObjective("Test2");
        p2.setStartDate("2024-03-15");
        p2.setEndDate("2024-04-15");
        p2.setTelescope("Chile");
        p2.setTarget(StarSystem.CONSTELLATIONS.Aquarius);
        String result = ocs.createSciencePlan(p2, astronomer);

        System.out.println("6. Test Duplicate Name: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanScheduleConflict() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan p1 = new SciencePlan();
        p1.setPlanName("First Plan");
        p1.setCreator(astronomer);
        p1.setFunding(3000);
        p1.setObjective("Obs1");
        p1.setStartDate("2024-01-15");
        p1.setEndDate("2024-02-15");
        p1.setTelescope("Hawaii");
        p1.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        ocs.createSciencePlan(p1, astronomer);

        SciencePlan p2 = new SciencePlan();
        p2.setPlanName("Conflict Plan");
        p2.setCreator(astronomer);
        p2.setFunding(2000);
        p2.setObjective("Obs2");
        p2.setStartDate("2024-02-01");
        p2.setEndDate("2024-03-01");
        p2.setTelescope("Hawaii");
        p2.setTarget(StarSystem.CONSTELLATIONS.Aquarius);
        String result = ocs.createSciencePlan(p2, astronomer);

        System.out.println("7. Test Schedule Conflict: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateMultiplePlansForDifferentTelescopes() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan hawaii = new SciencePlan();
        hawaii.setPlanName("Hawaii Plan");
        hawaii.setCreator(astronomer);
        hawaii.setFunding(3000);
        hawaii.setObjective("Obs Hawaii");
        hawaii.setStartDate("2024-01-15");
        hawaii.setEndDate("2024-02-15");
        hawaii.setTelescope("Hawaii");
        hawaii.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result1 = ocs.createSciencePlan(hawaii, astronomer);

        System.out.println("8.1 Plan Hawaii: ");
        System.out.println(result1);
        System.out.println();

        SciencePlan chile = new SciencePlan();
        chile.setPlanName("Chile Plan");
        chile.setCreator(astronomer);
        chile.setFunding(4000);
        chile.setObjective("Obs Chile");
        chile.setStartDate("2024-01-15");
        chile.setEndDate("2024-02-15");
        chile.setTelescope("Chile");
        chile.setTarget(StarSystem.CONSTELLATIONS.Aquarius);
        String result2 = ocs.createSciencePlan(chile, astronomer);

        System.out.println("8.2 Plan Chile: ");
        System.out.println(result2);
        System.out.println();
    }

    private static void testCreateSciencePlanMissingAstronomer() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Test Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000);
        plan.setObjective("Observe");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result = ocs.createSciencePlan(plan, null);

        System.out.println("9. Test Missing Astronomer: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanInvalidTelescope() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Invalid Telescope");
        plan.setCreator(astronomer);
        plan.setFunding(3000);
        plan.setObjective("Observe");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("InvalidScope");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("10. Test Invalid Telescope: ");
        System.out.println(result);
        System.out.println();
    }

    private static void testCreateSciencePlanAutomaticPlanID() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();

        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Auto ID Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000);
        plan.setObjective("Observe");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);

        String result = ocs.createSciencePlan(plan, astronomer);

        System.out.println("11. Test Automatic ID: ");
        System.out.println(result);
        System.out.println();
    }

    /* =========================================================
   =============== VALIDATE SCIENCE PLAN TESTS ==============
   ========================================================= */
    private static void testValidateSciencePlanNormal() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();

        SciencePlan plan = new SciencePlan();
        plan.setPlanNo(1);
        plan.setPlanName("Valid Plan");
        plan.setCreator(astronomer);
        plan.setFunding(2000);
        plan.setObjective("Observe Carina");
        plan.setTelescope("Gemini South");
        plan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-05-15");

        System.out.println("1. Test Normal Flow: ");
        SciencePlan result = ocs.validateSciencePlan(plan, observer);
        System.out.println(result.getStatus());
        System.out.println();
    }

    private static void testValidateMissingFields() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();

        SciencePlan plan = new SciencePlan();
        plan.setPlanNo(0);
        plan.setPlanName(null);
        plan.setCreator(astronomer);
        plan.setFunding(0);
        plan.setObjective(null);
        plan.setTelescope("Gemini North");
        plan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-05-15");

        System.out.println("2. Test Validate Missing Fields: ");
        SciencePlan result = ocs.validateSciencePlan(plan, observer);
        System.out.println(result.getStatus());
        System.out.println();
    }

    private static void testValidateWrongLocation() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();

        SciencePlan plan = new SciencePlan();
        plan.setPlanNo(3);
        plan.setPlanName("Wrong Location Test");
        plan.setCreator(astronomer);
        plan.setFunding(1000);
        plan.setObjective("Obs");
        plan.setTelescope("Gemini North");
        plan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-12-15");

        System.out.println("3. Test Validate Wrong Location: ");
        SciencePlan result = ocs.validateSciencePlan(plan, observer);
        System.out.println(result.getStatus());
        System.out.println();
    }

    private static void testValidateWrongDateRange() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();

        SciencePlan plan = new SciencePlan();
        plan.setPlanNo(4);
        plan.setPlanName("Wrong Date Test");
        plan.setCreator(astronomer);
        plan.setFunding(1500);
        plan.setObjective("Obs");
        plan.setTelescope("Gemini North");
        plan.setTarget(StarSystem.CONSTELLATIONS.Carina);

        plan.setStartDate("2024-06-01");
        plan.setEndDate("2024-07-01");

        System.out.println("4. Test Validate Wrong Date: ");
        SciencePlan result = ocs.validateSciencePlan(plan, observer);
        System.out.println(result.getStatus());
        System.out.println();
    }

    /* =========================================================
   =============== CREATE OBSERVING PROGRAM TESTS ==============
   ========================================================= */
    private static void testCreateObservingProgramSuccess() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();
        SciencePlan validatedPlan = new SciencePlan();
        validatedPlan.setPlanNo(1);
        validatedPlan.setPlanName("Valid Plan");
        validatedPlan.setCreator(astronomer);
        validatedPlan.setFunding(2000);
        validatedPlan.setObjective("Observe Carina");
        validatedPlan.setTelescope("Gemini South");
        validatedPlan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        validatedPlan.setStartDate("2024-01-15");
        validatedPlan.setEndDate("2024-05-15");
        validatedPlan.setStatus(AbstractSciencePlan.STATUS.VALIDATED);

        TelePositionPair[] telePairs = { new TelePositionPair() };

        System.out.println("1. Test Normal Flow: ");

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                5.0,
                10.0,
                40.0,
                ObservingProgramConfigs.FoldMirrorType.REFLECTIVE_CONVERGING_BEAM,
                2,
                ObservingProgramConfigs.CalibrationUnit.Argon,
                ObservingProgramConfigs.LightType.CerroPachonSkyEmission,
                telePairs,
                observer
        );
        System.out.println("Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("Plan Status After Creation: " + validatedPlan.getStatus());
        System.out.println();
    }

    private static void testCreateObservingProgramFailure1() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();
        SciencePlan validatedPlan = new SciencePlan();
        validatedPlan.setPlanNo(1);
        validatedPlan.setPlanName("Valid Plan");
        validatedPlan.setCreator(astronomer);
        validatedPlan.setFunding(2000);
        validatedPlan.setObjective("Observe Carina");
        validatedPlan.setTelescope("Gemini South");
        validatedPlan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        validatedPlan.setStartDate("2024-01-15");
        validatedPlan.setEndDate("2024-05-15");
        validatedPlan.setStatus(AbstractSciencePlan.STATUS.VALIDATED);

        TelePositionPair[] telePairs = { new TelePositionPair() };

        System.out.println("2. Test Invalid F-Stop (Too Low): ");

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                1.0,
                10.0,
                40.0,
                ObservingProgramConfigs.FoldMirrorType.REFLECTIVE_CONVERGING_BEAM,
                2,
                ObservingProgramConfigs.CalibrationUnit.Argon,
                ObservingProgramConfigs.LightType.CerroPachonSkyEmission,
                telePairs,
                observer
        );
        System.out.println("Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("Plan Status After Attempt: " + validatedPlan.getStatus());
        System.out.println();
    }

    private static void testCreateObservingProgramFailure2() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();
        SciencePlan validatedPlan = new SciencePlan();
        validatedPlan.setPlanNo(1);
        validatedPlan.setPlanName("Valid Plan");
        validatedPlan.setCreator(astronomer);
        validatedPlan.setFunding(2000);
        validatedPlan.setObjective("Observe Carina");
        validatedPlan.setTelescope("Gemini South");
        validatedPlan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        validatedPlan.setStartDate("2024-01-15");
        validatedPlan.setEndDate("2024-05-15");
        validatedPlan.setStatus(AbstractSciencePlan.STATUS.VALIDATED);

        TelePositionPair[] telePairs = { new TelePositionPair() };

        System.out.println("3. Test Invalid F-Stop (Too High): ");

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                200.0,
                10.0,
                40.0,
                ObservingProgramConfigs.FoldMirrorType.REFLECTIVE_CONVERGING_BEAM,
                2,
                ObservingProgramConfigs.CalibrationUnit.Argon,
                ObservingProgramConfigs.LightType.CerroPachonSkyEmission,
                telePairs,
                observer
        );
        System.out.println("Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("Plan Status After Attempt: " + validatedPlan.getStatus());
        System.out.println();
    }

    private static void testCreateObservingProgramFailure3() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();
        SciencePlan validatedPlan = new SciencePlan();
        validatedPlan.setPlanNo(1);
        validatedPlan.setPlanName("Valid Plan");
        validatedPlan.setCreator(astronomer);
        validatedPlan.setFunding(2000);
        validatedPlan.setObjective("Observe Carina");
        validatedPlan.setTelescope("Gemini South");
        validatedPlan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        validatedPlan.setStartDate("2024-01-15");
        validatedPlan.setEndDate("2024-05-15");
        validatedPlan.setStatus(AbstractSciencePlan.STATUS.VALIDATED);

        TelePositionPair[] telePairs = { new TelePositionPair() };

        System.out.println("4. Test Invalid Optics Primary: ");

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "XYZ",
                5.0,
                10.0,
                40.0,
                ObservingProgramConfigs.FoldMirrorType.REFLECTIVE_CONVERGING_BEAM,
                2,
                ObservingProgramConfigs.CalibrationUnit.Argon,
                ObservingProgramConfigs.LightType.CerroPachonSkyEmission,
                telePairs,
                observer
        );
        System.out.println("Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("Plan Status After Attempt: " + validatedPlan.getStatus());
        System.out.println();
    }

    private static void testCreateObservingProgramFailure4() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();
        SciencePlan validatedPlan = new SciencePlan();
        validatedPlan.setPlanNo(1);
        validatedPlan.setPlanName("Valid Plan");
        validatedPlan.setCreator(astronomer);
        validatedPlan.setFunding(2000);
        validatedPlan.setObjective("Observe Carina");
        validatedPlan.setTelescope("Gemini South");
        validatedPlan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        validatedPlan.setStartDate("2024-01-15");
        validatedPlan.setEndDate("2024-05-15");
        validatedPlan.setStatus(AbstractSciencePlan.STATUS.VALIDATED);
        TelePositionPair[] telePairs = { new TelePositionPair() };

        System.out.println("5. Test Invalid Optics Secondary RMS: ");

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                5.0,
                0.0,
                40.0,
                ObservingProgramConfigs.FoldMirrorType.REFLECTIVE_CONVERGING_BEAM,
                2,
                ObservingProgramConfigs.CalibrationUnit.Argon,
                ObservingProgramConfigs.LightType.CerroPachonSkyEmission,
                telePairs,
                observer
        );
        System.out.println("Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("Plan Status After Attempt: " + validatedPlan.getStatus());
        System.out.println();
    }

    private static void testCreateObservingProgramFailure5() {
        OCS ocs = new OCS();
        Astronomer astronomer = sampleAstronomer();
        ScienceObserver observer = sampleObserver();
        SciencePlan validatedPlan = new SciencePlan();
        validatedPlan.setPlanNo(1);
        validatedPlan.setPlanName("Valid Plan");
        validatedPlan.setCreator(astronomer);
        validatedPlan.setFunding(2000);
        validatedPlan.setObjective("Observe Carina");
        validatedPlan.setTelescope("Gemini South");
        validatedPlan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        validatedPlan.setStartDate("2024-01-15");
        validatedPlan.setEndDate("2024-05-15");
        validatedPlan.setStatus(AbstractSciencePlan.STATUS.VALIDATED);

        TelePositionPair[] telePairs = null;

        System.out.println("6. Test Invalid Tele Position Pair: ");

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                5.0,
                10.0,
                40.0,
                ObservingProgramConfigs.FoldMirrorType.REFLECTIVE_CONVERGING_BEAM,
                2,
                ObservingProgramConfigs.CalibrationUnit.Argon,
                ObservingProgramConfigs.LightType.CerroPachonSkyEmission,
                telePairs,
                observer
        );
        System.out.println("Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("Plan Status After Attempt: " + validatedPlan.getStatus());
        System.out.println();
    }
}
