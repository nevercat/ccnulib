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

public class TopShelfFragment extends TopShelfFragmentBase {
	private ProgressDialog mypDialog;

	private ListView list;

	public TopShelfFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.top_shelf_fragment, container, false);

		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setTitle("请稍等");
		mypDialog.setMessage("正在查找...");
		mypDialog.setIndeterminate(false);

		findViewById();
		setListener();
		init();

		TopShelf ts = new TopShelf();
		ts.execute();

		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		list = (ListView) findViewById(R.id.top_shelf_fragment);
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

	class TopShelf extends
			AsyncTask<String, Integer, List<Map<String, Object>>> {

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub

			return JsoupUtil.getTopShelf();
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
						.getApplicationContext(), result, R.layout.top_shelf,
						new String[] { "topshelf_num", "topshelf_title",
								"topshelf_author", "topshelf_publish",
								"topshelf_callnum", "topshelf_amount" },
						new int[] { R.id.topShelfNumTV,
								R.id.topShelfBookTitleTV,
								R.id.topShelfAuthorTV, R.id.topShelfPublishTV,
								R.id.topShelfCallNumTV, R.id.topShelfAmountTV });
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
