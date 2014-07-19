package com.wangwenfeng.ccnulib.ui.top;

import android.app.Activity;
import android.content.Context;

import com.wangwenfeng.ccnulib.BaseFragment;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.ui.common.OnBackClickListener;

public abstract class TopLendFragmentBase extends BaseFragment implements
		OnBackClickListener {
	protected LibActionBar mLibActionBar;

	public TopLendFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

}
