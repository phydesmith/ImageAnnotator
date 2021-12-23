package io.javasmithy;


import java.io.IOException;
import java.net.URL;
import java.util.*;

import io.javasmithy.detections.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public class SecondaryController implements Initializable, HasDetectionSettings, Resizable {
    private DetectionSettings ds;
    private List<RadioButton> radioButtons;
    private GraphicsContext gc;
    private double orgX, orgY;
    private double[] coords;
    private Image testImage;
    private List<AnnotationBox> annotationBoxes;
    private ToggleGroup classRadioButtonToggleGroup;

    @FXML
    private BorderPane secondaryRoot;
    @FXML
    private VBox radioButtonsBox;
    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gc = canvas.getGraphicsContext2D();
        this.coords = new double[4];
        this.annotationBoxes = new ArrayList<>();
        initObjectBoundary();
        loadTestImage();
        drawSelectedImage();
    }
    private void initObjectBoundary(){
        addBoundaryDrawEventHandler();
    }
    private void addBoundaryDrawEventHandler(){
        this.canvas.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            double xDelta = 0;
            double yDelta = 0;
            double xVal = 0;
            double yVal = 0 ;

            @Override
            public void handle(MouseEvent mouseEvent) {
                gc.setStroke(Color.RED);



                if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    gc.strokeRect(mouseEvent.getX(), mouseEvent.getY(), 0,0);
                    orgX= mouseEvent.getX();
                    orgY= mouseEvent.getY();
                }
                if (mouseEvent.isPrimaryButtonDown()) {
                    gc.clearRect(0,0,1600, 800);
                    drawSelectedImage();
                    drawCurrentAnnotations();

                    xDelta = mouseEvent.getX()-orgX;
                    yDelta = mouseEvent.getY()-orgY;

                    xVal = orgX;
                    yVal = orgY;

                    if (xDelta < 0){
                        xVal = xVal+xDelta;
                        xDelta = -xDelta;
                    }
                    if (yDelta < 0) {
                        yVal = yVal+yDelta;
                        yDelta = -yDelta;
                    }

                    gc.strokeRect(xVal, yVal, xDelta, yDelta);
                }
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    coords[0] = xVal;
                    coords[1] = yVal;
                    coords[2] = xDelta;
                    coords[3] = yDelta;
                    System.out.println(Arrays.toString(coords));
                }
            }
        });
    }
    private void loadTestImage(){
        this.testImage = new Image(getClass().getResource("/images/test-image.jpg").toExternalForm());
    }
    private void drawSelectedImage(){
        this.canvas.setHeight(testImage.getHeight());
        this.canvas.setWidth(testImage.getWidth());
        gc.drawImage(this.testImage, 0.0, 0.0);
    }
    private void drawCurrentAnnotations(){
        for (AnnotationBox annotationBox:
                this.annotationBoxes) {
            gc.strokeRect(annotationBox.getXMin(), annotationBox.getYMin(), annotationBox.getBoxWidth(), annotationBox.getBoxHeight());
        }
    }
    public void initRadioButtons(){
        this.classRadioButtonToggleGroup = new ToggleGroup();
        this.radioButtons = new ArrayList<>();
        List<DetectionClass> detectionClasses = new ArrayList(ds.getDetectionClasses().values());
        Collections.sort(detectionClasses);
        for(int i = 0; i < detectionClasses.size(); i++){
            RadioButton button = new RadioButton();
            button.setText(detectionClasses.get(i).getDetectionClassName());
            button.setToggleGroup(classRadioButtonToggleGroup);
            radioButtons.add(button);
        }
        this.radioButtonsBox.getChildren().addAll(this.radioButtons);
    }

    @Override
    public DetectionSettings getDetectionSettings() {
        if (this.ds == null) this.ds = new DetectionSettingsBuilder().createDetectionSettings();
        return this.ds;
    }

    @Override
    public void setDetectionSettings(DetectionSettings ds) {
        this.ds = ds;
        initRadioButtons();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary", this.ds);
    }

    public void resizeWindow(){
        Window window = secondaryRoot.getScene().getWindow();
        window.setHeight(1000);
        window.setWidth(1600);
    }

    @FXML
    private void exit() {
        App.exit();
    }

    @FXML
    private void saveCurrentAnnotation(){
        String classname = ((RadioButton)this.classRadioButtonToggleGroup.getSelectedToggle()).getText();

        AnnotationBox ab = new AnnotationBoxBuilder()
                .setXMin((int)coords[0])
                .setYMin((int)coords[1])
                .setBoxWidth((int)coords[2])
                .setBoxHeight((int)coords[3])
                .setDetectionClass(  this.ds.getDetectionClasses().get(classname) )
                .setImageName( this.ds.getSessionId() + this.testImage.getUrl())
                .createAnnotationBox();

        this.annotationBoxes.add(ab);
    }


}