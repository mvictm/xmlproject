package com.max.xml.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 1 on 09.07.2018.
 */
public class PathToFolder {
    public static String getRootPath() {
        Properties property = new Properties();
        String root = "";

        try (InputStream fis = PathToFolder.class.getClassLoader().getResourceAsStream("settings.properties");) {

            property.load(fis);
            root = property.getProperty("root");
            File rootDirectory = new File(root);

            if (!rootDirectory.exists()) {
                rootDirectory.mkdirs();
            } else {
                System.out.println("Repeated take "+root+" path");
            }
        } catch (IOException e) {
            System.err.println("Property file is not found!");
        }
        return root;
    }

    public static String getPathDOMFile(){
        return "/DOMFile.xml";
    }

    public static String getPathSAXFile(){
        return "/SAXFile.xml";
    }

    public static String getPathJDOMFile(){
        return "/JDOMFile.xml";
    }

    public static String getPathStAXFile(){
        return "/StAXFile.xml";
    }

    public static String getPathXPATHFile(){
        return "/XPATHFile.xml";
    }
}
