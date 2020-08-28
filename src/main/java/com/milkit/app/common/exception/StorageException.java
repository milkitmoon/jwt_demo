package com.milkit.app.common.exception;

import com.milkit.app.common.ErrorCode;

public class StorageException extends ServiceException {
	
	private static final long serialVersionUID = 982309731424817548L;
	
	
	private static int code = ErrorCode.StorageException;
	
	
	public StorageException() {
		super(code, "파일저장 시 오류가 발생했습니다.");
	}
	
	public StorageException(long filesize) {
		super(code, "파일저장 시 오류가 발생했습니다.", new Object[]{filesize});
	}
	
	public StorageException(String message) {
		super(code, message);
	}

}
