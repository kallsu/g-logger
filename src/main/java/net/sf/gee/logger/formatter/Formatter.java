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
package net.sf.gee.logger.formatter;

/**
 * Formatter interface.
 * 
 * @param <K> is the input object to format.
 *
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 */
public interface Formatter<K> {

  /**
   * Format object as {@linkplain java.lang.String}
   *
   * @param object the object to format
   * @param arguments the object array of arguments.
   * 
   * @return if there are some errors than it returns an empty string, otherwise the formatted
   *         string.
   */
  public String format(K object, Object[] arguments);

  /**
   * Format object as {@linkplain java.lang.String}
   *
   * @param object the object to format
   * 
   * @return if there are some errors than it returns an empty string, otherwise the formatted
   *         string.
   */
  public String format(K object);
}
