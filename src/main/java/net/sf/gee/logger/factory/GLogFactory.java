/**
 * -------------------------------------------------------------------------------------------------
 * 
 * Copyright 2015 - Giorgio Desideri
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * 
 * See the License for the specific language governing permissions and limitations under the
 * License.
 * 
 */
package net.sf.gee.logger.factory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.BasicConfigurator;

import net.sf.gee.logger.GLogger;
import net.sf.gee.logger.formatter.Formatter;
import net.sf.gee.logger.log.AbstractGLogger;
import net.sf.gee.logger.log.SimpleGLogger;

/**
 * A factory for creating GLog objects.
 *
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 */
public class GLogFactory {

  public static final String GLOG_CONF_FILE_NAME = "glog.properties";

  public static final String GLOG_TYPE_VAR_NAME = "glog.logger.type";
  public static final String GLOG_TYPE_CONFIG_FILE = "glog.config.file";

  /**
   * Singleton instance
   */
  private static GLogFactory instance = null;

  private String logType = null;

  private String logTypeConfFile = null;

  /**
   * Private constructor of singleton instance
   */
  private GLogFactory() {
    init();
  }

  /**
   * Init method.
   * 
   * Search GLOG_TYPE_VAR_NAME inside JAVA_OPTS variables or inside a conf.properties file inside
   * classpath
   */
  private void init() {

    System.out.println(
        String.format("Search GLOG configuration file [%s] inside classpath", GLOG_CONF_FILE_NAME));

    // search configuration properties
    URL url = this.getClass().getClassLoader().getResource(GLOG_CONF_FILE_NAME);

    if (url != null) {
      System.out.printf("[%s] found inside classpath, try to load.", url.toString());

      Properties props = new Properties();

      try {
        // read file
        props.load(url.openStream());

        // get properties
        this.logType = (String) props.get(GLOG_TYPE_VAR_NAME);
        this.logTypeConfFile = (String) props.get(GLOG_TYPE_CONFIG_FILE);

        System.out.println("Configuration loaded.");

        System.out.println(String.format("%s : %s", GLOG_TYPE_VAR_NAME, logType));
        System.out.println(String.format("%s : %s", GLOG_TYPE_CONFIG_FILE, logTypeConfFile));

      }
      catch (IOException e) {
        // stack trace of exception
        e.printStackTrace();

        System.err.println("Use default configuration, Logger: LOG4J");

        this.logType = "LOG4J";
        this.logTypeConfFile = null;
      }

    } // Print warn informations
    else {

      System.err.println(String.format(
          "Unable to find GLOG configuration file [%s] inside classpath", GLOG_CONF_FILE_NAME));

      System.err.println("Use default configuration, Logger: LOG4J");

      this.logType = "LOG4J";
      this.logTypeConfFile = null;
    }
  }

  /**
   * Gets the single instance of GLogFactory.
   *
   * @return single instance of GLogFactory
   */
  public static synchronized GLogFactory getInstance() {
    if (instance == null) {
      instance = new GLogFactory();
    }

    return instance;
  }

  /**
   * Builds the logger.
   *
   * @param <T> the type of glogger
   * 
   * @param gLoggerClass concrete class that implements {@link GLogger}
   * @param callerClass caller class
   * 
   * @return T instance
   */
  public <T> T getLogger(Class<T> gLoggerClass, Class<?> callerClass) {
    return getLogger(gLoggerClass, callerClass, null);
  }

  /**
   * Build the default logger {@link SimpleGLogger}
   * 
   * @param callerClass
   * 
   * @return instance of {@link SimpleGLogger}
   */
  public SimpleGLogger getLogger(Class<?> callerClass) {
    return getLogger(SimpleGLogger.class, callerClass, null);
  }

  /**
   * Builds the logger.
   *
   * @param <T> the type of glogger
   * 
   * @param gLoggerClass concrete class that implements {@link GLogger}
   * @param callerClass caller class
   * @param formatter concrete class that implements {@link Formatter}
   * 
   * @return T instance
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public <T> T getLogger(Class<T> gLoggerClass, Class<?> callerClass, Formatter<T> formatter) {

    T logger = null;

    try {
      // Java util
      if ("UTIL".equalsIgnoreCase(logType)) {

        // load configuration file
        if (logTypeConfFile != null) {
          try {
            java.util.logging.LogManager.getLogManager().readConfiguration(
                this.getClass().getClassLoader().getResourceAsStream(logTypeConfFile));
          }
          catch (SecurityException | IOException e) {
            System.err.println(String.format("Error during loading GLOG configuration file [%s]",
                logTypeConfFile));
          }
        }

        // get constructor
        final Constructor<T> cstr = gLoggerClass
            .getDeclaredConstructor(java.util.logging.Logger.class, java.lang.Class.class);

        // return instance
        logger = cstr.newInstance(java.util.logging.Logger.getLogger(callerClass.getName()),
            callerClass);

      }
      // Apache Log4J
      else if ("LOG4J".equalsIgnoreCase(logType)) {

        // load configuration file
        if (logTypeConfFile != null) {
          try {

            if (logTypeConfFile.endsWith(".xml")) {
              org.apache.log4j.xml.DOMConfigurator
                  .configure(this.getClass().getClassLoader().getResource(logTypeConfFile));
            }
            else {
              org.apache.log4j.PropertyConfigurator
                  .configure(this.getClass().getClassLoader().getResource(logTypeConfFile));
            }

          }
          catch (SecurityException e) {
            System.err.println(String.format("Error during loading GLOG configuration file [%s]",
                logTypeConfFile));
          }

        } // no file
        else {
          BasicConfigurator.configure();
        }

        // get constructor
        final Constructor<T> cstr = gLoggerClass
            .getDeclaredConstructor(org.apache.log4j.Logger.class, java.lang.Class.class);

        // return instance
        logger =
            cstr.newInstance(org.apache.log4j.Logger.getLogger(callerClass.getName()), callerClass);

      }
      // Simple Log4J
      else if ("SLF4J".equalsIgnoreCase(logType)) {

        // get constructor
        final Constructor<T> cstr =
            gLoggerClass.getDeclaredConstructor(org.slf4j.Logger.class, java.lang.Class.class);

        // return instance
        logger =
            cstr.newInstance(org.slf4j.LoggerFactory.getLogger(callerClass.getName()), callerClass);

      } // default
      else {
        // log4j
        logger = (T) new SimpleGLogger(callerClass);
      }

      // check formatter
      if (formatter != null) {

        // set formatter
        ((AbstractGLogger) logger).setFormatter(formatter);
      }

    }
    catch (NoSuchMethodException | SecurityException | InstantiationException
        | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | FactoryConfigurationError e) {

      e.printStackTrace();
    }

    return logger;
  }
}
