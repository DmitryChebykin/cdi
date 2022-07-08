package org.demo.cdi.configuration;

import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.fxmlcontroller.AbstractFxController;
import org.demo.cdi.fxmlcontroller.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.io.IOException;

@ApplicationScoped
@Slf4j
public class FxControllerProducer {

    @Produces
    @Singleton
    public AppController appController() {
        try {
            return AbstractFxController.init(new Stage(), "app.fxml");
        } catch (IOException e) {
            log.error("Ошибка чтения fxml {}", e.getMessage());
            return null;
        }
    }
}
