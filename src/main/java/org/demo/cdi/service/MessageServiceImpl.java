package org.demo.cdi.service;

import javafx.application.Platform;
import org.demo.cdi.fxmlcontroller.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MessageServiceImpl implements MessageService {
    @Inject
    private AppController appController;

    @Override
    public void changeMessage() {

        Platform.runLater(() -> appController.setWelcomeText("Еще раз привет привет мамкиным программистам. Можно закрывать прогу."));


    }
}
