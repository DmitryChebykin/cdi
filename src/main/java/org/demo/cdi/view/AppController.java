package org.demo.cdi.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppController {
    private Scene scene;

    private Stage stage;

    @FXML
    private Text text;

    @FXML
    private Label label;

    @FXML
    private TextField txtField;

    public void setText(String text) {
        this.text.setText(text);
        this.text.setFill(Paint.valueOf("red"));
    }

    @FXML
    public void onAction(ActionEvent event) {

    }
}
