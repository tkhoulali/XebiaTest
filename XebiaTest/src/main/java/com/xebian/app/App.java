package com.xebian.app;

import com.xebian.services.FileService;
import com.xebian.services.ServiceException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ServiceException {

		try {
			FileService.executeFile("csvFiles/csvToRead.csv");
		} catch (ServiceException e) {
			ServiceException.processFileErrorCodes(e);
		}
	}
}
