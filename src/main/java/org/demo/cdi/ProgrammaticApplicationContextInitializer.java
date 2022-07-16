package org.demo.cdi;

import org.demo.cdi.extension.ConditionalBeanExtension;
import org.demo.cdi.extension.StartupBeanExtension;
import org.demo.cdi.injectionpoint.InjectionPoint;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.CreationException;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.util.Set;

public class ProgrammaticApplicationContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

    public static String decapitalize(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char[] c = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);

        return new String(c);
    }

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        Weld weld = new Weld();

        weld.addExtension(new ConditionalBeanExtension());
        weld.addExtension(new StartupBeanExtension());

        WeldContainer weldContainer = weld.initialize();

        WeldContainer.current().select(InjectionPoint.class).get().autoStartAction();

        BeanManager beanManager = weldContainer.getBeanManager();

        SingletonBeanRegistry beanRegistry = applicationContext.getBeanFactory();

        Set<Bean<?>> beans = beanManager.getBeans(Object.class, new AnnotationLiteral<Any>() {
        }, new AnnotationLiteral<Default>() {
        });

        for (Bean<?> bean : beans) {

            String simpleName = bean.getBeanClass().getSimpleName();

            String packageName = bean.getBeanClass().getPackage().getName();

            if (packageName.startsWith("org.demo.cdi") && !packageName.contains("FXML")) {

                try {
                    CreationalContext<?> ctx = beanManager.createCreationalContext(bean);

                    Class<? extends Annotation> scope = bean.getScope();

                    Context context = beanManager.getContext(scope);

                    Object o = context.get(bean);

                    if (o == null) {
                        o = beanManager.getReference(bean, bean.getBeanClass(), ctx);
                    }

                    if (!packageName.startsWith("org.demo.cdi.view")) {
                        String fullClassName = o.getClass().getSimpleName();

                        String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

                        String decapitalize = decapitalize(simpleClassName);

                        beanRegistry.registerSingleton(decapitalize, o);

                        System.out.println("Register Weld bean in Spring context: " + decapitalize);
                    }
                } catch (CreationException | IllegalStateException | IllegalArgumentException ignored) {
                    System.out.println("Error register bean of " + simpleName);
                }
            }
        }
    }
}

