package edu.buaa.automanager.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import edu.buaa.automanager.Env;

public class SocketService extends Service {

	protected static final int SOCKET_START = 0;
	ArrayList<SocketListener> listeners = new ArrayList<SocketListener>();

	public class SocketBinder extends Binder {
		private BufferedReader in;
		private PrintWriter out;
		private Socket socketClient;

		public SocketService getInstance() {
			return SocketService.this;
		}

		public void sendMsg(String msg) {
			for (SocketListener listener : listeners) {
				listener.onSocketStart("", msg);
			}
			try {
				in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
						socketClient.getOutputStream())), true);
				out.println(msg);
			} catch (IOException e) {
				for (SocketListener listener : listeners) {
					listener.onSocketError("", msg, e);
				}
				e.printStackTrace();
			}

		}
	}

	class LooperThread extends Thread {
		private Handler socketHandler;

		@Override
		public void run() {
			Looper.prepare();
			socketHandler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case SocketService.SOCKET_START:
						Bundle bundle = msg.getData();

						break;
					}
				}

			};
			Looper.loop();
		}
	}

	public void addListener(SocketListener listener) {
		listeners.add(listener);
	}

	public void removeListener(SocketListener listener) {
		listeners.remove(listener);
	}

	public void clearListeners() {
		listeners.clear();
	}

	private Socket socketClient;
	private final IBinder mBinder = new SocketBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		connectToServer();
	}

	private void connectToServer() {
		try {
			socketClient = new Socket(Env.HOST, Env.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		disconnectServer();
	}

	private void disconnectServer() {
		try {
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
