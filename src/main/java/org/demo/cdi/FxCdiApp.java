package org.demo.cdi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.demo.cdi.event.ApplicationStartupEvent;
import org.demo.cdi.event.ShutdownEvent;
import org.demo.cdi.injectionpoint.InjectionPoint;
import org.demo.cdi.view.AppFXMLController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.CDI;
import java.io.IOException;

/**
 * Hello world!
 */
@Slf4j
@ApplicationScoped
public class FxCdiApp extends Application {
    private CdiContainer cdiContainer;

    private Parent rootNode;

    public static void main(String[] args) {
        log.info("Hello from main of {} !", FxCdiApp.class.getCanonicalName());
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        startContainer();
    }

    @Override
    public void stop() throws Exception {
        cdiContainer.shutdown();
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        CDI.current().select(AppFXMLController.class).get().setStage(primaryStage);
        Scene scene = new Scene(rootNode);
        scene.setFill(Color.WHITE);

        primaryStage.setScene(scene);
        primaryStage.show();

        CDI.current().select(InjectionPoint.class).get().autoStartAction();
        CDI.current().getBeanManager().fireEvent(new ApplicationStartupEvent(primaryStage));
    }

    private void startContainer() throws IOException {
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();
        cdiContainer.getContextControl().startContext(ApplicationScoped.class);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.fxml"));

        fxmlLoader.setControllerFactory(param -> CDI.current().select(param).get());
        rootNode = fxmlLoader.load();
    }

    private void clearParent(@Observes final ShutdownEvent shutdownEvent){
        rootNode = null;
        log.info("set root to null");
    }
}

