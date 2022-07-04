package org.demo.cdi.printer;

import org.demo.cdi.extention.Startup;
import org.demo.cdi.service.Printable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@DefaultPrinter
@Dependent
public class MetaPrinter implements Printable {

    private final List<Printable> cachedPrintables = new CopyOnWriteArrayList<>();

    @Inject
    @Validator
    @Startup
    private Instance<Printable> allPrintables;

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
