package org.demo.cdi.service.message;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.demo.cdi.view.UserUiFxmlStartController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MockedMessageServiceImpl implements MessageService {

    @Inject
    UserUiFxmlStartController userUiFxmlStartController;

    @Override
    public void changeMessage() {
        Platform.runLater(() -> {
            System.out.println("appController = " + userUiFxmlStartController.toString());

            Text text = userUiFxmlStartController.getText();
            if (text != null) {
                text.setText("Это тест MockedMessageServiceImpl. Можно закрывать прогу. Можно закрывать прогу. Или нажмите кнопку или повводите email");
                text.setFill(Paint.valueOf("red"));
            }
        });
    }
}

