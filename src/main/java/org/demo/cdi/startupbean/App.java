package org.demo.cdi.startupbean;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.demo.cdi.event.MyEvent;
import org.demo.cdi.view.AppController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class App {

    @Inject
    AppController appController;

    @Inject
    private Event<MyEvent> event;

    public void start(@Observes @StartupScene Stage stage) {
        Scene scene = appController.getScene();
        stage.setScene(scene);
        appController.setStage(stage);
        stage.show();
        System.out.println("stage = " + stage.toString());
        event.fire(new MyEvent());
    }
}
