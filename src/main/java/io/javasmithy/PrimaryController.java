package io.javasmithy;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import io.javasmithy.detections.DetectionSettingsBuilder;
import io.javasmithy.detections.HasDetectionSettings;
import io.javasmithy.detections.DetectionClass;
import io.javasmithy.detections.DetectionSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;


public class PrimaryController implements Initializable, HasDetectionSettings, Resizable {
    private ObservableList detectionClassList;
    private DetectionSettings ds;

    @FXML
    BorderPane primaryRoot;
    @FXML
    TableView<DetectionClass> classTableView;
    @FXML
    TableColumn<DetectionClass, String> detectionClassNameColumn;
    @FXML
    TableColumn<DetectionClass, Integer> detectionClassIdColumn;
    @FXML
    TextField sessionIdField;
    @FXML
    Spinner<Integer> xOffsetSpinner, yOffsetSpinner, heightSpinner, widthSpinner;
    @FXML
    CheckBox normalizeCheckbox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableView();
    }
    @FXML
    private void initTableView(){
        this.classTableView.setItems(FXCollections.observableList(new ArrayList<>()));
        this.detectionClassList = this.classTableView.getItems();
        this.classTableView.setEditable(true);
        detectionClassNameColumn.setCellValueFactory(
                new PropertyValueFactory<DetectionClass, String>("detectionClassName")
        );
        detectionClassNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        detectionClassIdColumn.setCellValueFactory(
                new PropertyValueFactory<DetectionClass, Integer>("detectionClassId")
        );
    }

    @FXML
    private void addDetectionClass(){
        int id = detectionClassList.size()+1;

        detectionClassList.add(
                new DetectionClass(
                        "new Entry"+id,
                        id
                )
        );
    }

    @FXML
    private void removeDetectionClass(){
        int index = classTableView.getSelectionModel().getSelectedIndex();
        detectionClassList.remove(index);
        for (int i = 0; i < detectionClassList.size(); i++){
            ((DetectionClass)detectionClassList.get(i)).setDetectionClassId(i+1);
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        this.ds = new DetectionSettingsBuilder()
                .setDetectionClasses( createClassMap() )
                .setSessionId( checkForCustomSessionId() )
                .withXOffset( this.xOffsetSpinner.getValue())
                .withYOffset( this.yOffsetSpinner.getValue())
                .setCaptureHeight(this.heightSpinner.getValue())
                .setCaptureWidth(this.widthSpinner.getValue())
                .withNormalizeCoordinates(normalizeCheckbox.isSelected())
                .createDetectionSettings();
        App.setRoot("secondary", this.ds);
    }
    private String checkForCustomSessionId(){
        return this.sessionIdField.getText().isEmpty() ? (System.nanoTime()+"") : this.sessionIdField.getText();
    }
    private Map<String, DetectionClass> createClassMap(){
        Map<String, DetectionClass> detectionClassMap = new HashMap<>();
        for (Object d:
             this.detectionClassList) {
            detectionClassMap.put(    ((DetectionClass)d).getDetectionClassName(), (DetectionClass) d);
        }
        return detectionClassMap;
    }

    public void resizeWindow(){
        Window window = primaryRoot.getScene().getWindow();
        window.setHeight(800);
        window.setWidth(800);
    }

    @FXML
    private void exit() {
        App.exit();
    }

    @Override
    public DetectionSettings getDetectionSettings() {
        return this.ds;
    }

    @Override
    public void setDetectionSettings(DetectionSettings ds) {
        this.ds = ds;
        updateSettings();
    }
    private void updateSettings(){
        this.detectionClassList = FXCollections.observableList( new ArrayList(this.ds.getDetectionClasses().values()) );
        this.classTableView.setItems(detectionClassList);
        this.sessionIdField.setText(this.ds.getSessionId());
        this.xOffsetSpinner.getValueFactory().setValue(this.ds.getxOffset());
        this.yOffsetSpinner.getValueFactory().setValue(this.ds.getyOffset());
        this.heightSpinner.getValueFactory().setValue(this.ds.getCaptureHeight());
        this.widthSpinner.getValueFactory().setValue(this.ds.getCaptureWidth());
        this.normalizeCheckbox.setSelected(this.ds.isNormalizeCoordinates());
    }
}