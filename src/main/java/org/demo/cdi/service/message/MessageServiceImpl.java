package org.demo.cdi.service.message;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.demo.cdi.view.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MessageServiceImpl implements MessageService {
    @Inject
    FXMLLoader fxmlLoader;

    @Override
    public void changeMessage() {

        Platform.runLater(() -> fxmlLoader.<AppController>getController().setText("Еще раз привет привет мамкиным программистам. Можно закрывать прогу."));
    }
}
