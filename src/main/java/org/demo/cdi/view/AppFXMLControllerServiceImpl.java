package org.demo.cdi.view;

import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import org.demo.cdi.configuration.SingletonBean;

@SingletonBean
public class AppFXMLControllerServiceImpl implements AppFXMLControllerService {

    @Override
    public void onMouseHoverEvent(Control control, String message) {
         {
            control.setTooltip(new Tooltip(message));
            Bounds boundsInScene = control.localToScreen(control.getBoundsInLocal());
            control.getTooltip().show(control, boundsInScene.getMinX(), boundsInScene.getMaxY());
            control.getTooltip().setAutoHide(true);
    }
}}
