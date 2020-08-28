package com.milkit.app.common.exception;

import com.milkit.app.common.ErrorCode;

public class UploadFilesizeLimitException extends ServiceException {
	
	private static final long serialVersionUID = 982309731424817548L;
	
	
	private static int code = ErrorCode.UploadFilesizeLimitException;
	
	
	public UploadFilesizeLimitException() {
		super(code, "업로드 파일사이즈를 초과하였습니다.");
	}
	
	public UploadFilesizeLimitException(long filesize) {
		super(code, "업로드 파일사이즈를 초과하였습니다.", new Object[]{filesize});
	}

}
