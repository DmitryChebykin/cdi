package org.demo.cdi.service.device;

import org.demo.cdi.configuration.SingletonBean;

@PrintIdentity
@SingletonBean
public class PrintableThree implements Printable {

    @Override
    public void print() {
        System.out.println("Hello says " + this.getClass().getCanonicalName() + "!");
    }
}
