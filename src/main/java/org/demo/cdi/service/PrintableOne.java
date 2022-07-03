package org.demo.cdi.service;

import org.demo.cdi.printer.Validator;

@Validator
public class PrintableOne implements Printable {

    @Override
    public void print() {
        System.out.println("Hello says " + this.getClass().getCanonicalName() + "!");
    }
}
