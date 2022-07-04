package org.demo.cdi.extention;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class StartupBeanExtension implements Extension {
    private final Set<Bean<?>> startupBeans = new LinkedHashSet<>();

    <X> void processBean(@Observes ProcessBean<X> event) {
        Bean<X> bean = event.getBean();
        Annotated annotated = event.getAnnotated();
        if (annotated.isAnnotationPresent(StartupWeld.class) &&
                annotated.isAnnotationPresent(ApplicationScoped.class)) {
            startupBeans.add(bean);
        }
    }

    void afterDeploymentValidation(@Observes AfterDeploymentValidation event, BeanManager manager) {
        for (Bean<?> bean : startupBeans) {
            // the call to toString() is a cheat to force the bean to be initialized
            manager.getReference(bean, bean.getBeanClass(), manager.createCreationalContext(bean)).toString();
        }
    }
}