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

public class TopScoreFragment extends TopScoreFragmentBase {
	private ProgressDialog mypDialog;

	private ListView list;

	public TopScoreFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.top_score_fragment, container, false);

		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setTitle("���Ե�");
		mypDialog.setMessage("���ڲ���...");
		mypDialog.setIndeterminate(false);

		findViewById();
		setListener();
		init();

		TopScore ts = new TopScore();
		ts.execute();

		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		list = (ListView) findViewById(R.id.top_score_fragment);
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

	class TopScore extends
			AsyncTask<String, Integer, List<Map<String, Object>>> {

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub

			return JsoupUtil.getTopScore();
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			System.out.println(result);
			mypDialog.cancel();
			if (result == null) {
				Toast.makeText(getActivity().getApplicationContext(), "�����ˣ�",
						Toast.LENGTH_LONG).show();
				getActivity().finish();
			} else {
				SimpleAdapter listAdapter = new SimpleAdapter(getActivity()
						.getApplicationContext(), result, R.layout.top_score,
						new String[] { "topscore_num", "topscore_title",
								"topscore_author", "topscore_publish",
								"topscore_callnum", "topscore_amount" },
						new int[] { R.id.topScoreNumTV,
								R.id.topScoreBookTitleTV,
								R.id.topScoreAuthorTV, R.id.topScorePublishTV,
								R.id.topScoreCallNumTV, R.id.topScoreAmountTV });
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
