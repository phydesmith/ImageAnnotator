package io.javasmithy.detections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectionSettingsBuilder {
    private Map<String, DetectionClass> detectionClasses = new HashMap<String, DetectionClass>();
    private String sessionId = System.nanoTime()+"";
    private int xOffset = 0;
    private int yOffset = 0;
    private int captureHeight = 0;
    private int captureWidth = 0;
    private boolean normalizeCoordinates = false;

    public DetectionSettingsBuilder setDetectionClasses(Map<String, DetectionClass> detectionClasses) {
        this.detectionClasses = detectionClasses;
        return this;
    }

    public DetectionSettingsBuilder setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public DetectionSettingsBuilder withXOffset(int xOffset) {
        this.xOffset = xOffset;
        return this;
    }

    public DetectionSettingsBuilder withYOffset(int yOffset) {
        this.yOffset = yOffset;
        return this;
    }

    public DetectionSettingsBuilder setCaptureHeight(int captureHeight) {
        this.captureHeight = captureHeight;
        return this;
    }

    public DetectionSettingsBuilder setCaptureWidth(int captureWidth) {
        this.captureWidth = captureWidth;
        return this;
    }

    public DetectionSettingsBuilder withNormalizeCoordinates(boolean normalizeCoordinates) {
        this.normalizeCoordinates = normalizeCoordinates;
        return this;
    }

    public DetectionSettings createDetectionSettings() {
        return new DetectionSettings(detectionClasses, sessionId, xOffset, yOffset, captureHeight, captureWidth, normalizeCoordinates);
    }
}