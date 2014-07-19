package com.wangwenfeng.ccnulib.ui.login;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wangwenfeng.ccnulib.BaseFragment;
import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.ui.common.OnBackClickListener;
import com.wangwenfeng.ccnulib.ui.findpassword.FindPasswordFragment;

public abstract class LoginFragmentBase extends BaseFragment implements
		OnBackClickListener, OnClickListener, TextWatcher {
	protected LibActionBar mLibActionBar;

	/** 账号输入框 **/
	protected AutoCompleteTextView mActvAccount;

	/** 密码输入框 **/
	protected EditText mEtPassword;

	/** 登录按钮 **/
	protected Button mBtnLogin;

	/** 找回密码文本 **/
	protected TextView mTvFindPassword;

	public LoginFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

	/**
	 * 是否输入账号和密码
	 * 
	 * @param account
	 *            账号内容
	 * @param password
	 *            密码内容
	 */
	protected void isInput(String account, String password) {
		// 根据输入状态激活登录按钮
		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
			mBtnLogin.setEnabled(false);
		} else {
			mBtnLogin.setEnabled(true);
		}
	}

	/** 忘记密码添加超链接 **/
	protected void addIntentLink() {
		String s = getString(R.string.find_password_new);
		SpannableString sp = new SpannableString(s);
		// 设置超链接内容以及显示的字体颜色
		sp.setSpan(new IntentSpan(new OnClickListener() {

			public void onClick(View view) {
				// 跳转到找回密码界面
				getFragmentManager()
						.beginTransaction()
						.replace(
								R.id.login_container,
								new FindPasswordFragment(getActivity(),
										getActivity())).commit();
			}
		}), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(
				new ForegroundColorSpan(getResources().getColor(
						R.color.c_ff949494)), 0, s.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 设置文本内容
		mTvFindPassword.setText(sp);
		mTvFindPassword.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public class IntentSpan extends ClickableSpan {
		private final OnClickListener listener;

		public IntentSpan(View.OnClickListener listener) {
			this.listener = listener;
		}

		public void onClick(View view) {
			listener.onClick(view);
		}

		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			ds.setUnderlineText(true);
		}
	}
}
