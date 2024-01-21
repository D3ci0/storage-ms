package com.gamma.storage.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "gamma.storage.filesystem")
@Component
public class FileSystemStorageConfig {
    private Map<String, String> config;

    public FileSystemStorageConfig() {
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
