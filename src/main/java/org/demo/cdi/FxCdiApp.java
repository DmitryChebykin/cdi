package org.demo.cdi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.demo.cdi.event.ApplicationStartupEvent;
import org.demo.cdi.injectionpoint.InjectionPoint;
import org.demo.cdi.view.AppFXMLController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import java.net.URL;

/**
 * Hello world!
 */
@Slf4j
public class FxCdiApp extends Application {
    private CdiContainer cdiContainer;

    private Parent rootNode;

    public static void main(String[] args) {
        log.info("Hello from main of {} !", FxCdiApp.class.getCanonicalName());
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();

        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContext(ApplicationScoped.class);

        URL fxmlLocation = getClass().getResource("/app.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        fxmlLoader.setControllerFactory(param -> CDI.current().select(param).get());
        rootNode = fxmlLoader.load();
    }

    @Override
    public void stop() throws Exception {
        cdiContainer.shutdown();
    }

    @Override
    public void start(Stage primaryStage) {
        CDI.current().select(AppFXMLController.class).get().setStage(primaryStage);
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
        CDI.current().select(InjectionPoint.class).get().autoStartAction();
        CDI.current().getBeanManager().fireEvent(new ApplicationStartupEvent(primaryStage));
    }
}

