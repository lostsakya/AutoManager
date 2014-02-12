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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.buaa.automanager.Constants;
import edu.buaa.automanager.Env;

public class SocketService extends Service {

	protected static final int SOCKET_START = 0;
	public static final int RECEIVE_MSG = 1;

	// ArrayList<SocketListener> listeners = new ArrayList<SocketListener>();

	public class SocketBinder extends Binder {
		private BufferedReader in;
		private PrintWriter out;
		private Socket socketClient;
		private SocketListener listener;

		public SocketService getInstance() {
			return SocketService.this;
		}

		public void sendMsg(int action, String msg) {
			try {
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println(msg);
		}

		public String receiveMsg(int action) {
			StringBuffer stringBuffer = new StringBuffer();
			String receivedMsg = "";
			Message message = Message.obtain(socketHandler);
			Bundle bundle = new Bundle();
			bundle.putInt(Constants.ACTION, action);

			try {
				in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				String buffer = "";
				while ((buffer = in.readLine()) != null) {
					stringBuffer.append(buffer);
				}
				receivedMsg = stringBuffer.toString();
				listener.onSocketSuccess(action, receivedMsg);
				return receivedMsg;
			} catch (IOException e) {
				listener.onSocketError(action, e);
				e.printStackTrace();
			} finally {
				message.what = RECEIVE_MSG;
				bundle.putString(Constants.RECEIVE_MSG, receivedMsg);
				message.setData(bundle);
				message.sendToTarget();
			}
			return receivedMsg;

		}
	}

	public Handler socketHandler;

	class LooperThread extends Thread {

		@Override
		public void run() {
			Looper.prepare();
			socketHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case SocketService.SOCKET_START:
						break;
					default:
						break;
					}
				}

			};
			Looper.loop();
		}
	}

	// public void addListener(SocketListener listener) {
	// listeners.add(listener);
	// }
	//
	// public void removeListener(SocketListener listener) {
	// listeners.remove(listener);
	// }
	//
	// public void clearListeners() {
	// listeners.clear();
	// }

	private Socket socketClient;
	private final IBinder mBinder = new SocketBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		new LooperThread().start();
		connectToServer();
	}

	private void connectToServer() {
		try {
			socketClient = new Socket();
			socketClient.connect(new InetSocketAddress(Env.HOST, Env.PORT), Env.TIMEOUT);
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
