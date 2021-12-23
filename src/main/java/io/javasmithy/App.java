package io.javasmithy;

import io.javasmithy.detections.DetectionSettingsBuilder;
import io.javasmithy.detections.HasDetectionSettings;
import io.javasmithy.detections.DetectionSettings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private final String APP_TITLE = "Image Annotator";
    private final DetectionSettings DEFAULT_SETTINGS = new DetectionSettingsBuilder().createDetectionSettings();

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(getFXMLLoader("primary").load(), 800, 800);
        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml, DetectionSettings ds) throws IOException {
        FXMLLoader fxmlLoader = getFXMLLoader(fxml);
        scene.setRoot(fxmlLoader.load());
        ((HasDetectionSettings)fxmlLoader.getController()).setDetectionSettings(ds);
        ((Resizable)fxmlLoader.getController()).resizeWindow();

    }

    static void exit(){
        Platform.exit();
        System.exit(0);
    }

    private static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

}