package com.softserveinc.ita.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

  static Properties prop = new Properties();

  public static void readProp() throws IOException {
    prop.load(new FileInputStream("src/main/resources/config.properties"));
  }


  public String getProperty(String key) {
    return prop.getProperty(key);
  }


}