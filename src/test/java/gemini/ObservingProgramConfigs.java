package gemini;

import gemini.model.AbstractObservingProgramConfigs;

public class ObservingProgramConfigs extends AbstractObservingProgramConfigs {
    public ObservingProgramConfigs(
            double foldMirrorDegree,
            FoldMirrorType foldMirrorType,
            CalibrationUnit calibrationUnit,
            LightType lightType
    ) {
        super(foldMirrorDegree, foldMirrorType, calibrationUnit, lightType);
    }

    public ObservingProgramConfigs() {}
}
