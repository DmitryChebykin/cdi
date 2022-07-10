package org.demo.cdi.configuration;

import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.view.AbstractFxController;
import org.demo.cdi.view.SlaveFXMLController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

@ApplicationScoped
@Slf4j
public class FxControllerProducer {

    @Produces
    @SingletonBean
    public SlaveFXMLController slaveFXMLController() throws IOException {
        return AbstractFxController.init(new Stage(), "slave.fxml");
    }
}
