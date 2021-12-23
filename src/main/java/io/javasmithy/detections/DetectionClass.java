package io.javasmithy.detections;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetectionClass implements Comparable{
    private StringProperty detectionClassName;
    private IntegerProperty detectionClassId;

    public DetectionClass(String detectionClassName, Integer detectionClassId) {
        setDetectionClassName(detectionClassName);
        setDetectionClassId(detectionClassId);
    }

    public String getDetectionClassName() {
        return detectionClassName.get();
    }

    public StringProperty detectionClassNameProperty() {
        if (this.detectionClassName == null) this.detectionClassName = new SimpleStringProperty("entry");
        return detectionClassName;
    }

    public void setDetectionClassName(String detectionClassName) {
        detectionClassNameProperty().set(detectionClassName);
    }

    public int getDetectionClassId() {
        return detectionClassId.get();
    }

    public IntegerProperty detectionClassIdProperty() {
        if (this.detectionClassId == null) this.detectionClassId = new SimpleIntegerProperty(-1);
        return detectionClassId;
    }

    public void setDetectionClassId(int detectionClassId) {
        detectionClassIdProperty().set(detectionClassId);
    }

    @Override
    public int compareTo(Object o) {
        DetectionClass target = (DetectionClass) o;
        int result = 0;
        if (this.getDetectionClassId() < target.getDetectionClassId()) result = -1;
        if (this.getDetectionClassId() == target.getDetectionClassId()) result = 0;
        if (this.getDetectionClassId() > target.getDetectionClassId()) result = 1;
        return result;
    }
}
