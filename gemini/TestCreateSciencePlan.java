package ocs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.gemini.*;
import edu.gemini.model.StarSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for "Create a Science Plan" Use Case

  Use Case: Create a science plan
  Primary Actor: Astronomer
 
 This test class validates the following scenarios:
 - Normal flow: Successfully create a science plan with all required fields
 - Validation: Start date cannot be after end date
 - Validation: All required fields must be completed
 - Validation: Funding must be positive numerical value
 - Validation: Plan name must be unique
 - Validation: No schedule conflicts for the same telescope
 - Validation: Target must be valid from star catalogue
 */
public class TestCreateSciencePlan {
    private OCS ocs;
    private Astronomer astronomer;

    @BeforeEach
    public void setUp() {
        ocs = new OCS();
        astronomer = new Astronomer();
        astronomer.setId(1);
        astronomer.setFirstName("Tanyarat");
        astronomer.setLastName("Suksuwan");
        astronomer.setInstitution("Mahidol University");
        astronomer.setAddress("Bangkok, Thailand");
        astronomer.setEmail("@mahidol.ac.th");
    }

    /**
     * Normal Flow: Step 1-6
     * Successfully create a science plan with all required information
     */
    @Test
    public void testCreateSciencePlanNormalFlow() {
        // Step 3: Create science plan form
        SciencePlan plan = new SciencePlan();
        
        // Step 4: Astronomer fills in all details
        plan.setPlanName("Vietnam Test1");
        plan.setCreator(astronomer);
        plan.setFunding(5000.50);
        plan.setObjective("Test1");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Carina);
        
        // Step 6: Save science plan
        String result = ocs.createSciencePlan(plan, astronomer);
        
