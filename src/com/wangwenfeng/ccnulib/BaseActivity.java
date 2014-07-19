package com.wangwenfeng.ccnulib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * @Title BaseActivity.java
 * @Package com.wangwenfeng.ccnulib
 * @Description Activity����
 * @author Vin Wong http://www.wangwenfeng.com
 * @date 2013-8-11 ����10:19:44
 * @version v1.0
 */
public abstract class BaseActivity extends Activity {

	/** �󶨽���UI **/
	protected abstract void findViewById();

	/** ����UI�¼����� **/
	protected abstract void setListener();

	/** �������ݳ�ʼ�� **/
	protected abstract void init();

	/** ������ʾToast��ʾ(����res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/** ������ʾToast��ʾ(����String) **/
	protected void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/** ��ʱ����ʾToast��ʾ(����res) **/
	protected void showLongToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
	}

	/** ��ʱ����ʾToast��ʾ(����String) **/
	protected void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	/** Debug���Log��־ **/
	protected void showLogDebug(String tag, String msg) {
		Log.d(tag, msg);
	}

	/** Error���Log��־ **/
	protected void showLogError(String tag, String msg) {
		Log.e(tag, msg);
	}

	/** ͨ��Class��ת���� **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** ����Bundleͨ��Class��ת���� **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** ͨ��Action��ת���� **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** ����Bundleͨ��Action��ת���� **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** ���б�������ݵĶԻ��� **/
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	/** ���б��⡢���ݡ�������ť�ĶԻ��� **/
	protected AlertDialog showAlertDialog(String title, String message,
			String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** ���б��⡢���ݡ�ͼ�ꡢ������ť�ĶԻ��� **/
	protected AlertDialog showAlertDialog(String title, String message,
			int icon, String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** �����ҽ��ҳ��������˳� **/
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	/** Ĭ���˳� **/
	protected void defaultFinish() {
		super.finish();
	}
}
