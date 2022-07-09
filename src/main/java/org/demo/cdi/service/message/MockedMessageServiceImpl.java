package org.demo.cdi.service.message;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.demo.cdi.view.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MockedMessageServiceImpl implements MessageService {

    @Inject
    AppController appController;

    @Override
    public void changeMessage() {
        Platform.runLater(() -> {
            Stage stage = appController.getStage();
            System.out.println("stage = " + stage.toString());
            System.out.println("appController = " + appController.toString());

            Text text = appController.getText();
            text.setText("Это тест MockedMessageServiceImpl. Можно закрывать прогу.");
            text.setFill(Paint.valueOf("red"));
        });
    }
}

