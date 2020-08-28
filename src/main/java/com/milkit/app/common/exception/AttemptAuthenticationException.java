package com.milkit.app.common.exception;

import com.milkit.app.common.ErrorCode;

public class AttemptAuthenticationException extends ServiceException {

	private static int code = ErrorCode.AttemptAuthenticationException;
	
	
	public AttemptAuthenticationException() {
		super(code, "인증 시 오류가 발생했습니다.");
	}
}
