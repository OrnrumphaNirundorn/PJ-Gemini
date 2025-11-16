package gemini.model;

public abstract class AbstractObservingProgramConfigs {
    private static double foldMirrorDegree;
    private FoldMirrorType foldMirrorType;
    private CalibrationUnit calibrationUnit;
    private LightType lightType;

    public AbstractObservingProgramConfigs(
            double foldMirrorDegree,
            FoldMirrorType foldMirrorType,
            CalibrationUnit calibrationUnit,
            LightType lightType
    ) {
        this.foldMirrorDegree = foldMirrorDegree;
        this.foldMirrorType = foldMirrorType;
        this.calibrationUnit = calibrationUnit;
        this.lightType = lightType;
    }

    public AbstractObservingProgramConfigs() {}

    public enum FoldMirrorType {
        REFLECTIVE_CONVERGING_BEAM, CASSEGRAIN_FOCUS;
    }

    public enum CalibrationUnit {
        Argon, Xenon, ThAr, CuAr;
    }

    public enum LightType {
        MaunaKeaSkyEmission, CerroPachonSkyEmission;
    }

    public static double getFoldMirrorDegree() { return foldMirrorDegree; }
    public static FoldMirrorType[] getFoldMirrorType() {
        return FoldMirrorType.values();
    }
    public static CalibrationUnit[] getCalibrationUnit() {
        return CalibrationUnit.values();
    }
    public static LightType[] getLightType() {
        return LightType.values();
    }
}