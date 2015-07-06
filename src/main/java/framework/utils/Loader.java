package framework.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Loader {
    static FileInputStream propFile ;
    static Properties proper = new Properties();
    static File resultFile = new File("log.txt");
    static FileWriter fileWriter = null;
    static final String propPath =System.getProperty("user.dir")+"\\src\\test\\resources\\HAYS.properties";
    private static PropertiesConfiguration config;

    public static String loadProperty(String name) {

        try {
            propFile = new FileInputStream(propPath);
            proper.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";
        if (name != null) {
            value = proper.getProperty(name);
        }
        return value;
    }


    public static void updateProperty(String propName, String propValue){
        try {
            config=new PropertiesConfiguration(propPath);
            config.setProperty(propName, propValue);
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void logWritter(String text) {
        try {
            fileWriter = new FileWriter(resultFile, true);
            fileWriter.append(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void clearResultFile(){
        try{
            fileWriter=new FileWriter(resultFile);
            fileWriter.write("");
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
