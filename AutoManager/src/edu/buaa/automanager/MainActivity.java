package edu.buaa.automanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.automanager.R;

import edu.buaa.automanager.utils.MsgSender;
import edu.buaa.automanager.utils.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

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
				sendMessage(message);
				out.close();
			}
			break;
		default:
			break;
		}

	}

	private void sendMessage(String message) {
		try {
			socketClient = new Socket(Env.HOST, Env.PORT);
			in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
			out.println(message);
			out.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSocket() {
		try {
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean verify(String username, String password) {
		if (TextUtils.isEmpty(username)) {
			ToastUtil.shortUtil(getApplicationContext(), "用户名不能为空");
		}
		if (TextUtils.isEmpty(password)) {
			ToastUtil.shortUtil(getApplicationContext(), "密码不能为空");
		}
		return false;
	}
}
