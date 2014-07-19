package com.wangwenfeng.ccnulib.ui.findpassword;

import android.app.Activity;
import android.content.Context;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.wangwenfeng.ccnulib.BaseFragment;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.ui.common.OnBackClickListener;

public abstract class FindPasswordFragmentBase extends BaseFragment implements
		OnClickListener, TextWatcher, OnBackClickListener {
	protected LibActionBar mLibActionBar;

	/** ֤��������� **/
	protected AutoCompleteTextView mActvId;

	/** ���ð�ť **/
	protected Button mBtnReset;

	public FindPasswordFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

}
