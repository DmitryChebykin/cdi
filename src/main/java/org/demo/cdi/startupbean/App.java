package org.demo.cdi.startupbean;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.demo.cdi.event.ApplicationStartupEvent;
import org.demo.cdi.event.MyEvent;
import org.demo.cdi.view.AppFXMLController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class App {

    @Inject
    AppFXMLController appFXMLController;

    @Inject
    private Event<MyEvent> event;

//    public void start(@Observes @StartupScene Stage stage) {
//        Scene scene = appFXMLController.getScene();
//        stage.setScene(scene);
//        appFXMLController.setStage(stage);
//        stage.show();
//        System.out.println("stage = " + stage);
//        System.out.println("appController = " + appFXMLController.toString());
//        event.fire(new MyEvent());
//    }

    public void start(@Observes ApplicationStartupEvent event) {
        Stage stage = event.getPrimaryStage();
        Scene scene = appFXMLController.getScene();
        stage.setScene(scene);
        appFXMLController.setStage(stage);
        stage.show();
        System.out.println("stage = " + stage);
        System.out.println("appController = " + appFXMLController.toString());
        this.event.fire(new MyEvent());
    }
}

