package org.demo.cdi;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootApp.class)
                .initializers(new ProgrammaticApplicationContextInitializer())
                .run(args);
    }

    @Bean
    public CommandLineRunner getRunner(ApplicationContext applicationContext) {
        return (args) -> {
            String[] strings = applicationContext.getBeanDefinitionNames();
            Arrays.stream(strings).forEach(e -> System.out.println(e));
            Object dbConfig = applicationContext.getBean("dbConfig");
            System.out.println(dbConfig);

            System.out.println("Print all singleton bean");

            AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

            if (autowireCapableBeanFactory instanceof SingletonBeanRegistry) {
                String[] singletonNames = ((SingletonBeanRegistry) autowireCapableBeanFactory).getSingletonNames();
                for (String singleton : singletonNames) {
                    System.out.println(singleton);
                }
            }
        };
    }
}
