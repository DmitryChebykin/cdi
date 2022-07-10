package org.demo.cdi.view;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import org.demo.cdi.configuration.SingletonBean;
import org.demo.cdi.event.NavigationEvent;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@SingletonBean
public class AppFXMLControllerServiceImpl implements AppFXMLControllerService {
    @Inject
    @Any
    private Event<NavigationEvent> events;

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
}
