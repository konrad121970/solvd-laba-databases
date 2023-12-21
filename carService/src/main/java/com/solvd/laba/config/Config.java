package com.solvd.laba.config;

import java.io.InputStream;
import java.util.Properties;

public enum Config {
    DRIVER("driver"),
    URL("url"),
    USER("user"),
    PASSWORD("password"),
    POOL_SIZE("poolSize");

    private final String key;
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            // load properties from class path
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Config(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return properties.getProperty(key);
    }
}