        // Assertions
        assertTrue(result.contains("successfully"));
        assertTrue(result.contains("Plan ID"));
        assertNotNull(ocs.getSciencePlanByNo(plan.getPlanNo()));
        assertEquals(1, ocs.getAllSciencePlans().size());
    }

    /**
     * Exceptional Flow: Start date after end date
     * The system should alert: "Start date cannot be after the end date"
     */
    @Test
    public void testCreateSciencePlanInvalidDateRange() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Invalid Date Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("To observe star systems");
        plan.setStartDate("2024-03-15");  // After end date
        plan.setEndDate("2024-01-15");    // Before start date
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result = ocs.createSciencePlan(plan, astronomer);
        
        assertEquals("Start date cannot be after the end date.", result);
    }

    /**
     * Exceptional Flow: Missing required fields
     * The system should display: "Please complete all required fields before saving"
     */
    @Test
    public void testCreateSciencePlanMissingPlanName() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("");  // Empty plan name
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("Missing plan name");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Vietnam");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result = ocs.createSciencePlan(plan, astronomer);
        
        assertEquals("Please complete all required fields before saving the science plan.", result);
    }

    /**
     * Exceptional Flow: Missing objective
     * The system should display: "Please complete all required fields before saving"
     */
    @Test
    public void testCreateSciencePlanMissingObjective() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Miss Objective Test Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective(null);  // Missing objective
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Vietnam");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result = ocs.createSciencePlan(plan, astronomer);
        
        assertEquals("Please complete all required fields before saving the science plan.", result);
    }

    /**
     * Exceptional Flow: Negative funding
     * The system should display: "Funding amount must be a positive numerical value"
     */
    @Test
    public void testCreateSciencePlanNegativeFunding() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Negative Funding Plan");
        plan.setCreator(astronomer);
        plan.setFunding(-1000.00);  // Negative funding
        plan.setObjective("To observe star systems");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Vietnam");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result = ocs.createSciencePlan(plan, astronomer);
        
        assertEquals("Funding amount must be a positive numerical value.", result);
    }

    /**
     * Exceptional Flow: Duplicate plan name
     * The system should warn: "A science plan with this name already exists"
     */
    @Test
    public void testCreateSciencePlanDuplicateName() {
        // Create first plan
        SciencePlan plan1 = new SciencePlan();
        plan1.setPlanName("Duplicate Test Plan");
        plan1.setCreator(astronomer);
        plan1.setFunding(3000.00);
        plan1.setObjective("First observation");
        plan1.setStartDate("2024-01-15");
        plan1.setEndDate("2024-02-15");
        plan1.setTelescope("Hawaii");
        plan1.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result1 = ocs.createSciencePlan(plan1, astronomer);
        assertTrue(result1.contains("successfully"));
        
        // Try to create second plan with same name
        SciencePlan plan2 = new SciencePlan();
        plan2.setPlanName("Duplicate Test Plan");  // Same name
        plan2.setCreator(astronomer);
        plan2.setFunding(2000.00);
        plan2.setObjective("Second observation");
        plan2.setStartDate("2024-03-15");
        plan2.setEndDate("2024-04-15");
        plan2.setTelescope("Chile");
        plan2.setTarget(StarSystem.CONSTELLATIONS.Aquarius);
        
        String result2 = ocs.createSciencePlan(plan2, astronomer);
        
        assertEquals("A science plan with this name already exists. Please use a different plan name.", result2);
    }

    /**
     * Exceptional Flow: Schedule conflict
     * The system should alert: "Selected date range conflicts with another scheduled plan"
     */
    @Test
    public void testCreateSciencePlanScheduleConflict() {
        // Create first plan
        SciencePlan plan1 = new SciencePlan();
        plan1.setPlanName("First Vietnam Plan");
        plan1.setCreator(astronomer);
        plan1.setFunding(3000.00);
        plan1.setObjective("First observation");
        plan1.setStartDate("2024-01-15");
        plan1.setEndDate("2024-02-15");
        plan1.setTelescope("Hawaii");
        plan1.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result1 = ocs.createSciencePlan(plan1, astronomer);
        assertTrue(result1.contains("successfully"));
        
        // Try to create second plan with overlapping dates for same telescope
        SciencePlan plan2 = new SciencePlan();
        plan2.setPlanName("Conflicting Vietnam Plan");
        plan2.setCreator(astronomer);
        plan2.setFunding(2000.00);
        plan2.setObjective("Conflicting observation");
        plan2.setStartDate("2024-02-01");  // Overlaps with plan1
        plan2.setEndDate("2024-03-01");    // Overlaps with plan1
        plan2.setTelescope("Hawaii");      // Same telescope
        plan2.setTarget(StarSystem.CONSTELLATIONS.Aquarius);
        
        String result2 = ocs.createSciencePlan(plan2, astronomer);
        
        assertEquals("Selected date range conflicts with another scheduled plan for this telescope.", result2);
    }

    /**
     * Normal Flow: Multiple plans for different telescopes with overlapping dates
     * Should succeed because they use different telescopes
     */
    @Test
    public void testCreateMultiplePlansForDifferentTelescopes() {
        // Create plan for Hawaii
        SciencePlan hawaiiPlan = new SciencePlan();
        hawaiiPlan.setPlanName("Hawaii Observation");
        hawaiiPlan.setCreator(astronomer);
        hawaiiPlan.setFunding(3000.00);
        hawaiiPlan.setObjective("Observation at Hawaii");
        hawaiiPlan.setStartDate("2024-01-15");
        hawaiiPlan.setEndDate("2024-02-15");
        hawaiiPlan.setTelescope("Hawaii");
        hawaiiPlan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result1 = ocs.createSciencePlan(hawaiiPlan, astronomer);
        assertTrue(result1.contains("successfully"));
        
        // Create plan for Chile with same dates (should succeed - different telescope)
        SciencePlan chilePlan = new SciencePlan();
        chilePlan.setPlanName("Chile Observation");
        chilePlan.setCreator(astronomer);
        chilePlan.setFunding(4000.00);
        chilePlan.setObjective("Observation at Chile");
        chilePlan.setStartDate("2024-01-15");  // Same dates
        chilePlan.setEndDate("2024-02-15");    // Same dates
        chilePlan.setTelescope("Chile");       // Different telescope
        chilePlan.setTarget(StarSystem.CONSTELLATIONS.Aquarius);
        
        String result2 = ocs.createSciencePlan(chilePlan, astronomer);
        
        assertTrue(result2.contains("successfully"));
        assertEquals(2, ocs.getAllSciencePlans().size());
    }

    /**
     * Exceptional Flow: Missing astronomer information
     */
    @Test
    public void testCreateSciencePlanMissingAstronomer() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Test Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("To observe star systems");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result = ocs.createSciencePlan(plan, null);
        
        assertEquals("Error: Astronomer information is required.", result);
    }

    /**
     * Exceptional Flow: Invalid telescope selection
     */
    @Test
    public void testCreateSciencePlanInvalidTelescope() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Invalid Telescope Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("To observe star systems");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        
        plan.setTelescope("InvalidScope");  // Invalid telescope
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        
        String result = ocs.createSciencePlan(plan, astronomer);
        
        assertEquals("Invalid telescope selected. Must be Hawaii or Chile.", result);
    }

    /**
     * Normal Flow: Plan ID is automatically generated
     */
    @Test
    public void testCreateSciencePlanAutomaticPlanID() {
        SciencePlan plan = new SciencePlan();
        plan.setPlanName("Auto ID Plan");
        plan.setCreator(astronomer);
        plan.setFunding(3000.00);
        plan.setObjective("To observe star systems");
        plan.setStartDate("2024-01-15");
        plan.setEndDate("2024-03-15");
        plan.setTelescope("Hawaii");
        plan.setTarget(StarSystem.CONSTELLATIONS.Andromeda);
        // Plan ID not set - should be auto-generated
        
        String result = ocs.createSciencePlan(plan, astronomer);
        
        assertTrue(result.contains("successfully"));
        assertTrue(plan.getPlanNo() > 0);  // Plan ID should be assigned
    }
}
