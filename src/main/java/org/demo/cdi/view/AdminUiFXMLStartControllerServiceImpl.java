package org.demo.cdi.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.demo.cdi.UserUiFxApp;
import org.demo.cdi.configuration.SingletonBean;
import org.demo.cdi.event.ShutdownEvent;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.event.Event;
import javax.inject.Inject;

@SingletonBean
public class AdminUiFXMLStartControllerServiceImpl implements AdminUiFXMLStartControllerService {
    @Inject
    Event<ShutdownEvent> shutdownEvent;

    @Inject
    WeldContainer weldContainer;

    @Override
    public void switchToUserUi(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        shutdownEvent.fire(new ShutdownEvent());

        weldContainer.shutdown();

        Stage anotherStage = new Stage();

        Application app2;
        try {
            app2 = UserUiFxApp.class.newInstance();
            app2.init();
            app2.start(anotherStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
