package edu.buaa.automanager.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static void shortUtil(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
