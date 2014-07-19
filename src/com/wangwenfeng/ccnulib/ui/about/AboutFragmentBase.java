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
	/** 自定义标题栏 **/
	protected LibActionBar mLibActionBar;
	/** 新版本特性按钮 **/
	protected Button mBtnNewFeatures;
	/** 访问作者网站按钮 **/
	protected Button mBtnVisitAppWebsite;
	/** 访问作者微博按钮 **/
	protected Button mBtnVisitAppWeiBo;
	/** 作者QQ按钮 **/
	protected Button mBtnCustomerServiceHotline;
	/** 作者邮箱按钮 **/
	protected Button mBtnCustomerServiceMail;

	/** 打开作者网站 **/
	protected void openWebsite() {
		openUrl("http://www.wangwenfeng.com");
	}

	/** 打开官网微博对话框 **/
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
									// 新浪微博地址
									openUrl("http://weibo.com/wangwenfeng0919");
									break;

								}
							}
						}).create().show();
	}

	/** 根据URL打开界面 **/
	protected void openUrl(String url) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		startActivity(intent);

	}

	/** 发送邮件到作者邮箱 **/
	protected void sendTo() {
		Intent data = new Intent(Intent.ACTION_SENDTO);
		data.setData(Uri.parse("mailto:wwf@mails.ccnu.edu.cn"));
		startActivity(data);
	}

	public AboutFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

}
