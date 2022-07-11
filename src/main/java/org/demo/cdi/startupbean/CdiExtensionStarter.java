package org.demo.cdi.startupbean;

import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.event.MyEvent;
import org.demo.cdi.service.message.MessageService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@StartupWeld
@Slf4j
public class CdiExtensionStarter {
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @Inject
    private MessageService messageService;

    private void changeMessage() {
        messageService.changeMessage();
    }

    public void onStartup(@Observes MyEvent myEvent)//  @Startup or  @Observes final ContainerInitialized event as args
    {
        log.info("Application starting up. PostConstruct for Extension");

        Runnable task = this::changeMessage;

        scheduledExecutorService.schedule(task, 10, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }
}
