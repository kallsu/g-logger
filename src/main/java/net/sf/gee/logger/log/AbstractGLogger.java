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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Priority;

import net.sf.gee.logger.GLogger;
import net.sf.gee.logger.formatter.Formatter;

/**
 * The Class AbstractGLogger.
 *
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 * @param <K> the key type
 */
public abstract class AbstractGLogger<K> implements GLogger<K> {

  /** Class to be logged. */
  protected Class<?> clazz = null;

  /** Java util logger. */
  protected java.util.logging.Logger utilLogger = null;

  /** Log4j logger. */
  protected org.apache.log4j.Logger log4jLogger = null;

  /** SLF4J logger. */
  protected org.slf4j.Logger slf4jLogger = null;

  /** Formatter. */
  protected Formatter<K> formatter = null;

  /**
   * Instantiates a new AbstractGLogger.
   *
   * @param clazz the clazz
   */
  public AbstractGLogger(Class<?> clazz) {
    super();

    this.clazz = clazz;
  }

  /**
   * Instantiates a new AbstractGLogger.
   *
   * @param logger the logger <code>java.util.logging.Logger</code>
   * @param clazz the clazz
   */
  public AbstractGLogger(java.util.logging.Logger logger, Class<?> clazz) {
    this(clazz);

    this.utilLogger = logger;
  }

  /**
   * Instantiates a AbstractGLogger.
   *
   * @param logger the logger <code>org.apache.log4j.Logger</code>
   * @param clazz the clazz
   */
  public AbstractGLogger(org.apache.log4j.Logger logger, Class<?> clazz) {
    this(clazz);

    this.log4jLogger = logger;
  }

  /**
   * Instantiates a AbstractGLogger.
   *
   * @param logger the logger <code>org.slf4j.Logger</code>
   * @param clazz the clazz
   */
  public AbstractGLogger(org.slf4j.Logger logger, Class<?> clazz) {
    this(clazz);

    this.slf4jLogger = logger;
  }

  /**
   * Init the default pattern value ( yyyy-MM-dd' 'HH:mm:ss.SSSZ - [CLASS.MethodName] - Log Message)
   *
   * @return the default pattern.
   */
  protected String loadDefaultPattern() {

    StringBuilder sb = new StringBuilder();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSSZ");

    sb.append(sdf.format(new Date(System.currentTimeMillis())));
    sb.append(" - [");
    sb.append(clazz.getPackage());
    sb.append(".");
    sb.append(clazz.getSimpleName());
    sb.append("] - ");

    return sb.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#isTrace()
   */
  @Override
  public final boolean isTrace() {
    if ((utilLogger != null)
        && (java.util.logging.Level.FINEST.intValue() == utilLogger.getLevel().intValue())) {
      return true;

    }
    else if (log4jLogger != null) {
      return log4jLogger.isTraceEnabled();

    }
    else if (slf4jLogger != null) {
      return slf4jLogger.isTraceEnabled();
    }

    return false;
  }


  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#isDebug()
   */
  @Override
  public final boolean isDebug() {
    if ((utilLogger != null)
        && (java.util.logging.Level.FINE.intValue() == utilLogger.getLevel().intValue())) {
      return true;

    }
    else if (log4jLogger != null) {
      return log4jLogger.isDebugEnabled();

    }
    else if (slf4jLogger != null) {
      return slf4jLogger.isDebugEnabled();
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#isInfo()
   */
  @Override
  public boolean isInfo() {
    if ((utilLogger != null)
        && (java.util.logging.Level.INFO.intValue() == utilLogger.getLevel().intValue())) {
      return true;

    }
    else if (log4jLogger != null) {
      return log4jLogger.isInfoEnabled();

    }
    else if (slf4jLogger != null) {
      return slf4jLogger.isInfoEnabled();
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#isWarn()
   */
  @SuppressWarnings("deprecation")
  @Override
  public boolean isWarn() {
    if ((utilLogger != null)
        && (java.util.logging.Level.WARNING.intValue() == utilLogger.getLevel().intValue())) {
      return true;

    }
    else if (log4jLogger != null) {
      return log4jLogger.isEnabledFor(Priority.WARN);

    }
    else if (slf4jLogger != null) {
      return slf4jLogger.isWarnEnabled();
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.gee.logger.GLogger#isError()
   */
  @SuppressWarnings("deprecation")
  @Override
  public boolean isError() {
    if ((utilLogger != null)
        && (java.util.logging.Level.SEVERE.intValue() == utilLogger.getLevel().intValue())) {
      return true;

    }
    else if (log4jLogger != null) {
      return log4jLogger.isEnabledFor(Priority.ERROR);

    }
    else if (slf4jLogger != null) {
      return slf4jLogger.isWarnEnabled();
    }

    return false;
  }

  /**
   * Gets the formatter.
   *
   * @return the formatter
   */
  public final Formatter<K> getFormatter() {
    return formatter;
  }

  /**
   * Sets the formatter.
   *
   * @param formatter the formatter
   */
  public final void setFormatter(Formatter<K> formatter) {
    this.formatter = formatter;
  }
}
