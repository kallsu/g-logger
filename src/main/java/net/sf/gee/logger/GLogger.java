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
package net.sf.gee.logger;

/**
 * Interface for loggers.
 *
 * @param <K> the log object type
 *
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 * 
 */
public interface GLogger<K> {

  /**
   * Log trace message.
   *
   * @param message message with markup
   * @param strings markup values
   */
  public void logTrace(K message, Object... strings);

  /**
   * Log debug message.
   *
   * @param message message with markup
   * @param parameters the parameters
   */
  public void logDebug(K message, Object... parameters);

  /**
   * Log info message.
   *
   * @param message message with markup
   * @param strings markup values
   */
  public void logInfo(K message, Object... strings);

  /**
   * Log warn message.
   *
   * @param message message with markup
   * @param strings markup values
   */
  public void logWarn(K message, Object... strings);

  /**
   * Log error with this parameters.
   *
   * @param message error message with markup
   * @param cause {@linkplain java.lang.Throwable}
   * @param strings markup values
   */
  public void logError(K message, Throwable cause, Object... strings);

  /**
   * Log trace message.
   *
   * @param message trace message
   */
  public void logTrace(K message);

  /**
   * Log debug message.
   *
   * @param message debug message
   */
  public void logDebug(K message);

  /**
   * Log info message.
   *
   * @param message info message
   */
  public void logInfo(K message);

  /**
   * Log warn message.
   *
   * @param message warn message
   */
  public void logWarn(K message);

  /**
   * Log error with this parameters.
   *
   * @param message error message
   * @param cause {@linkplain java.lang.Throwable}
   */
  public void logError(K message, Throwable cause);

  /**
   * Log error.
   * 
   * @param cause {@linkplain java.lang.Throwable}
   */
  public void logError(Throwable cause);

  /**
   * Log error.
   * 
   * @param message
   * @param strings
   */
  public void logError(K message, Object... strings);

  /**
   * Checks if is trace level.
   *
   * @return true, if is trace
   */
  public boolean isTrace();

  /**
   * Checks if is in debug level.
   *
   * @return true, if is debug
   */
  public boolean isDebug();


  /**
   * Checks if is trace level.
   *
   * @return true, if is info level.
   */
  public boolean isInfo();

  /**
   * Checks if is warn level.
   *
   * @return true, if is trace
   */
  public boolean isWarn();

  /**
   * Checks if is error level.
   *
   * @return true, if is trace
   */
  public boolean isError();
}
