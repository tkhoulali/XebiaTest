package com.xebian.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.xebian.impl.Orientation;
import com.xebian.impl.Tondeuse;
import com.xebian.services.FileService;
import com.xebian.services.ServiceException;
import com.xebian.services.TasksService;

public class TestFileService {

	@Test
	public void testFileOK() throws ServiceException {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		// List of tasks from file
		List<Callable<Tondeuse>> taches = FileService.executeFile("csvFiles/csvToRead.csv");

		// List of tasks after execution
		List<Tondeuse> tondeuses = TasksService.execute(executor, taches, "file");

		// tasks
		Tondeuse tondeuse1 = tondeuses.get(0);
		Tondeuse tondeuse2 = tondeuses.get(1);

		assertEquals(tondeuse1.getOrientation(), Orientation.N);
		assertEquals(tondeuse1.getPositionX(), 1);
		assertEquals(tondeuse1.getPositionY(), 3);

		assertEquals(tondeuse2.getOrientation(), Orientation.E);
		assertEquals(tondeuse2.getPositionX(), 5);
		assertEquals(tondeuse2.getPositionY(), 1);
	}

	// Empty file
	@Test(expected = ServiceException.class)
	public void testFileKO() throws ServiceException {

		FileService.executeFile("csvFiles/csvToRead25.csv");

		fail("Exception should be thrown here");
	}

	// commandes not correct
	@Test(expected = ServiceException.class)
	public void testFileKO2() throws ServiceException {
		ExecutorService executor = Executors.newSingleThreadExecutor();

		// List of tasks from file

		List<Callable<Tondeuse>> taches = FileService.executeFile("csvFiles/csvToRead3.csv");
		// List of tasks after execution
		List<Tondeuse> tondeuses = TasksService.execute(executor, taches, "file");

		fail("Exception should be thrown here");
	}
}
