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
        SciencePlan sp = new SciencePlan();
        sp.setPlanNo(1);
        sp.setPlanName("test1");
        sp.setCreator("JJ");
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
        SciencePlan sp = new SciencePlan();
        sp.setPlanNo(2);
        sp.setPlanName("test2");
        sp.setCreator("JN");
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
}

