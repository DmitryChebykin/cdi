package org.demo.cdi.configuration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.view.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
@Slf4j
public class FxControllerProducer {

    @Inject
    FXMLLoader fxmlLoader;

    @Produces
    @Singleton
    public AppController appController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.fxml");
            Parent fxmlParent = fxmlLoader.load(resourceAsStream);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlParent);
            stage.setScene(scene);
            AppController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.setScene(scene);
            return controller;
        } catch (IOException e) {
            log.error("Ошибка чтения fxml {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}