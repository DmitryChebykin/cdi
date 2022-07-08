package org.demo.cdi.service;

import javafx.application.Platform;
import org.demo.cdi.fxmlcontroller.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MockedMessageServiceImpl implements MessageService {
    @Inject
    private AppController appController;

    @Override
    public void changeMessage() {

        Platform.runLater(() -> appController.setWelcomeText("Это тест. Можно закрывать прогу."));
    }
}

