module io.javasmithy {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.javasmithy to javafx.fxml;
    exports io.javasmithy;
    exports io.javasmithy.detections;
    opens io.javasmithy.detections to javafx.fxml;
}
