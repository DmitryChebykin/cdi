package org.demo.cdi;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.injectionpoint.InjectionPoint;
import org.demo.cdi.startupbean.StartupScene;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Hello world!
 */
@Slf4j
public class FxCdiApp extends Application {
    private Weld weld;

    public static void main(String[] args) {
        log.info("Hello from main of {} !", FxCdiApp.class.getCanonicalName());
        Application.launch(args);
    }

    @Override
    public void stop() throws Exception {
        weld.shutdown();
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        weld = new Weld();

        try {
            WeldContainer weldContainer = weld.initialize();

            weldContainer.select(InjectionPoint.class).get().autoStartAction();
            weldContainer.getBeanManager().fireEvent(primaryStage, new AnnotationLiteral<StartupScene>() {
            });
        } catch (Exception e) {
            log.info(e.getMessage());
            weld.shutdown();
        }
    }
}

