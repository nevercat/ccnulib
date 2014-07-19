package com.wangwenfeng.ccnulib.ui.search;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.data.GlobleData;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;
import com.wangwenfeng.ccnulib.util.JsoupUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SearchSimpleFragment extends SearchSimpleFragmentBase {
	private ListView listView;

	private ProgressDialog mypDialog;

	private String html;

	private EditText searchText;

	private Button searchButton;

	private Button clearButton;

	private TextView sumNumber;

	private TextView pageNumber;

	private Button nextButton;

	private Button preButton;

	final String HTML1 = "?strSearchType=title&match_flag=forward&historyCount=1&strText=";
	final String HTML2 = "&doctype=ALL&displaypg=20&showmode=table&sort=CATA_DATE&orderby=desc&dept=ALL";

	public SearchSimpleFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.search_simple, container, false);
		findViewById();

		// 搜索进度提示
		mypDialog = new ProgressDialog(getActivity());
		mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mypDialog.setTitle("请稍等");
		mypDialog.setMessage("正在查找...");
		mypDialog.setIndeterminate(false);

		searchText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s.length() > 0) {
					clearButton.setVisibility(View.VISIBLE);
				} else {
					clearButton.setVisibility(View.GONE);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}
		});

		clearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchText.setText("");
			}
		});

		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// 字符编码，尤其对中文字符
					String str = new String(searchText.getText().toString()
							.trim().getBytes(), "ISO-8859-1");
					if (str.equals("")) {
						GlobleData.showToast(getActivity()
								.getApplicationContext(), "请输入检索内容!");
					} else {
						html = GlobleData.MAIN_URL + HTML1 + str + HTML2;
						System.out.println(html);
						// 默认的第一次搜索
						new LoadBookInfo().execute(html);
						// 当第二次检索时，应将之前的信息清空！
						JsoupUtil.clearInfo();
						// 总页数
						sumNumber = (TextView) findViewById(R.id.sum_number);
						pageNumber = (TextView) findViewById(R.id.page_number);
						// 上一页、下一页按钮
						nextButton = (Button) findViewById(R.id.next);
						preButton = (Button) findViewById(R.id.pre);
						preButton.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								if (JsoupUtil.page <= 1) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"已经是第一页了！", Toast.LENGTH_SHORT)
											.show();
								} else {
									new LoadBookInfo()
											.execute(JsoupUtil.preUrl);
									JsoupUtil.page--;
								}
							}
						});
						nextButton.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								if (JsoupUtil.page >= Math
										.ceil(JsoupUtil.sumNumber / 20)) {
									Toast.makeText(
											getActivity()
													.getApplicationContext(),
											"已经是最后一页了！", Toast.LENGTH_SHORT)
											.show();
								} else {
									new LoadBookInfo()
											.execute(JsoupUtil.nextUrl);
									JsoupUtil.page++;
								}
							}
						});
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		setListener();
		init();
		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		listView = (ListView) findViewById(R.id.search_section_list);
		searchButton = (Button) findViewById(R.id.search);
		searchText = (EditText) findViewById(R.id.search_box);
		clearButton = (Button) findViewById(R.id.search_clear_btn);
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

	public void slidingMenuOpen() {

	}

	class LoadBookInfo extends
			AsyncTask<String, ListView, List<Map<String, Object>>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			System.out.println("onPreExecute");
			mypDialog.show();
			super.onPreExecute();
		}

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("doInBackground");
			// System.out.println("html:" + html);
			// System.out.println(params[0]);
			return JsoupUtil.searchBook(params[0]);
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			System.out.println("onPostExecute");
			// 显示总数、页码及图书列表
			mypDialog.cancel();
			if (result == null) {
				// finish();
				Toast.makeText(getActivity().getApplicationContext(),
						"本馆没有您检索的纸本馆藏书目", Toast.LENGTH_LONG).show();
			} else {
				sumNumber.setText(JsoupUtil.sumNumber.toString());
				pageNumber.setText(JsoupUtil.pageNumber);
				SimpleAdapter listAdapter = new SimpleAdapter(getActivity()
						.getApplicationContext(), result,
						R.layout.search_result, new String[] { "bookNo",
								"bookTitle", "bookCallno", "bookAuthor",
								"bookPublisher", "bookType" }, new int[] {
								R.id.searchBookNoTV, R.id.searchBookTitleTV,
								R.id.searchCallNoTV, R.id.searchAuthorTV,
								R.id.searchPublisherTV, R.id.searchTypeTV

						});
				listView.setAdapter(listAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						System.out.println(arg0);
						System.out.println(arg1);
						System.out.println(arg2);
						System.out.println(arg3);
					}
				});
				super.onPostExecute(result);
			}
		}

	}
}
