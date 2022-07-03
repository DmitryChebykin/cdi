package org.demo.cdi;

import org.demo.cdi.service.Printable;

import javax.inject.Inject;

public class InjectionPoint {

    @Inject
    Printable printable;

    void letsNotCallItPrintButItPrints() {
        printable.print();
    }
}
