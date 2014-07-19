package com.wangwenfeng.ccnulib.ui.search;

import android.app.Activity;
import android.content.Context;

import com.wangwenfeng.ccnulib.BaseFragment;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.ui.common.OnBackClickListener;

public abstract class SearchAdvFragmentBase extends BaseFragment implements
		OnBackClickListener {
	protected LibActionBar mLibActionBar;

	public SearchAdvFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

}
