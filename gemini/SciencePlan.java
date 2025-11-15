package gemini;

import gemini.model.AbstractSciencePlan;
import gemini.model.StarSystem;

import java.time.LocalDate;

public class SciencePlan extends AbstractSciencePlan {
    private StarSystem starSystem;
    private AstronomicalData astronomicalData;

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
            StarSystem starSystem,
            AstronomicalData astronomicalData
    ) {
        super(planNo, planName, creator, funding, objective, startDate, endDate, telescope, target, status);
        this.starSystem = starSystem;
        this.astronomicalData = astronomicalData;
    }

    public SciencePlan() {
        super();
    }
}
