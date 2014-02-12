package edu.buaa.automanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyThread extends Thread {
	public Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			if (msg.what == 0x11) {

				Bundle bundle = msg.getData();

			}

		}

	};

	public String txt1;
	private Socket socket;
	private String buffer;

	public MyThread(String str) {

		txt1 = str;

	}

	@Override
	public void run() {

		// 定义消息

		Message msg = new Message();

		msg.what = 0x11;

		Bundle bundle = new Bundle();

		bundle.clear();

		try {

			// 连接服务器 并设置连接超时为5秒

			socket = new Socket();

			socket.connect(new InetSocketAddress("1.1.9.30", 30000), 5000);

			// 获取输入输出流

			OutputStream ou = socket.getOutputStream();

			BufferedReader bff = new BufferedReader(new InputStreamReader(

			socket.getInputStream()));

			// 读取发来服务器信息

			String line = null;

			buffer = "";

			while ((line = bff.readLine()) != null) {

				buffer = line + buffer;

			}

			// 向服务器发送信息

			ou.write("android 客户端".getBytes("gbk"));

			ou.flush();

			bundle.putString("msg", buffer.toString());

			msg.setData(bundle);

			// 发送消息 修改UI线程中的组件

			myHandler.sendMessage(msg);

			// 关闭各种输入输出流

			bff.close();

			ou.close();

			socket.close();

		} catch (SocketTimeoutException aa) {

			// 连接超时 在UI界面显示消息

			bundle.putString("msg", "服务器连接失败！请检查网络是否打开");

			msg.setData(bundle);

			// 发送消息 修改UI线程中的组件

			myHandler.sendMessage(msg);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
