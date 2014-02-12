package edu.buaa.automanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.buaa.automanager.service.SocketService;
import edu.buaa.automanager.service.SocketService.SocketBinder;

public class BaseSocketActivity extends BaseActivity {
	protected ServiceConnection connection = new ServiceConnection() {

		private SocketBinder socketBinder;
		private boolean mIsBound;

		public void doBindService() {
			bindService(new Intent(getApplicationContext(), SocketService.class), connection,
					Context.BIND_AUTO_CREATE);
			mIsBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			socketBinder = null;
			mIsBound = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			socketBinder = (SocketBinder) service;
			mIsBound = true;
		}
	};
	private Socket socketClient;
	private BufferedReader in;
	private PrintWriter out;

	public void sendMessage(String message) {
		try {
			socketClient = new Socket(Env.HOST, Env.PORT);
			in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socketClient.getOutputStream())), true);
			out.println(message);
			out.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
