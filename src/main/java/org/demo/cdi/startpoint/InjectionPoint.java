package org.demo.cdi.startpoint;

import org.demo.cdi.fxmlcontroller.AppController;
import org.demo.cdi.printer.DefaultPrinter;
import org.demo.cdi.service.MessageService;
import org.demo.cdi.service.Printable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InjectionPoint {
    @Inject
    private AppController appController;

    @Inject
    private MessageService messageService;

    @Inject
    @DefaultPrinter
    private Printable printable;

    public void autoStartAction() {
        appController.getStage().show();
        printable.print();
    }
}
