package org.demo.cdi.startupbean;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
@Slf4j
public class ContainerInitializedEventStarter {

    @PostConstruct
    public void onStartup(@Observes final ContainerInitialized event) {
        log.info("Application starting up. PostConstruct for Event");
    }
}

