package org.demo.cdi.startupbean;

import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.service.MessageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@StartupWeld
@Slf4j
public class StartupBean {
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    @Inject
    private MessageService messageService;

    private void changeMessage() {
        messageService.changeMessage();
    }

    @PostConstruct
    public void onStartup()//  @Startup or  @Observes final ContainerInitialized event as args
    {
        log.info("Application starting up. PostConstruct for Extension");

        Runnable task = this::changeMessage;

        ses.schedule(task, 10, TimeUnit.SECONDS);
    }
}
