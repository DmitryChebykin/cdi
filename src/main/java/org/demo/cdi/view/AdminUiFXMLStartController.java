package org.demo.cdi.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.demo.cdi.configuration.SingletonBean;

import javax.inject.Inject;

@Getter
@Setter
@SingletonBean
public class AdminUiFXMLStartController {
    private Stage stage;

    @Inject
    private AdminUiFXMLStartControllerService adminUiFXMLStartControllerService;

    @FXML
    private Text text;

    public void switchToUserUi(ActionEvent event) {
        adminUiFXMLStartControllerService.switchToUserUi(event);
    }
}

