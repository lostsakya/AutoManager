package edu.buaa.automanager.utils;

import edu.buaa.automanager.exception.MsgParseException;

public class MsgParser {

	public void parseFullMsg(String fullMsg) throws MsgParseException {
		verifyMinLength(fullMsg);
		parseStart(fullMsg);
		parseEnd(fullMsg);
		int commandType = getCommandTpye(fullMsg);
		parseContent(commandType, fullMsg);

	}

	private int getCommandTpye(String fullMsg) {
		return Integer.parseInt(fullMsg.substring(3, 3 + 1));
	}

	private void verifyMinLength(String fullMsg) throws MsgParseException {
		if (fullMsg.length() < 7) {
			throw new MsgParseException("wrong message length!");
		}
	}

	private static String getContent(String fullMsg) throws MsgParseException {
		return fullMsg.substring(4, fullMsg.length() - 3);
	}

	private void parseStart(String fullMsg) throws MsgParseException {
		if (fullMsg.substring(0, 3) != Protocal.TAG_PARSE_START) {
			throw new MsgParseException("wrong start tag");
		}
	}

	private void parseEnd(String fullMsg) throws MsgParseException {
		if (fullMsg.substring(fullMsg.length() - 3) != Protocal.TAG_PARSE_END) {
			throw new MsgParseException("wrong end tag");
		}
	}

	public String parseContent(int commandType, String content) {

		switch (commandType) {
		case Protocal.COMMAND_LOGIN_RESPONSE:

			break;
		case Protocal.COMMAND_KEEP_CONNECTION_TEST_RESPONSE:

			break;
		case Protocal.COMMAND_SEND_RESPONSE:

			break;
		case Protocal.COMMAND_TERMINAL_RESPONSE:

			break;
		case Protocal.COMMAND_TOTAL_DATA_RESPONSE:

			break;
		// case Protocal.COMMAND_LOGIN:
		//
		// break;
		// case Protocal.COMMAND_LOGIN_RESPONSE:
		//
		// break;
		// case Protocal.COMMAND_KEEP_CONNECTION_TEST:
		//
		// break;
		// case Protocal.COMMAND_KEEP_CONNECTION_TEST_RESPONSE:
		//
		// break;
		// case Protocal.COMMAND_SEND:
		//
		// break;
		// case Protocal.COMMAND_SEND_RESPONSE:
		//
		// break;
		// case Protocal.COMMAND_TERMINAL_RESPONSE:
		//
		// break;
		// case Protocal.COMMAND_QUERY_LOCUS:
		//
		// break;
		// case Protocal.COMMAND_TOTAL_DATA_RESPONSE:
		//
		// break;

		default:
			break;
		}

		return content;
	}
}
