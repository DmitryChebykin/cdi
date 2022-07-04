package org.demo.cdi.startpoint;

import org.demo.cdi.printer.DefaultPrinter;
import org.demo.cdi.service.Printable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InjectionPoint {

    @Inject
    @DefaultPrinter
    private Printable printable;

    public void letsNotCallItPrintButItPrints() {
        printable.print();
    }
}
