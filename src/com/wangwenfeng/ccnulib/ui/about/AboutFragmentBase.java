package com.wangwenfeng.ccnulib.ui.about;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wangwenfeng.ccnulib.BaseFragment;
import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.ui.common.OnBackClickListener;

public abstract class AboutFragmentBase extends BaseFragment implements
		OnClickListener, OnBackClickListener {
	/** �Զ�������� **/
	protected LibActionBar mLibActionBar;
	/** �°汾���԰�ť **/
	protected Button mBtnNewFeatures;
	/** ����������վ��ť **/
	protected Button mBtnVisitAppWebsite;
	/** ��������΢����ť **/
	protected Button mBtnVisitAppWeiBo;
	/** ����QQ��ť **/
	protected Button mBtnCustomerServiceHotline;
	/** �������䰴ť **/
	protected Button mBtnCustomerServiceMail;

	/** ��������վ **/
	protected void openWebsite() {
		openUrl("http://www.wangwenfeng.com");
	}

	/** �򿪹���΢���Ի��� **/
	protected void openWeiBo() {
		new AlertDialog.Builder(getActivity())
				.setTitle(getString(R.string.visit_app_weibo))
				.setItems(R.array.weibo_name,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									// ����΢����ַ
									openUrl("http://weibo.com/wangwenfeng0919");
									break;

								}
							}
						}).create().show();
	}

	/** ����URL�򿪽��� **/
	protected void openUrl(String url) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		startActivity(intent);

	}

	/** �����ʼ����������� **/
	protected void sendTo() {
		Intent data = new Intent(Intent.ACTION_SENDTO);
		data.setData(Uri.parse("mailto:wwf@mails.ccnu.edu.cn"));
		startActivity(data);
	}

	public AboutFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

}
