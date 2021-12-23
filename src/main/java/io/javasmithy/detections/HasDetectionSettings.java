package io.javasmithy.detections;

import io.javasmithy.detections.DetectionSettings;

public interface HasDetectionSettings {
    public DetectionSettings getDetectionSettings();
    public void setDetectionSettings(DetectionSettings ds);
}
