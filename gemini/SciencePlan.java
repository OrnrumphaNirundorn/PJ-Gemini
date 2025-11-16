package gemini;

import gemini.model.AbstractSciencePlan;
import gemini.model.StarSystem;

import java.time.LocalDate;

public class SciencePlan extends AbstractSciencePlan {
    private AstronomicalData astronomicalData;

    public SciencePlan(
            int planNo,
            String planName,
            Astronomer creator,
            double funding,
            String objective,
            LocalDate startDate,
            LocalDate endDate,
            String telescope,
            StarSystem.CONSTELLATIONS target,
            STATUS status,
            AstronomicalData astronomicalData
    ) {
        super(planNo, planName, creator, funding, objective, startDate, endDate, telescope, target, status);
        this.astronomicalData = astronomicalData;
    }

    public SciencePlan() {
        super();
    }
}
