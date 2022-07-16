package org.demo.cdi.configuration;

import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.view.AbstractFxController;
import org.demo.cdi.view.UserUiSlaveFXMLController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@Slf4j
@ApplicationScoped
public class FXMLControllerProducer {

    @Produces
    @SingletonBean
    public UserUiSlaveFXMLController slaveFXMLController() throws IOException {
        return AbstractFxController.init(new Stage(), "slave.fxml");
    }
}
