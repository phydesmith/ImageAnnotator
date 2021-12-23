package io.javasmithy.detections;

public class AnnotationBox {
    private String imageName;
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private int boxHeight;
    private int boxWidth;
    private DetectionClass detectionClass;

    public AnnotationBox(String imageName, int xMin, int yMin, int boxHeight, int boxWidth, DetectionClass detectionClass) {
        this.imageName = imageName;
        this.xMin = xMin;
        this.yMin = yMin;
        this.boxHeight = boxHeight;
        this.boxWidth = boxWidth;
        this.detectionClass = detectionClass;
        calcXYMax();
    }
    private void calcXYMax(){
        this.xMax = this.xMin + this.boxWidth;
        this.yMax = this.yMin + this.boxHeight;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getXMin() {
        return xMin;
    }

    public void setXMin(int xMin) {
        this.xMin = xMin;
    }

    public int getYMin() {
        return yMin;
    }

    public void setTMin(int yMin) {
        this.yMin = yMin;
    }

    public int getXMax() {
        return xMax;
    }

    public void setXMax(int xMax) {
        this.xMax = xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public void setYMax(int yMax) {
        this.yMax = yMax;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(int height) {
        this.boxHeight = height;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
    }

    public DetectionClass getDetectionClass() {
        return detectionClass;
    }

    public void setDetectionClass(DetectionClass detectionClass) {
        this.detectionClass = detectionClass;
    }

    @Override
    public String toString() {
        return "";
    }


}
