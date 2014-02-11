package edu.buaa.automanager.utils;

public class Protocal {
	public final static String TAG_SEND_START = "@%@";
	public final static String TAG_SEND_END = "%@%";
	public final static String TAG_PARSE_START = TAG_SEND_END;
	public final static String TAG_PARSE_END = TAG_SEND_START;

	public static int COMMAND_TYPE;

	// public static String COMMAND_LOGIN = "1";
	// public static String COMMAND_LOGIN_RESPONSE = "2";
	// public static String COMMAND_KEEP_CONNECTION_TEST = "3";
	// public static String COMMAND_KEEP_CONNECTION_TEST_RESPONSE = "4";
	// public static String COMMAND_SEND = "5";
	// public static String COMMAND_SEND_RESPONSE = "6";
	// public static String COMMAND_TERMINAL_RESPONSE = "7";
	// public static String COMMAND_QUERY_LOCUS = "8";
	// public static String COMMAND_TOTAL_DATA_RESPONSE = "9";
	public static final int COMMAND_LOGIN = 1;
	public static final int COMMAND_LOGIN_RESPONSE = 2;
	public static final int COMMAND_KEEP_CONNECTION_TEST = 3;
	public static final int COMMAND_KEEP_CONNECTION_TEST_RESPONSE = 4;
	public static final int COMMAND_SEND = 5;
	public static final int COMMAND_SEND_RESPONSE = 6;
	public static final int COMMAND_TERMINAL_RESPONSE = 7;
	public static final int COMMAND_QUERY_LOCUS = 8;
	public static final int COMMAND_TOTAL_DATA_RESPONSE = 9;

	public static String CONTENT;
}
