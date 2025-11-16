package gemini.model;

import java.time.LocalDate;

public abstract class AbstractSciencePlan {
    private int planNo;
    private String planName;
    private String creator;
    private double funding;
    private String objective;
    private LocalDate startDate;
    private LocalDate endDate;
    private String telescope;
    private StarSystem.CONSTELLATIONS target;
    private STATUS status;

    public AbstractSciencePlan(
            int planNo,
            String planName,
            String creator,
            double funding,
            String objective,
            LocalDate startDate,
            LocalDate endDate,
            String telescope,
            StarSystem.CONSTELLATIONS target,
            STATUS status
    ) {
        this.planNo = planNo;
        this.planName = planName;
        this.creator = creator;
        this.funding = funding;
        this.objective = objective;
        this.startDate = startDate;
        this.endDate = endDate;
        this.telescope = telescope;
        this.target = target;
        this.status = status;
    }

    public AbstractSciencePlan() {}

    public int getPlanNo() { return planNo; }
    public String getPlanName() { return planName; }
    public String getCreator() { return creator; }
    public double getFunding() { return funding; }
    public String getObjective() { return objective; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getTelescope() { return telescope; }
    public StarSystem.CONSTELLATIONS getTarget() { return target; }
    public STATUS getStatus() { return status; }

    public void setPlanNo(int planNo) { this.planNo = planNo; }
    public void setPlanName(String planName) { this.planName = planName; }
    public void setCreator(String creator) { this.creator = creator; }
    public void setFunding(double funding) { this.funding = funding; }
    public void setObjective(String objective) { this.objective = objective; }
    public void setStartDate(String startDate) {
        LocalDate localDate = LocalDate.parse(startDate);
        this.startDate = localDate;
    }
    public void setEndDate(String endDate) {
        LocalDate localDate = LocalDate.parse(endDate);
        this.endDate = localDate;
    }
    public void setTelescope(String telescope) { this.telescope = telescope; }
    public void setTarget(StarSystem.CONSTELLATIONS target) { this.target = target; }
    public void setStatus(STATUS status) { this.status = status; }

    /**
     * This is the enum indicating the status of a science plan
     * <ul>
     * <li>COMPLETE = The plan finishes running</li>
     * <li>TESTED = The plan finished testing</li>
     * <li>CANCELLED = The plan has been canceled</li>
     * <li>RUNNING = The plan is being run in edu.gemini.OCS</li>
     * <li>SUBMITTED = The plan has been submitted to edu.gemini.OCS</li>
     * <li>VALIDATED = The plan has been validated by the science observer</li>
     * <li>INVALIDATED = The plan has been rejected by the science observer</li>
     * </ul>
     *
     * The flows can be as follows.
     * <ul>
     * <li>FLOW1: SUBMITTED (Astronomer) > ** TESTED (Astronomer) >
     * ** VALIDATED/INVALIDATED (Science Observer) > ** RUNNING (edu.gemini.OCS) > COMPLETE (edu.gemini.OCS) > *** </li>
     * <li>FLOW2: SUBMITTED (Astronomer) > CANCELLED (Astronomer) > ***</li>
     * </ul>
     */
    public enum STATUS {
        /**
         * SAVED = The plan is saved
         */
        SAVED,
        /**
         * COMPLETE = The plan finishes running
         */
        COMPLETE,
        /**
         * TESTED = The plan finished testing
         */
        TESTED,
        /**
         * CANCELLED = The plan has been canceled
         */
        CANCELLED,
        /**
         * RUNNING = The plan is being run in edu.gemini.OCS
         */
        RUNNING,
        /**
         * SUBMITTED = The plan has been submitted to edu.gemini.OCS
         */
        SUBMITTED,
        /**
         * VALIDATED = The plan has been validated by the science observer
         */
        VALIDATED,
        /**
         * INVALIDATED = The plan has been rejected by the science observer
         */
        INVALIDATED
    }
}
