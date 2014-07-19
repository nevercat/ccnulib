package com.wangwenfeng.ccnulib.ui.about;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AboutFragment extends AboutFragmentBase {

	public AboutFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.about, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		mBtnNewFeatures = (Button) findViewById(R.id.about_new_features);
		mBtnVisitAppWebsite = (Button) findViewById(R.id.about_visit_app_website);
		mBtnVisitAppWeiBo = (Button) findViewById(R.id.about_visit_app_weibo);
		mBtnCustomerServiceHotline = (Button) findViewById(R.id.about_customer_service_hotline);
		mBtnCustomerServiceMail = (Button) findViewById(R.id.about_customer_service_mail);
	}

	@Override
	protected void setListener() {
		mLibActionBar.setOnBackClickListener(this);
		mBtnNewFeatures.setOnClickListener(this);
		mBtnVisitAppWebsite.setOnClickListener(this);
		mBtnVisitAppWeiBo.setOnClickListener(this);
		mBtnCustomerServiceHotline.setOnClickListener(this);
		mBtnCustomerServiceMail.setOnClickListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about_new_features:

			break;

		case R.id.about_visit_app_website:
			openWebsite();
			break;

		case R.id.about_visit_app_weibo:
			openWeiBo();
			break;

		case R.id.about_customer_service_hotline:
			break;

		case R.id.about_customer_service_mail:
			break;
		}
	}
}
