package org.demo.cdi.startupbean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.util.stream.IntStream;

@ApplicationScoped
public class ConsolePrinterStarter {

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        IntStream.range(1, 101).forEach(e -> System.out.println("Программист A. не читатель, он писатель"));
    }
}
