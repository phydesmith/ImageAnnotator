package io.javasmithy.detections;

public class AnnotationBoxBuilder {
    private String imageName;
    private int xMin;
    private int yMin;
    private int boxHeight;
    private int boxWidth;
    private DetectionClass detectionClass;

    public AnnotationBoxBuilder setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public AnnotationBoxBuilder setXMin(int xMin) {
        this.xMin = xMin;
        return this;
    }

    public AnnotationBoxBuilder setYMin(int yMin) {
        this.yMin = yMin;
        return this;
    }

    public AnnotationBoxBuilder setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
        return this;
    }

    public AnnotationBoxBuilder setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
        return this;
    }

    public AnnotationBoxBuilder setDetectionClass(DetectionClass detectionClass) {
        this.detectionClass = detectionClass;
        return this;
    }

    public AnnotationBox createAnnotationBox() {
        return new AnnotationBox(imageName, xMin, yMin, boxHeight, boxWidth, detectionClass);
    }
}