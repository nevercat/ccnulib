package com.wangwenfeng.ccnulib.data;

import java.util.List;

import org.apache.http.cookie.Cookie;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class GlobleData {
	public static final String LOGIN_URL = "http://202.114.34.15/reader/redr_verify.php";
	public static final String BOOK_LST = "http://202.114.34.15/reader/book_lst.php";
	public static final String MAIN_URL = "http://202.114.34.15/opac/openlink.php";
	public static final String BOOK_HIST = "http://202.114.34.15/reader/book_hist.php";
	public static final String TOP_LEND = "http://202.114.34.15/top/top_lend.php";
	public static final String TOP_SCORE = "http://202.114.34.15/top/top_score.php?sort=score&cls_no=ALL";
	public static final String TOP_SHELF = "http://202.114.34.15/top/top_shelf.php";
	public static final String TOP_BOOK = "http://202.114.34.15/top/top_book.php";

	// �жϵ�¼״̬
	public static Boolean flag = false;

	public static List<Cookie> cookies;

	public static void showToast(Context c, String s) {
		Toast.makeText(c, s, Toast.LENGTH_LONG).show();

	}

	public static boolean hasInternet(Activity a) {
		ConnectivityManager manager = (ConnectivityManager) a
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null || !info.isConnected()) {
			return false;
		}
		if (info.isRoaming()) {
			return true;
		}
		return true;
	}
}
