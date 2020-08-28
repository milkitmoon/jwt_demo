package com.milkit.app.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ErrorCode {
	
	//정상
	public static final int OK = 0;

	//검증
	public static final int ValidateException = 301;
	public static final int Invalid = 302;
	public static final int AttemptAuthenticationException = 303;


	public static final int RESPONSE_OK = 200;
	public static final int RESPONSE_AUTH_OK = 210;
	public static final int RESPONSE_FAIL = 500;
	
	//유저관련
	public static final int IllegalRolyType = 701;
	public static final int ExistedAlreadyUser = 702;
	public static final int NotExistUserException = 703;
	public static final int LoginParameterException = 704;
	public static final int LoginPasswordNotMatchException = 705;
	public static final int LoginUserNotValidException = 706;
	public static final int UnauthorizedException = 707;
	
	//DB관련 오류
	public static final int SQLParameterException = 881;
	public static final int BatchInsertException = 882;
	public static final int DatabaseException = 888;
	public static final int DuplicationException = 889;
	
	//기타 오류
	public static final int ServiceException = 900;
	public static final int NotSupportExcelType = 951;
	public static final int ExcelParsingFail = 952;
	public static final int ExcelHeaderParsingException = 953;
	public static final int ManyResultValueException = 954;
	public static final int UploadFilesizeLimitException = 955;
	public static final int StorageException = 956;
	
	//시스템 오류
	public static final int SystemError = 999;




	protected static Map<Integer, String> errmsg = new HashMap<Integer, String>();

	protected static void setErrorCode(Integer code, String message) {
		errmsg.put(code, message);
	}
	
	
	static {
		// 정상.
		setErrorCode(OK, "ok");
		
		// 데이터검증.
		setErrorCode(ValidateException, "ValidateException");
		setErrorCode(Invalid, "invalid");
		
		// 유저관련.
		setErrorCode(IllegalRolyType, "User.role[wrong]");
		setErrorCode(ExistedAlreadyUser, "User.alreadyExist.user");
		setErrorCode(NotExistUserException, "User.notExist.user");
		setErrorCode(LoginParameterException, "User.param[not.null]");
		setErrorCode(LoginPasswordNotMatchException, "User.authentication.FAIL");
		setErrorCode(LoginUserNotValidException, "User.authentication.VALID");
		
		//점포관리
//		setErrorCode(SendMessageFail, "SendMessageForm.SendMessageFail");
//		setErrorCode(PhoneNumberNotExist, "SendMessageForm.phoneNumberList[not.null]");
		
		// DB관련 오류
		setErrorCode(SQLParameterException, "sqlParameter");
		setErrorCode(BatchInsertException, "batchInsert");
		setErrorCode(DatabaseException, "database");
		setErrorCode(DuplicationException, "DuplicationException");
		
		// 기타 & 서비스 오류.
		setErrorCode(ServiceException, "servece");
		setErrorCode(NotSupportExcelType, "Excel.NotSupportExcelType");
		setErrorCode(ExcelParsingFail, "Excel.ExcelParsingFail");
		setErrorCode(ExcelHeaderParsingException, "Excel.ExcelHeaderParsingException");
		setErrorCode(ManyResultValueException, "Link.VAN.ManyResultValueException");
		
		// 시스템 오류.
		setErrorCode(SystemError, "system");
		
		setErrorCode(RESPONSE_OK, "RESPONSE_OK");
		setErrorCode(RESPONSE_AUTH_OK, "RESPONSE_AUTH_OK");
		setErrorCode(RESPONSE_FAIL, "RESPONSE_FAIL");
		

	};
	
	public static String getMessageKey(int errorcode) {
		return (String)errmsg.get(new Integer(errorcode));
	}
	
}