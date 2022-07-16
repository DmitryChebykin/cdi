package org.demo.cdi.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;

public interface AppFXMLControllerService {
    void onMouseHoverEvent(Control control, String message);

    void activateSlaveView(ActionEvent actionEvent);

    void switchFxApp(ActionEvent event);
}
