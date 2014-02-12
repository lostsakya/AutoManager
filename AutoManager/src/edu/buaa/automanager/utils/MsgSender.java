package edu.buaa.automanager.utils;

public class MsgSender {

	private static String fullMsg(int command, String msg) {
		return Protocal.TAG_SEND_START + command + msg + Protocal.TAG_SEND_END;
	}

	public static String getLoginMsg(String username, String password) {
		return fullMsg(Protocal.COMMAND_LOGIN, lengthAndString(username)
				+ lengthAndString(password));
	}

	private static String twoDigitLength(String string) {
		int length = string.length();
		String lengthString = "";
		if (length < 10 && length > 0) {
			lengthString = "0" + length;
		} else if (length < 100 && length >= 10) {
			lengthString = length + "";
		}
		return lengthString;
	}

	private static String lengthAndString(String string) {
		return twoDigitLength(string) + string;
	}
}
