package org.demo.cdi.injectionpoint;

import org.demo.cdi.printer.DefaultPrinter;
import org.demo.cdi.service.device.Printable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InjectionPoint {

    @Inject
    @DefaultPrinter
    private Printable printable;

    public void autoStartAction() {
        printable.print();
    }
}
