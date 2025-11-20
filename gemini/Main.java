package gemini;

import gemini.model.StarSystem;

public class Main {

    public static void main(String[] args) {
        System.out.println("Invalid Science Plan Test");
        testValidateSciencePlan1();
        System.out.println();
        System.out.println("Valid Science Plan Test");
        testValidateSciencePlan2();
    }

    private static void testValidateSciencePlan1() {
        Astronomer astronomer1 = new Astronomer();
        astronomer1.setId(1);
        astronomer1.setFirstName("JJ");
        astronomer1.setLastName("JJ");

        SciencePlan sp = new SciencePlan();
        sp.setPlanNo(1);
        sp.setPlanName("test1");
        sp.setCreator(astronomer1);
        sp.setFunding(200.00);
        sp.setObjective("To test");
        sp.setTarget(StarSystem.CONSTELLATIONS.Carina);
        sp.setTelescope("Gemini North");
        sp.setStartDate("2022-01-26");
        sp.setEndDate("2022-04-26");

        ScienceObserver observer = new ScienceObserver() {};
        observer.setId(1);
        observer.setFirstName("Thanakit");
        observer.setLastName("Liu");

        OCS ocs = new OCS();

        SciencePlan validatedSciencePlan = ocs.validateSciencePlan(sp, observer);

        System.out.println(validatedSciencePlan.getPlanNo() + " " + validatedSciencePlan.getPlanName() + " " + validatedSciencePlan.getStatus());
    }

    private static void testValidateSciencePlan2() {
        Astronomer astronomer2 = new Astronomer();
        astronomer2.setId(2);
        astronomer2.setFirstName("JN");
        astronomer2.setLastName("JN");

        SciencePlan sp = new SciencePlan();
        sp.setPlanNo(2);
        sp.setPlanName("test2");
        sp.setCreator(astronomer2);
        sp.setFunding(200.00);
        sp.setObjective("To test");
        sp.setTarget(StarSystem.CONSTELLATIONS.Carina);
        sp.setTelescope("Gemini South");
        sp.setStartDate("2022-01-26");
        sp.setEndDate("2022-04-26");

        ScienceObserver observer = new ScienceObserver() {};
        observer.setId(2);
        observer.setFirstName("Thanakit");
        observer.setLastName("Lee");

        OCS ocs = new OCS();

        SciencePlan validatedSciencePlan = ocs.validateSciencePlan(sp, observer);

        System.out.println(validatedSciencePlan.getPlanNo() + " " + validatedSciencePlan.getPlanName() + " " + validatedSciencePlan.getStatus());
    }
        private static void testCreateObservingProgramSuccess(SciencePlan validatedPlan) {
        ScienceObserver observer = new ScienceObserver();
        observer.setFirstName("Shi");
        observer.setLastName("Sha");
        observer.setId(10); 

        OCS ocs = new OCS();

        AbstractTelePositionPair[] telePairs = { new TelePositionPair() };

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                5.0,
                10.0,
                40.0,
                FoldMirrorType.REFLECTIVE_CONVERGING,
                2,
                CalibrationUnit.ARGON,
                LightType.CERRO_PACHON_SKY_EMISSION,
                telePairs,
                observer
        );

        System.out.println("  Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("  Plan Status After Creation: " + validatedPlan.getStatus());
    }

    private static void testCreateObservingProgramFailure1(SciencePlan validatedPlan) {
        ScienceObserver observer = new ScienceObserver();
        observer.setFirstName("Ploy");
        observer.setLastName("Sod");
        observer.setId(11);

        OCS ocs = new OCS();

        AbstractTelePositionPair[] telePairs = { new TelePositionPair() };

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                20.0, 
                10.0,
                40.0,
                FoldMirrorType.REFLECTIVE_CONVERGING,
                2,
                CalibrationUnit.ARGON,
                LightType.CERRO_PACHON_SKY_EMISSION,
                telePairs,
                observer
        );

        System.out.println("  Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("  Plan Status After Attempt: " + validatedPlan.getStatus());
    }
    private static void testCreateObservingProgramFailure2(SciencePlan validatedPlan) {
        ScienceObserver observer = new ScienceObserver();
        observer.setFirstName("J");
        observer.setLastName("J");
        observer.setId(12);

        OCS ocs = new OCS();

        AbstractTelePositionPair[] telePairs = { new TelePositionPair() };
        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                200.0, //Wavelength สูงเกินไป
                10.0,
                40.0,
                FoldMirrorType.REFLECTIVE_CONVERGING,
                2,
                CalibrationUnit.ARGON,
                LightType.CERRO_PACHON_SKY_EMISSION,
                telePairs,
                observer
        );

        System.out.println("  Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("  Plan Status After Attempt: " + validatedPlan.getStatus());
    }
    private static void testCreateObservingProgramFailure3(SciencePlan validatedPlan) {
        ScienceObserver observer = new ScienceObserver();
        observer.setFirstName("To");
        observer.setLastName("on");
        observer.setId(13);

        OCS ocs = new OCS();

        AbstractTelePositionPair[] telePairs = { new TelePositionPair() };

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "XYZ", //ใช้ code ผิด plan ไม่ซัพพอต
                5.0,
                10.0,
                40.0,
                FoldMirrorType.REFLECTIVE_CONVERGING,
                2,
                CalibrationUnit.ARGON,
                LightType.CERRO_PACHON_SKY_EMISSION,
                telePairs,
                observer
        );

        System.out.println("  Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("  Plan Status After Attempt: " + validatedPlan.getStatus());
    }
    private static void testCreateObservingProgramFailure4(SciencePlan validatedPlan) {
        ScienceObserver observer = new ScienceObserver();
        observer.setFirstName("Ni");
        observer.setLastName("Ne");
        observer.setId(0); //ไอดีผิดหรือไม่มีไอดี

        OCS ocs = new OCS();

        AbstractTelePositionPair[] telePairs = { new TelePositionPair() };

        ObservingProgram op = ocs.createObservingProgram(
                validatedPlan,
                "GSZ",
                5.0,
                10.0,
                40.0,
                FoldMirrorType.REFLECTIVE_CONVERGING,
                2,
                CalibrationUnit.ARGON,
                LightType.CERRO_PACHON_SKY_EMISSION,
                telePairs,
                observer
        );

        System.out.println("  Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
        System.out.println("  Plan Status After Attempt: " + validatedPlan.getStatus());
    }
    private static void testCreateObservingProgramFailure5(SciencePlan validatedPlan) {
    System.out.println("\n--- Test 6");
    ScienceObserver observer = new ScienceObserver();
    observer.setFirstName("jay");
    observer.setLastName("jay");
    observer.setId(14);

    OCS ocs = new OCS();

    AbstractTelePositionPair[] telePairs = { new TelePositionPair() };

    ObservingProgram op = ocs.createObservingProgram(
        validatedPlan,
        "GSZ",
        5.0,
        0.0, //Exposure time ต้องมีค่า >0 เสมอ
        40.0,
        FoldMirrorType.REFLECTIVE_CONVERGING,
        2,
        CalibrationUnit.ARGON,
        LightType.CERRO_PACHON_SKY_EMISSION,
        telePairs,
        observer
    );

    System.out.println("  Creation Status: " + (op != null ? "SUCCESS" : "FAILURE"));
    System.out.println("  Plan Status After Attempt: " + validatedPlan.getStatus());
    }
}
