package com.wangwenfeng.ccnulib.ui.login;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.ui.mylib.BookHistFragment;
import com.wangwenfeng.ccnulib.ui.mylib.BookListFragment;
import com.wangwenfeng.ccnulib.ui.mylib.ReaderInfoFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.wangwenfeng.ccnulib.data.GlobleData;
import com.wangwenfeng.ccnulib.util.JsoupUtil;

public class LoginFragment extends LoginFragmentBase {
	private ProgressDialog mypDialog;

	private CheckBox recd;

	public LoginFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.login, container, false);

		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setTitle("µÇÂ¼");
		mypDialog.setMessage("µÇÂ¼ÖÐ¡­¡­");
		mypDialog.setIndeterminate(false);
		findViewById();
		setListener();
		init();

		// ¼Ç×¡ÃÜÂë
		Context ct = getActivity();
		SharedPreferences pre = ct.getSharedPreferences("lib",
				Context.MODE_APPEND);
		String user = pre.getString("number", "");
		String pass = pre.getString("passwd", "");
		if (user != "") {
			mActvAccount.setText(user);
			mEtPassword.setText(pass);
			recd.setChecked(true);
		}

		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		mActvAccount = (AutoCompleteTextView) findViewById(R.id.account);
		mEtPassword = (EditText) findViewById(R.id.password);
		mBtnLogin = (Button) findViewById(R.id.login);
		mTvFindPassword = (TextView) findViewById(R.id.findpassword);
		recd = (CheckBox) findViewById(R.id.recd);
	}

	@Override
	protected void setListener() {
		mLibActionBar.setOnBackClickListener(this);
		mBtnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = mActvAccount.getText().toString().trim();
				String passwd = mEtPassword.getText().toString().trim();
				if (recd.isChecked()) {
					Context ct = getActivity();
					SharedPreferences pre = ct.getSharedPreferences("lib",
							Context.MODE_APPEND);
					Editor edit = pre.edit();
					edit.putString("number", number);
					edit.putString("passwd", passwd);
					// edit.putString("cookie", l.getCookie());
					edit.commit();

				} else {
					Context ct = getActivity();
					SharedPreferences sp = ct.getSharedPreferences("lib",
							Context.MODE_APPEND);
					sp.edit().clear();
				}
				Login login = new Login();
				login.execute(number, passwd);
			}
		});
		mActvAccount.addTextChangedListener(this);
		mEtPassword.addTextChangedListener(this);
	}

	class Login extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			mypDialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			return JsoupUtil.loginUrl(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			mypDialog.cancel();
			if (result) {
				// ÒÑ¾­µÇÂ½
				GlobleData.flag = true;
				String msg = getArguments().getString("msg");
				if (msg.equals("readerInfo")) {
					getFragmentManager()
							.beginTransaction()
							.replace(
									R.id.login_container,
									new ReaderInfoFragment(getActivity(),
											getActivity())).commit();
				} else if (msg.equals("bookList")) {
					getFragmentManager()
							.beginTransaction()
							.replace(
									R.id.login_container,
									new BookListFragment(getActivity(),
											getActivity())).commit();
				} else if (msg.equals("bookHist")) {
					getFragmentManager()
							.beginTransaction()
							.replace(
									R.id.login_container,
									new BookHistFragment(getActivity(),
											getActivity())).commit();
				}
			} else {
				GlobleData.showToast(getActivity().getApplicationContext(),
						"µÇÂ¼Ê§°Ü£¬Çë¼ì²éÕËºÅºÍÃÜÂë£¡");
				// AlertDialog.Builder builder = new AlertDialog.Builder(
				// LoginActivity.this);
				// builder.setMessage("µÇÂ¼Ê§°Ü£¬Çë¼ì²éÕËºÅºÍÃÜÂë£¡");
				// AlertDialog ad = builder.create();
				// ad.show();
			}
			super.onPostExecute(result);
		}
	}

	@Override
	protected void init() {
		addIntentLink();
	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

	@Override
	public void onClick(View arg0) {
		// Æô¶¯ÏàÓ¦Ò³Ãæ
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
		String account = mActvAccount.getText().toString().trim();
		String password = mEtPassword.getText().toString().trim();
		isInput(account, password);
	}

}
