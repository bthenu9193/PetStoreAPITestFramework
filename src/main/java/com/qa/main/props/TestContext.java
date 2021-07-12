package com.qa.main.props;

import java.io.IOException;
import java.util.Properties;

public class TestContext {
    public static final String category = "CATEGORY";
    public static final String status = "STATUS";

    private static PetProperties props;

    public static void init(Properties props) throws IOException {
        TestContext.props = new PetProperties(props);
    }

    public static String getCategory() {

        return props.getProperty(category);
    }
    public static String getStatus() {

        return props.getProperty(status);
    }

    private static class PetProperties extends Properties {
        private Properties properties;

        PetProperties(Properties properties) {
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
