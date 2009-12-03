package cn.googift.crawler.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import java.util.Collection;

public class AppConfig extends XMLConfiguration {
    /**
     * Constructor
     *
     * @param fileName Configuration file name.
     */
    public AppConfig(String fileName) {
        init(fileName);
    }

    /**
     * Initialize the class.
     *
     * @param fileName Configuration file name.
     */
    private void init(String fileName) {
        setFileName(fileName);
        try {
            load();
        } catch (ConfigurationException configEx) {
            configEx.printStackTrace();
        }
    }



    public static void main(String args[]) {
        AppConfig config = new AppConfig("");
        System.out.println(config.getString("database.user-name"));
        System.out.println(config.getString("database.password"));

        Object obj = config.getProperty("lists.list");
        if (obj instanceof Collection) {
            int size = ((Collection) obj).size();
            for (int i = 0; i < size; i++) {
                System.out.println(config.getProperty("lists.list(" + i + ")"));
            }
        } else if (obj instanceof String) {
            System.out.println(config.getProperty("lists.list"));
        }

        obj = config.getProperty("batch-job.job.name");
        if (obj instanceof Collection) {
            int size = ((Collection) obj).size();
            for (int i = 0; i < size; i++) {
                System.out.println(config.getProperty("batch-job.job(" + i + ").name"));
            }
        } else if (obj instanceof String) {
            System.out.println(config.getProperty("batch-job.job.name"));
        }
    }

}




