package org.demo.cdi;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.demo.cdi.event.ApplicationStartupEvent;
import org.demo.cdi.injectionpoint.InjectionPoint;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;

/**
 * Hello world!
 */
@Slf4j
public class FxCdiApp extends Application {
    private CdiContainer cdiContainer;

    public static void main(String[] args) {
        log.info("Hello from main of {} !", FxCdiApp.class.getCanonicalName());
        Application.launch(args);
    }

    @Override
    public void stop() throws Exception {
        cdiContainer.shutdown();
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();

        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContext(ApplicationScoped.class);

        CDI.current().select(InjectionPoint.class).get().autoStartAction();
        CDI.current().getBeanManager().fireEvent(new ApplicationStartupEvent(primaryStage));
    }
}

