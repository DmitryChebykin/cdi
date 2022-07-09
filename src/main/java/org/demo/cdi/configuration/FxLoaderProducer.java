package org.demo.cdi.configuration;

import javafx.fxml.FXMLLoader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@ApplicationScoped
public class FxLoaderProducer {
    @Inject
    Instance<Object> instance;

    @Produces
    @Singleton
    public FXMLLoader fxmlLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(param -> instance.select(param).get());
        return loader;
    }
}
