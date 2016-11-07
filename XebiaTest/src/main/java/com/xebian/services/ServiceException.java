package com.xebian.services;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1987435874401611658L;

	private String errorCode="Unknown_Exception";

	public ServiceException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}

	public String getErrorCode(){
		return this.errorCode;
	}
	
	public static void processFileErrorCodes(ServiceException e) throws ServiceException {
		switch(e.getErrorCode()){
		case "Empty_FILE_EXCEPTION":
			System.out.println("Empty File detected.");
			break;
		case "Format_FILE_EXCEPTION":
			System.out.println("File format not accepted.");
			break;
		case "FILE_NOT_FOUND_EXCEPTION":
			System.out.println("File Not Found.");
			throw e;
		case "FILE_CLOSE_EXCEPTION":
			System.out.println("File Close failed.");
			break;
		case "FILE_INPUT_OUTPUT_EXCEPTION":
			System.out.println("File Input Output failed.");
			break;
		case "FILE_INSTRUCTIONS_EXCEPTION":
			System.out.println("File insctructions line not accepted.");
			break;
		default:
			System.out.println("Unknown exception occured : "+e.getMessage()); //log it for further debugging
			e.printStackTrace();
		}
	}
	
	public static void processSurfaceErrorCodes(ServiceException e) throws ServiceException {
		switch(e.getErrorCode()){
		case "ILLEGAL_ARGUMENT_EXCEPTION":
			System.out.println("Surface arguments not correct.");
			break;
		case "NEGATIVE_SIZE_EXCEPTION":
			System.out.println("Negatives params not allowed.");
			break;
		case "NUMBER_FORMAT_EXCEPTION":
			System.out.println("params not allowed.");
			break;
		default:
			System.out.println("Unknown exception occured : "+e.getMessage()); //log it for further debugging
			e.printStackTrace();
		}
	}
}
