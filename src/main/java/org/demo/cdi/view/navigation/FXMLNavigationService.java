package org.demo.cdi.view.navigation;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.demo.cdi.configuration.SingletonBean;
import org.demo.cdi.event.NavigationEvent;
import org.demo.cdi.view.AppFXMLController;
import org.demo.cdi.view.SlaveFXMLController;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SingletonBean
public class FXMLNavigationService {
    @Inject
    private AppFXMLController appFXMLController;

    @Inject
    private SlaveFXMLController slaveFXMLController;

    public void showSlaveController(@Observes NavigationEvent navigationEvent) {
        Stage slaveFXMLControllerStage = slaveFXMLController.getStage();
        Stage appFXMLControllerStage = appFXMLController.getStage();
        Runnable task = () -> Platform.runLater(() -> {

            System.out.println(appFXMLController);
            System.out.println(slaveFXMLController);

            appFXMLControllerStage.show();
            slaveFXMLControllerStage.close();
        });

        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Platform.runLater(() -> {
            slaveFXMLControllerStage.show();

            appFXMLControllerStage.close();

            scheduledExecutorService.schedule(task, 4, TimeUnit.SECONDS);

            scheduledExecutorService.shutdown();
        });
    }
}

