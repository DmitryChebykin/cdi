package org.demo.cdi.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUiSlaveFXMLController extends AbstractFxController {
    @FXML
    private Text text;

    @Override
    public void initialize() {

    }
}
