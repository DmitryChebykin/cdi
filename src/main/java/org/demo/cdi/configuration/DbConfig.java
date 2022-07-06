package org.demo.cdi.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.demo.cdi.extention.StartupWeld;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.Map;

@StartupWeld
@ApplicationScoped
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@ToString
public class DbConfig {
    private String type;

    private String version;

    @PostConstruct
    public void loadFromYml() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.yml");

        Map<String, Object> database = (Map<String, Object>) new Yaml().<Map<String, Object>>load(inputStream).get("database");

        version = (String) database.get("version");
        type = (String) database.get("type");

        log.info("DbConfig {}", this);
    }
}
