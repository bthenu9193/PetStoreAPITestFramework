package com.qa.main.props;

import java.io.IOException;
import java.util.Properties;

public class testContext {
    public static final String category = "CATEGORY";
    public static final String status = "STATUS";

    private static petProperties props;

    public static void init(Properties props) throws IOException {
        testContext.props = new petProperties(props);
    }

    public static String getCategory() {

        return props.getProperty(category);
    }
    public static String getStatus() {

        return props.getProperty(status);
    }

    private static class petProperties extends Properties {
        private Properties properties;

        petProperties(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String getProperty(String key) {
            String property;

            //Check for environment variable first
            property = System.getenv(key);
            if (property != null && property.length() > 0) return property;

            //Then check for java property
            property = System.getProperty(key);
            if (property != null && property.length() > 0) return property;

            //Finally get the property from properties file as last option
            return properties.getProperty(key);
        }
    }
}
