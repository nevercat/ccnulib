package com.wangwenfeng.ccnulib.ui.findpassword;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class FindPasswordFragment extends FindPasswordFragmentBase {

	public FindPasswordFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.findpassword, container, false);
		findViewById();
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		mActvId = (AutoCompleteTextView) findViewById(R.id.findpassword_id);
		mBtnReset = (Button) findViewById(R.id.findpassword_reset);
	}

	@Override
	protected void setListener() {
		mLibActionBar.setOnBackClickListener(this);
		mBtnReset.setOnClickListener(this);
		mActvId.addTextChangedListener(this);
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
		// 点击重置后发送密码到邮箱
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("提示");
		builder.setMessage("密码信息邮件会很快发送到您的信箱，请注意查收！");
		AlertDialog ad = builder.create();
		ad.show();
	}

	@Override
	public void afterTextChanged(Editable arg0) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

	}

}
