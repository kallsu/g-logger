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

import org.junit.Assert;
import org.junit.Test;

import net.sf.gee.logger.factory.GLogFactory;
import net.sf.gee.logger.log.SimpleGLogger;

/**
 * @author Giorgio Desideri giorgio.desideri@gmail.com
 *
 */
public class GLogFactoryTest {

  public void testGetInstance() {
    Assert.assertNotNull(GLogFactory.getInstance());
  }

  @Test
  public void testGetLogger() {
    SimpleGLogger logger =
        GLogFactory.getInstance().getLogger(SimpleGLogger.class, GLogFactoryTest.class, null);

    Assert.assertNotNull(logger);
    Assert.assertTrue(logger instanceof SimpleGLogger);

    logger.logDebug("Debug message");

    logger = GLogFactory.getInstance().getLogger(SimpleGLogger.class, GLogFactoryTest.class);
    Assert.assertNotNull(logger);
    Assert.assertTrue(logger instanceof SimpleGLogger);

    logger.logDebug("Debug message");

    try {
      Assert.assertNull(GLogFactory.getInstance().getLogger(Integer.class, GLogFactoryTest.class));
    }
    catch (Exception e) {
      Assert.fail(e.getMessage());
    }


  }

}
