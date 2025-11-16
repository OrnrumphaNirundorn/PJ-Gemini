package gemini;

import gemini.model.AbstractObservingProgram;

public class ObservingProgram extends AbstractObservingProgram {
    private ObservingProgramConfigs observingProgramConfigs;
    private TelePositionPair telePositionPair;

    public ObservingProgram(
            String geminiLocation,
            String opticsPrimary,
            double fStop,
            double opticsSecondaryRMS,
            ObservingProgramConfigs observingProgramConfigs,
            TelePositionPair telePositionPair
    ) {
        super(geminiLocation, opticsPrimary, fStop, opticsSecondaryRMS);
        this.observingProgramConfigs = observingProgramConfigs;
        this.telePositionPair = telePositionPair;
    }

    public ObservingProgram() {
        super();
    }
}
