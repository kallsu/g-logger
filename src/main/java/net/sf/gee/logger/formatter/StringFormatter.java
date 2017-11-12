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
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 *
 */
public class StringFormatter implements Formatter<String> {

  /**
   * 
   */
  public StringFormatter() {}

  @Override
  public java.lang.String format(String message, Object[] arguments) {

    // check message
    if ((message != null) && (!"".equals(message))) {

      // check parameter values
      if (arguments != null && arguments.length > 0) {

        // format all string
        return String.format(message, arguments);

      } // return input message
      else {
        return message;
      }

    } // message is empty
    else {
      return "";
    }
  }

  @Override
  public java.lang.String format(String message) {
    // check message
    if ((message != null) && (!"".equals(message))) {
      return message;

    } // message is empty
    else {
      return "";
    }
  }

}
