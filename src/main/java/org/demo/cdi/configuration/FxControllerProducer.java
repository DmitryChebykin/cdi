package org.demo.cdi.configuration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.view.AppFXMLController;
import org.demo.cdi.view.AppFXMLControllerService;

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
    AppFXMLControllerService service;

    @Produces
    @Singleton
    public AppFXMLController appController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.fxml");
            Parent fxmlParent = fxmlLoader.load(resourceAsStream);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlParent);
            stage.setScene(scene);
            AppFXMLController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.setScene(scene);
            controller.setAppFXMLControllerService(service);
            System.out.println("stage = " + stage.toString());
            System.out.println("appController = " + controller.toString());
            return controller;
        } catch (IOException e) {
            log.error("Ошибка чтения fxml {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
