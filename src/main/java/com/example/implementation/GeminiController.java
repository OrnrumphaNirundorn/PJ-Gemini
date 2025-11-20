package com.example.implementation;

import gemini.*;
import gemini.model.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GeminiController {

    @GetMapping("/hello")
    public String hello() {
        return "Spring Boot is now connected to your Gemini project!";
    }

    // 1. Endpoint for Creating a Science Plan
    @GetMapping("/createSciPlan")
    public String createPlan() {
        OCS ocs = new OCS();
        Astronomer astr = new Astronomer();
        astr.setId(1);
        astr.setFirstName("JJ");
        astr.setLastName("JJ");

        SciencePlan sp = new SciencePlan();
        sp.setPlanNo(1);
        sp.setPlanName("PlanA");
        sp.setCreator(astr);
        sp.setFunding(200.00);
        sp.setObjective("To test");
        sp.setTarget(StarSystem.CONSTELLATIONS.Carina);
        sp.setTelescope("Gemini North");
        sp.setStartDate("2022-01-26");
        sp.setEndDate("2022-04-26");

        return "Plan created: " + ocs.createSciencePlan(sp, astr);
    }

    // 2. Endpoint for Validating a Science Plan
    @GetMapping("/validateSciPlan")
    public SciencePlan validateTestPlan() {
        OCS ocs = new OCS();
        Astronomer astr = new Astronomer();
        astr.setId(1);
        astr.setFirstName("JJ");
        astr.setLastName("JJ");

        AbstractSciencePlan sp = new SciencePlan();
        sp.setPlanNo(1);
        sp.setPlanName("test1");
        sp.setCreator(astr);
        sp.setFunding(200.00);
        sp.setObjective("To test");
        sp.setTarget(StarSystem.CONSTELLATIONS.Carina);
        sp.setTelescope("Gemini North");
        sp.setStartDate("2022-01-26");
        sp.setEndDate("2022-04-26");

        ScienceObserver so = new ScienceObserver();
        so.setId(1);
        so.setFirstName("Thanakit");
        so.setLastName("Liu");
        SciencePlan sciPlan = ocs.validateSciencePlan(sp, so);

        if(sciPlan == null) {
            return null;
        }

        return sciPlan;
    }

    // 3. Endpoint for Creating an Observing Program
    @GetMapping("/createObservingProgram")
    public Object createObservingProgram() {
        OCS ocs = new OCS();
        Astronomer astronomer = new Astronomer();
        ScienceObserver observer = new ScienceObserver();
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

        return op;
    }


}
