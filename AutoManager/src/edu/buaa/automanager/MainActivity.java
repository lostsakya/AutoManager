package edu.buaa.automanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import edu.buaa.automanager.service.SocketService;
import edu.buaa.automanager.utils.LogUtil;
import edu.buaa.automanager.utils.MsgSender;
import edu.buaa.automanager.utils.ToastUtil;

public class MainActivity extends BaseSocketActivity implements OnClickListener {
	private String TAG = getClass().getSimpleName();
	private Socket socketClient;
	private BufferedReader in;
	private PrintWriter out;
	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(this);

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
				bindService(new Intent(getApplicationContext(), SocketService.class), connection,
						Context.BIND_AUTO_CREATE);
				sendMessage(message);
			}
			break;
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
}
