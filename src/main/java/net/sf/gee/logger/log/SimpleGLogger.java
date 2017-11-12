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
package net.sf.gee.logger.log;

import java.util.logging.Level;

import net.sf.gee.logger.GLogger;
import net.sf.gee.logger.formatter.StringFormatter;

/**
 * The Class SimpleGLogger.
 *
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 */
public class SimpleGLogger extends AbstractGLogger<String> implements GLogger<String> {

  /**
   * Instantiates a new simple g logger.
   *
   * @param clazz the clazz
   */
  public SimpleGLogger(Class<?> clazz) {
    super(clazz);

    setFormatter(new StringFormatter());
  }

  /**
   * Instantiates a new simple g logger.
   *
   * @param logger the logger
   * @param clazz the clazz
   */
  public SimpleGLogger(java.util.logging.Logger logger, Class<?> clazz) {
    super(logger, clazz);

    setFormatter(new StringFormatter());
  }

  /**
   * Instantiates a new simple g logger.
   *
   * @param logger the logger
   * @param clazz the clazz
   */
  public SimpleGLogger(org.apache.log4j.Logger logger, Class<?> clazz) {
    super(logger, clazz);

    setFormatter(new StringFormatter());
  }

  /**
   * Instantiates a new simple g logger.
   *
   * @param logger the logger
   * @param clazz the clazz
   */
  public SimpleGLogger(org.slf4j.Logger logger, Class<?> clazz) {
    super(logger, clazz);

    setFormatter(new StringFormatter());
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logTrace(java.lang.Object, java.lang.Object[])
   */
  @Override
  public void logTrace(String message, Object... parameters) {
    this.logTrace(getFormatter().format(message, parameters));
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logDebug(java.lang.Object, java.lang.Object[])
   */
  @Override
  public void logDebug(String message, Object... parameters) {
    this.logDebug(getFormatter().format(message, parameters));
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logInfo(java.lang.Object, java.lang.Object[])
   */
  @Override
  public void logInfo(String message, Object... parameters) {
    this.logInfo(getFormatter().format(message, parameters));
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logWarn(java.lang.Object, java.lang.Object[])
   */
  @Override
  public void logWarn(String message, Object... parameters) {
    this.logWarn(getFormatter().format(message, parameters));
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logError(java.lang.Object, java.lang.Throwable,
   * java.lang.Object[])
   */
  @Override
  public void logError(String message, Throwable t, Object... parameters) {
    this.logError(getFormatter().format(message, parameters), t);
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logTrace(java.lang.Object)
   */
  @Override
  public void logTrace(String message) {
    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.FINEST, message);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.trace(message);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.trace(message);

    } // default
    else {
      String prefix = loadDefaultPattern();

      System.out.println(prefix + message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logDebug(java.lang.Object)
   */
  @Override
  public void logDebug(String message) {

    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.FINE, message);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.debug(message);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.debug(message);

    } // default
    else {
      String prefix = loadDefaultPattern();

      System.out.println(prefix + message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logInfo(java.lang.Object)
   */
  @Override
  public void logInfo(String message) {
    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.INFO, message);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.info(message);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.info(message);

    } // default
    else {
      String prefix = loadDefaultPattern();

      System.out.println(prefix + message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logWarn(java.lang.Object)
   */
  @Override
  public void logWarn(String message) {
    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.WARNING, message);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.warn(message);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.warn(message);

    } // default
    else {
      String prefix = loadDefaultPattern();

      System.out.println(prefix + message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logError(java.lang.Object, java.lang.Throwable)
   */
  @Override
  public void logError(String message, Throwable t) {

    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.SEVERE, message, t);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.error(message, t);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.error(message, t);

    } // default
    else {
      String prefix = loadDefaultPattern();

      System.err.println(prefix + message);
      t.printStackTrace();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logError(java.lang.Throwable)
   */
  @Override
  public void logError(Throwable t) {

    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.SEVERE, t.getMessage(), t);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.error(t);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.error(t.getMessage(), t);

    } // default
    else {
      t.printStackTrace();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#logError(java.lang.Object, java.lang.Object[])
   */
  @Override
  public void logError(String message, Object... strings) {

    if (strings != null && strings.length > 0) {
      message = String.format(message, strings);
    }

    // util logger
    if (utilLogger != null) {
      utilLogger.log(Level.SEVERE, message);

    } // log4j
    else if (log4jLogger != null) {
      log4jLogger.error(message);

    } // slf4j
    else if (slf4jLogger != null) {
      slf4jLogger.error(message);

    } // default
    else {
      System.err.println(message);
    }
  }
}
