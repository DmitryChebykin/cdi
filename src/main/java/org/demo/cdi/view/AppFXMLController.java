package org.demo.cdi.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import org.demo.cdi.configuration.SingletonBean;

import javax.inject.Inject;

@Getter
@Setter
@SingletonBean
public class AppFXMLController {
    private AppFXMLControllerService appFXMLControllerService;

    @FXML
    private Text text;

    @FXML
    private Label label;

    @FXML
    private TextField txtField;

    @Inject
    public AppFXMLController(AppFXMLControllerService appFXMLControllerService) {
        this.appFXMLControllerService = appFXMLControllerService;
    }

    public void changeText(String text) {
        this.text.setText(text);
        this.text.setFill(Paint.valueOf("red"));
    }

    @FXML
    private void showTooltip(ActionEvent event) {
        appFXMLControllerService.onMouseHoverEvent(txtField, "Тултип");
    }
}
