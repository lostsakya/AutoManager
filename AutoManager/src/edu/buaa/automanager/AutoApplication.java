package edu.buaa.automanager;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class AutoApplication extends Application {

	private static AutoApplication mInstance;
	public boolean m_bKeyRight = true;
	private BMapManager mBMapManager;

	public static final String strKey = "rkEYtTqYu3HO6Zuo1n0vnyEC";

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initEngineManager(this);
	}

	public void initEngineManager(Context context) {
		if (getmBMapManager() == null) {
			setmBMapManager(new BMapManager(context));
		}

		if (!getmBMapManager().init(strKey, new MyGeneralListener())) {
			Toast.makeText(AutoApplication.getInstance().getApplicationContext(), "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
		}
	}

	public static AutoApplication getInstance() {
		return mInstance;
	}

	public BMapManager getmBMapManager() {
		return mBMapManager;
	}

	public void setmBMapManager(BMapManager mBMapManager) {
		this.mBMapManager = mBMapManager;
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(AutoApplication.getInstance().getApplicationContext(), "您的网络出错啦！", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(AutoApplication.getInstance().getApplicationContext(), "输入正确的检索条件！", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) {
			// 非零值表示key验证未通过
			if (iError != 0) {
				// 授权Key错误：
				Toast.makeText(AutoApplication.getInstance().getApplicationContext(), "请在 AutoApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: " + iError, Toast.LENGTH_LONG).show();
				AutoApplication.getInstance().m_bKeyRight = false;
			} else {
				AutoApplication.getInstance().m_bKeyRight = true;
				Toast.makeText(AutoApplication.getInstance().getApplicationContext(), "key认证成功", Toast.LENGTH_LONG).show();
			}
		}
	}
}
