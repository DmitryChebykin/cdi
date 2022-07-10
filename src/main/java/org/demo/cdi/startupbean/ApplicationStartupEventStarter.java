package org.demo.cdi.startupbean;

import org.demo.cdi.event.ApplicationStartupEvent;
import org.demo.cdi.event.MyEvent;
import org.demo.cdi.view.AppFXMLController;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class ApplicationStartupEventStarter {

    @Inject
    AppFXMLController appFXMLController;

    @Inject
    private Event<MyEvent> event;

    public void start(@Observes ApplicationStartupEvent event) {
        System.out.println("appController = " + appFXMLController.toString());
        this.event.fire(new MyEvent());
    }
}

