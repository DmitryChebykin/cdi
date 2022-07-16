package org.demo.cdi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootApp.class)
                .initializers(new ProgrammaticApplicationContextInitializer())
                .run(args);
    }
}
