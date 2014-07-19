package com.wangwenfeng.ccnulib.ui.top;

import java.util.List;
import java.util.Map;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.util.JsoupUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TopLendFragment extends TopLendFragmentBase {
	private ProgressDialog mypDialog;

	private ListView list;

	public TopLendFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.top_lend_fragment, container, false);

		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setTitle("请稍等");
		mypDialog.setMessage("正在查找...");
		mypDialog.setIndeterminate(false);

		findViewById();
		setListener();
		init();
		
		TopLend tl = new TopLend();
		tl.execute();
		
		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		list = (ListView) findViewById(R.id.top_lend_fragment);
	}

	@Override
	protected void setListener() {
		mLibActionBar.setOnBackClickListener(this);
	}

	@Override
	protected void init() {

	}

	@Override
	public void onBackClick() {
		showSlidingMenu();
	}

	class TopLend extends AsyncTask<String, Integer, List<Map<String, Object>>> {

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub

			return JsoupUtil.getTopLend();
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			System.out.println(result);
			mypDialog.cancel();
			if (result == null) {
				Toast.makeText(getActivity().getApplicationContext(), "出错了！",
						Toast.LENGTH_LONG).show();
				getActivity().finish();
			} else {
				SimpleAdapter listAdapter = new SimpleAdapter(getActivity()
						.getApplicationContext(), result, R.layout.top_lend,
						new String[] { "toplend_num", "toplend_title",
								"toplend_author", "toplend_publish",
								"toplend_callnum", "toplend_coll",
								"toplend_borrowamount", "toplend_proportion" },
						new int[] { R.id.topLendNumTV, R.id.topLendBookTitleTV,
								R.id.topLendAuthorTV, R.id.topLendPublishTV,
								R.id.topLendCallNumTV, R.id.topLendCollTV,
								R.id.topLendBorrowAmountTV,
								R.id.topLendProportionTV });
				list.setAdapter(listAdapter);
			}

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			mypDialog.show();
			super.onPreExecute();
		}

	}
}
