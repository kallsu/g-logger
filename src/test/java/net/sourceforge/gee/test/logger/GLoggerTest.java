/**
 * ------------------------------------------------------------------------------------------------
 * Written by Giorgio Desideri.
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA
 * 
 **/
package net.sourceforge.gee.test.logger;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.sf.gee.logger.factory.GLogFactory;
import net.sf.gee.logger.log.SimpleGLogger;

/**
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 *
 */
public class GLoggerTest {

  @Test
  public void testSessionGLogger() {

    SimpleGLogger logger =
        GLogFactory.getInstance().getLogger(SimpleGLogger.class, GLogFactoryTest.class);

    Assert.assertNotNull(logger);

    logger.isTrace();
    logger.isDebug();
    logger.isInfo();
    logger.isWarn();
    logger.isError();

    logger.logTrace("sample message");
    logger.logDebug("sample message");
    logger.logInfo("sample message");
    logger.logWarn("sample message");
    logger.logError("sample message", new IOException("Test Exception"));

    logger.logTrace("[%s]", "sample message");
    logger.logDebug("[%s]", "sample message");
    logger.logInfo("[%s]", "sample message");
    logger.logWarn("[%s]", "sample message");
    logger.logError("[%s]", new IOException("Test Exception"), "sample message");
  }
}
