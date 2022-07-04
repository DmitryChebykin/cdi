package org.demo.cdi.service;

import org.demo.cdi.printer.Validator;

import javax.inject.Singleton;

@Validator
 @Singleton
public class PrintableTwo implements Printable {

    @Override
    public void print() {
        System.out.println("Hello says " + this.getClass().getCanonicalName() + "!");
    }
}
