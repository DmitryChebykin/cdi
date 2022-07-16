package org.demo.cdi.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import org.demo.cdi.FxCdiAppTwo;
import org.demo.cdi.configuration.SingletonBean;
import org.demo.cdi.event.NavigationEvent;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@SingletonBean
public class AppFXMLControllerServiceImpl implements AppFXMLControllerService {
    @Inject
    @Any
    private Event<NavigationEvent> events;

    @Inject
    private WeldContainer cdiContainer;

    @Override
    public void onMouseHoverEvent(Control control, String message) {
        {
            control.setTooltip(new Tooltip(message));
            Bounds boundsInScene = control.localToScreen(control.getBoundsInLocal());
            control.getTooltip().show(control, boundsInScene.getMinX(), boundsInScene.getMaxY());
            control.getTooltip().setAutoHide(true);
        }
    }

    @Override
    public void activateSlaveView(ActionEvent actionEvent) {
        events.fire(new NavigationEvent());
    }

    @Override
    public void switchFxApp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        cdiContainer.shutdown();
        Stage anotherStage = new Stage();

        Application app2;
        try {
            app2 = FxCdiAppTwo.class.newInstance();
            app2.init();
            app2.start(anotherStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
