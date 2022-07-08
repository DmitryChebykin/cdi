package org.demo.cdi.extension;

import org.demo.cdi.service.MessageServiceImpl;
import org.demo.cdi.service.MockedMessageServiceImpl;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConditionalBeanExtension implements Extension {

    public void observePAT1(@Observes ProcessAnnotatedType<MessageServiceImpl> pat) throws IOException {
        // resolve your configuration option, you can alternatively place this login into no-args constructor and re-use
        String vmArgumentType = loadVmArg();
        // if the arg does not equal this impl, we do not want it
        if (!vmArgumentType.equals(MessageServiceImpl.class.getSimpleName())) {
            pat.veto();
        }
    }

    private String loadVmArg() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        return prop.getProperty("test");
    }

    public void observePAT2(@Observes ProcessAnnotatedType<MockedMessageServiceImpl> pat) throws IOException {
        // resolve your configuration option, you can alternatively place this login into no-args constructor and re-use
        String vmArgumentType = loadVmArg();
        // if the arg does not equal this impl, we do not want it
        if (!vmArgumentType.equals(MockedMessageServiceImpl.class.getSimpleName())) {
            pat.veto();
        }
    }
}
