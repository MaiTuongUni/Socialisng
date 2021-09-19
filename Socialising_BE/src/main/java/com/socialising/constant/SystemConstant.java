package com.socialising.constant;

public class SystemConstant {
	
	public static final int ACTIVE_STATUS = 1;

	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 86400000;//1h
	public static final String SIGNING_KEY = "laptrinhjavaweb";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES_KEY = "scopes";
	public static final String ROLE_CUSTOMER = "USER";
	public static final String API_VERSION = "/api";
	public static final String SUCCESS_MESSAGE = "success";
	public static final String FAIL_MESSAGE = "fail";

	//ORDER STATUS
	public static final String ORDER_CONFIRM = "CONFIRM";
	public static final String ORDER_CONFIRM_OK = "OK";
	public static final String PASSWORD_DEFAULT = "123456";
	public static final String ORDER_PAID = "PAID";

}
