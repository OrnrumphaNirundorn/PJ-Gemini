package gemini;

import gemini.model.AbstractAstronomicalData;

public class AstronomicalData extends AbstractAstronomicalData {
    public AstronomicalData(
            String fileType,
            String fileQuality,
            String colorType,
            double contrast,
            double brightness,
            double saturation,
            double highlights,
            double exposure,
            double shadows,
            double whites,
            double blacks,
            double luminance
    ) {
        super(fileType, fileQuality, colorType, contrast, brightness, saturation, highlights, exposure, shadows, whites, blacks, luminance);
    }

    public AstronomicalData() {}
}
