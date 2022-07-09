package org.demo.cdi.service.device;

import javax.enterprise.context.ApplicationScoped;

@PrintIdentity
@ApplicationScoped
public class PrintableOne implements Printable {

    @Override
    public void print() {
        System.out.println("Hello says " + this.getClass().getCanonicalName() + "!");
    }
}
