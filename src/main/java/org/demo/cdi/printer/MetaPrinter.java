package org.demo.cdi.printer;

import org.demo.cdi.service.Printable;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Default
public class MetaPrinter implements Printable {

    @Validator
    @Inject
    private Instance<Printable> allPrintables;

    private final List<Printable> cachedPrintables = new CopyOnWriteArrayList<>();

    @Override
    public void print() {
        getPrintablesSynchronized().forEach(Printable::print);
    }

    private synchronized List<Printable> getPrintablesSynchronized() {
        if (cachedPrintables.isEmpty()) {
            allPrintables.forEach(cachedPrintables::add);
        }
        return cachedPrintables;
    }
}
