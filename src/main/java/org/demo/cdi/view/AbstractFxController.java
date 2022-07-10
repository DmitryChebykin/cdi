package org.demo.cdi.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Setter
@Getter
public abstract class AbstractFxController {
    private Scene scene;

    private Stage stage;

    public static <T extends AbstractFxController> T init(Stage stage, String fxmlSource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        URL resource = Thread.currentThread().getContextClassLoader().getResource(fxmlSource);

        Parent rootParent = fxmlLoader.load(Objects.requireNonNull(resource).openStream());

        Scene scene = new Scene(rootParent);
        scene.setFill(Color.WHITE);

        stage.setScene(scene);

        T controller = fxmlLoader.getController();

        controller.setScene(scene);
        controller.setStage(stage);

        return controller;
    }

    public abstract void initialize();
}
