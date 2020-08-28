package com.milkit.app.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.milkit.app.common.ErrorCode;


@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

	private int code = ErrorCode.ServiceException;
	
	private Object[] objs;
	

	public ServiceException() {
		super("서비스 오류입니다.");
	}
	
	public ServiceException(int code) {
//		super( ErrorCode.getMessage(code) );
		this();
		this.code = code;
	}
	
	public ServiceException(int code, Object[] objs) {
//		super( ErrorCode.getMessage(code) );
		this();
		this.code = code;
		this.objs = objs;
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
    public ServiceException(int code, String message) {
    	super(message);
        this.code = code;
    }
    
	public ServiceException(int code, String message, Object[] objs) {
		super( message );
		this.code = code;
		this.objs = objs;
	}
    
    public ServiceException(int code, String message, Throwable cause) {
    	super(message, cause);
    	this.code = code;
    }
    
    public ServiceException(Throwable cause) {
    	super(cause.getMessage(), cause);
    }
    
    public ServiceException(Throwable cause, int code) {
//    	super(ErrorCode.getMessage(code), cause);
    	super(cause.getMessage(), cause);
		this.code = code;
    }
    
    public void setCode(int code) {
    	this.code = code;
    }
    public int getCode() {
		return this.code;
	}
    
	public Object[] getObjs() {
		return objs;
	}
	public void setObjs(Object[] objs) {
		this.objs = objs;
	}
    
    public String getMessage() {
/*    	
    	String errMessage = null;
    	if (getObjs() != null) {
       		errMessage = ErrorCode.getMessage(getCode(), getObjs());

    	} else {
    		String argMessage = super.getMessage();
    		if(argMessage != null && !argMessage.equals("")) {
    			errMessage = argMessage;
    		} else {
    			errMessage = ErrorCode.getMessage(getCode());
    		}
    	}

    	return errMessage;
*/
    	return super.getMessage();
    }

	public String getCodeString() {
		return Integer.toString(this.code);
	}
	

	public String getCauseMessage() {
		String retMessage = "[" + this.getCode() + "]";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		super.printStackTrace(ps);
		
		retMessage += baos.toString().replaceAll("\n", " ");
		
		return retMessage;
	}

}
