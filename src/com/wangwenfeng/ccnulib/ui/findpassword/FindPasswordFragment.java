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
		// ������ú������뵽����
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("��ʾ");
		builder.setMessage("������Ϣ�ʼ���ܿ췢�͵��������䣬��ע����գ�");
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
