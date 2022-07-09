package org.demo.cdi.configuration;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@ApplicationScoped
@Slf4j
@ToString
public class DbConfig {
    @Inject
    @ConfigProperty(name="dataId")
    private String dataId;

    private String type;

    private String version;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
    log.info("Я стартовал, {}", this.getClass().getSimpleName());
    }

    @PostConstruct
    public void loadFromYml() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.yml");

        Map<String, Object> database = (Map<String, Object>) new Yaml().<Map<String, Object>>load(inputStream).get("database");

        version = (String) database.get("version");
        type = (String) database.get("type");

        dataId = new String(dataId.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        log.info("DbConfig {}", this);
    }
}
