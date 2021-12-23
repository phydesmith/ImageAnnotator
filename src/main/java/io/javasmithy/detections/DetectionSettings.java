package io.javasmithy.detections;

import java.util.List;
import java.util.Map;

public class DetectionSettings {
    private Map<String, DetectionClass> detectionClasses;
    private String sessionId;
    private int xOffset;
    private int yOffset;
    private int captureHeight;
    private int captureWidth;
    private boolean normalizeCoordinates;

    public DetectionSettings(Map<String, DetectionClass> detectionClasses, String sessionId, int xOffset, int yOffset, int captureHeight, int captureWidth, boolean normalizeCoordinates) {
        this.detectionClasses = detectionClasses;
        this.sessionId = sessionId;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.captureHeight = captureHeight;
        this.captureWidth = captureWidth;
        this.normalizeCoordinates = normalizeCoordinates;
    }

    public DetectionSettings() {}


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, DetectionClass> getDetectionClasses() {
        return detectionClasses;
    }

    public void setDetectionClasses(Map<String, DetectionClass> detectionClasses) {
        this.detectionClasses = detectionClasses;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getCaptureHeight() {
        return captureHeight;
    }

    public void setCaptureHeight(int captureHeight) {
        this.captureHeight = captureHeight;
    }

    public int getCaptureWidth() {
        return captureWidth;
    }

    public void setCaptureWidth(int captureWidth) {
        this.captureWidth = captureWidth;
    }

    public boolean isNormalizeCoordinates() {
        return normalizeCoordinates;
    }

    public void setNormalizeCoordinates(boolean normalizeCoordinates) {
        this.normalizeCoordinates = normalizeCoordinates;
    }

    @Override
    public String toString() {
        return "DetectionSettings{" +
                "detectionClasses=" + detectionClasses +
                ", xOffset=" + xOffset +
                ", yOffset=" + yOffset +
                ", captureHeight=" + captureHeight +
                ", captureWidth=" + captureWidth +
                ", normalizeCoordinates=" + normalizeCoordinates +
                '}';
    }
}
