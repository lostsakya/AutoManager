package edu.buaa.automanager.view.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import edu.buaa.automanager.BaseActivity;
import edu.buaa.automanager.Env;

public class BaseSocketActivity extends BaseActivity {
	// protected ServiceConnection connection = new ServiceConnection() {
	//
	// private SocketBinder socketBinder;
	// private boolean mIsBound;
	//
	// public void doBindService() {
	// bindService(new Intent(getApplicationContext(), SocketService.class),
	// connection,
	// Context.BIND_AUTO_CREATE);
	// mIsBound = true;
	// }
	//
	// @Override
	// public void onServiceDisconnected(ComponentName name) {
	// socketBinder = null;
	// mIsBound = false;
	// }
	//
	// @Override
	// public void onServiceConnected(ComponentName name, IBinder service) {
	// socketBinder = (SocketBinder) service;
	// mIsBound = true;
	// }
	// };
	private Socket socketClient;
	private BufferedReader in;
	private PrintWriter out;

	public void sendMessage(String message) {
		try {
			socketClient = new Socket(Env.HOST, Env.PORT);
			out = new PrintWriter(socketClient.getOutputStream(), true);
			out.println(message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readMessage() {
		try {
			in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = in.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}

}
