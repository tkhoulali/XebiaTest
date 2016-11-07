package com.xebian.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.xebian.impl.Surface;
import com.xebian.services.ServiceException;

public class TestSurface {

	@Test
	public void testInitOKSurface() throws ServiceException {
		Surface surface = new Surface(10, 10);
		assertEquals(surface.checkCase(5, 5), true);
	}

	@Test
	public void testInitKOSurface() throws ServiceException {

		try {
			new Surface(-1, 10);

			fail("This should have thrown an exception");

		} catch (ServiceException e) {
			Assert.assertTrue(e.getMessage().equals("params should be positive"));
			ServiceException.processSurfaceErrorCodes(e);
		}
	}

	@Test
	public void testOutOfBoundSurface() throws ServiceException {
		Surface surface = new Surface(20, 20);
		assertEquals(surface.checkCase(50, 50), false);
	}

	@Test
	public void testInBoundSurface() throws ServiceException {
		Surface surface = new Surface(20, 20);
		assertEquals(surface.checkCase(15, 18), true);
	}
}
