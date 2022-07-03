package org.demo.cdi;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Hello world!
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("Hello from main of {} !", App.class.getCanonicalName());
        Weld weld = new Weld();

        try (WeldContainer container = weld.initialize()) {
            container.select(InjectionPoint.class).get().letsNotCallItPrintButItPrints();
        }
    }
}

