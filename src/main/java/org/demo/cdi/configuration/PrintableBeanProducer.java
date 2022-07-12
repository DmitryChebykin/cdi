package org.demo.cdi.configuration;

import org.demo.cdi.service.device.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class PrintableBeanProducer {

    @Inject
    @PrintIdentity
    private PrintableOne printableOne;

    @Inject
    @PrintIdentity
    private PrintableTwo printableTwo;

    @Inject
    @PrintIdentity
    private PrintableThree printableThree;

    @Produces
    public List<Printable> producePrintable() {
        List<Printable> printableList = new CopyOnWriteArrayList<>();
        printableList.add(printableOne);
        printableList.add(printableTwo);
        printableList.add(printableThree);
        return printableList;
    }
}

