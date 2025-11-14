package edu.gemini;

import edu.gemini.model.AbstractSciencePlan;
import edu.gemini.model.AbstractSciencePlan.STATUS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SciencePlan extends AbstractSciencePlan {

    private Astronomer astronomer;                     
    private List<StarSystem> starSystems = new ArrayList<>();    
    private List<AstronomicalData> astronomicalData = new ArrayList<>(); 
    private STATUS status; // from AbstractSciencePlan.STATUS

    public SciencePlan() {
        super(0, null, null, 0.0, null, null, null, null, null);
        this.status = STATUS.SAVED;
    }

    public SciencePlan(
            int planNo,
            String planName,
            String creator,
            double funding,
            String objective,
            LocalDate startDate,
            LocalDate endDate,
            String telescope,
            String target,
            STATUS status,
            Astronomer astronomer
    ) {
        super(planNo, planName, creator, funding, objective, startDate, endDate, telescope, target);
        this.status = status;
        this.astronomer = astronomer;
    }
}
