package edu.buaa.automanager.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.buaa.automanager.Env;
import edu.buaa.automanager.R;
import edu.buaa.automanager.utils.LogUtil;
import edu.buaa.automanager.utils.MsgSender;
import edu.buaa.automanager.utils.ToastUtil;

public class MainActivity extends BaseSocketActivity implements OnClickListener {
	public class SocketThread extends Thread {
		private String message;

		SocketThread(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			super.run();
			createSocket();
			sendMessage(message);
			String readMessage;
			while ((readMessage = readMessage()) != "") {
				LogUtil.log(TAG, readMessage);
			}
		}
	}

	private String TAG = getClass().getSimpleName();
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;
	private Button btnLogout;

	public void sendMessage(String message) {
		try {
			createSocket();
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSocket() {
		if (socket == null) {
			try {
				socket = new Socket(Env.HOST, Env.PORT);
				LogUtil.log(TAG, "建立socket连接");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void closeSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readMessage() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogout = (Button) findViewById(R.id.btn_logout);
		btnLogin.setOnClickListener(this);
		btnLogout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String username = etUsername.getText().toString();
			String password = etPassword.getText().toString();
			if (verify(username, password)) {
				String message = MsgSender.getLoginMsg(username, password);
				LogUtil.log(TAG, message);
				// bindService(new Intent(getApplicationContext(),
				// SocketService.class), connection, Context.BIND_AUTO_CREATE);
				new SocketThread(message).start();
			}
			break;
		case R.id.btn_logout:
			// String message = MsgSender.getLogoutMsg();
			// LogUtil.log(TAG, message);
			// new SocketThread(message).start();
			gotoNextActivity();
		default:
			break;
		}
	}

	private boolean verify(String username, String password) {
		if (TextUtils.isEmpty(username)) {
			ToastUtil.shortUtil(getApplicationContext(), "用户名不能为空");
			return false;
		}
		if (TextUtils.isEmpty(password)) {
			ToastUtil.shortUtil(getApplicationContext(), "密码不能为空");
			return false;
		}
		return true;
	}

	private void gotoNextActivity() {
		startActivity(new Intent(getApplicationContext(), TabsActivity.class));
	}
}
