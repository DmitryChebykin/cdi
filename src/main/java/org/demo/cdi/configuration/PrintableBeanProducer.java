package org.demo.cdi.configuration;

import org.demo.cdi.printer.Validator;
import org.demo.cdi.service.Printable;
import org.demo.cdi.service.PrintableOne;
import org.demo.cdi.service.PrintableTwo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class PrintableBeanProducer {

    @Inject
    @Validator
    private PrintableOne printableOne;

    @Inject
    @Validator
    private PrintableTwo printableTwo;

    @Produces
    public List<Printable> producePrintable() {
        List<Printable> printableList = new CopyOnWriteArrayList<>();
        printableList.add(printableOne);
        printableList.add(printableTwo);
        return printableList;
    }
}

