package com.wangwenfeng.ccnulib.ui.mylib;

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

public class BookListFragment extends BookListFragmentBase {
	private ProgressDialog mypDialog;

	private ListView list;

	public BookListFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.book_list_fragment, container, false);

		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setTitle("请稍等");
		mypDialog.setMessage("正在查找...");
		mypDialog.setIndeterminate(false);

		findViewById();
		setListener();
		init();
		BorrowedBook bb = new BorrowedBook();
		bb.execute();
		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		list = (ListView) findViewById(R.id.book_list_fragment);
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

	class BorrowedBook extends
			AsyncTask<String, Integer, List<Map<String, Object>>> {

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub

			return JsoupUtil.getBorrowedBook();
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			System.out.println(result);
			mypDialog.cancel();
			if (result == null) {
				Toast.makeText(getActivity().getApplicationContext(),
						"您当前没有借阅，或者出错了！", Toast.LENGTH_LONG).show();
				getActivity().finish();
			} else {
				SimpleAdapter listAdapter = new SimpleAdapter(getActivity()
						.getApplicationContext(), result, R.layout.book_list,
						new String[] { "barcode", "booktitle", "borrowedDate",
								"paybackDate", "renewAmount",
								"collectionAddress", "attachment" }, new int[] {
								R.id.barcodeTV, R.id.bookTitleTV,
								R.id.borrowedDateTV, R.id.paybackDateTV,
								R.id.renewAmountTV, R.id.collectionAddressTV,
								R.id.attachmentTV });
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
