package org.demo.cdi.printer;

import org.demo.cdi.service.device.Printable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@DefaultPrinter
@ApplicationScoped
public class MetaPrinter implements Printable {

    private final List<Printable> cachedPrintables = new CopyOnWriteArrayList<>();

    @Inject
    private List<Printable> allPrintables;

    @Override
    public void print() {
        getPrintablesSynchronized().forEach(Printable::print);
    }

    private synchronized List<Printable> getPrintablesSynchronized() {
        if (cachedPrintables.isEmpty()) {
            allPrintables.stream().forEach(cachedPrintables::add);
        }
        return cachedPrintables;
    }
}
