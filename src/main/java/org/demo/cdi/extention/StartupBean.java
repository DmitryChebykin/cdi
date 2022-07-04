package org.demo.cdi.extention;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@StartupWeld
@Slf4j
public class StartupBean {
    @PostConstruct
    public void onStartup()//  @Startup or  @Observes final ContainerInitialized event as args
    {
        log.info("Application starting up. PostConstruct for ContainerInitialized");
    }
}
