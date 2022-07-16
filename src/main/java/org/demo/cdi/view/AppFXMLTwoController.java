package org.demo.cdi.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.demo.cdi.configuration.SingletonBean;

@Getter
@Setter
@SingletonBean
public class AppFXMLTwoController {
    private Stage stage;

    @FXML
    private Text text;
}
