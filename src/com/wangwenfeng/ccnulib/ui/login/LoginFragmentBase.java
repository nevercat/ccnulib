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

	/** �˺������ **/
	protected AutoCompleteTextView mActvAccount;

	/** ��������� **/
	protected EditText mEtPassword;

	/** ��¼��ť **/
	protected Button mBtnLogin;

	/** �һ������ı� **/
	protected TextView mTvFindPassword;

	public LoginFragmentBase(Activity activity, Context context) {
		super(activity, context);
	}

	/**
	 * �Ƿ������˺ź�����
	 * 
	 * @param account
	 *            �˺�����
	 * @param password
	 *            ��������
	 */
	protected void isInput(String account, String password) {
		// ��������״̬�����¼��ť
		if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
			mBtnLogin.setEnabled(false);
		} else {
			mBtnLogin.setEnabled(true);
		}
	}

	/** ����������ӳ����� **/
	protected void addIntentLink() {
		String s = getString(R.string.find_password_new);
		SpannableString sp = new SpannableString(s);
		// ���ó����������Լ���ʾ��������ɫ
		sp.setSpan(new IntentSpan(new OnClickListener() {

			public void onClick(View view) {
				// ��ת���һ��������
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
		// �����ı�����
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
