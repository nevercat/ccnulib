package com.wangwenfeng.ccnulib.ui;

import java.util.ArrayList;
import java.util.List;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.data.GlobleData;
import com.wangwenfeng.ccnulib.slidingmenu.BaseSlidingFragmentActivity;
import com.wangwenfeng.ccnulib.slidingmenu.SlidingMenu;
import com.wangwenfeng.ccnulib.slidingmenu.SlidingMenu.OnOpenListener;
import com.wangwenfeng.ccnulib.ui.about.AboutFragment;
import com.wangwenfeng.ccnulib.ui.login.LoginFragment;
import com.wangwenfeng.ccnulib.ui.mylib.BookHistFragment;
import com.wangwenfeng.ccnulib.ui.mylib.BookListFragment;
import com.wangwenfeng.ccnulib.ui.mylib.ReaderInfoFragment;
import com.wangwenfeng.ccnulib.ui.search.SearchAdvFragment;
import com.wangwenfeng.ccnulib.ui.search.SearchMoreFragment;
import com.wangwenfeng.ccnulib.ui.search.SearchSimpleFragment;
import com.wangwenfeng.ccnulib.ui.top.TopBookFragment;
import com.wangwenfeng.ccnulib.ui.top.TopLendFragment;
import com.wangwenfeng.ccnulib.ui.top.TopScoreFragment;
import com.wangwenfeng.ccnulib.ui.top.TopShelfFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Title MainActivity.java
 * @Package com.wangwenfeng.ccnulib.ui
 * @Description 菜单主界面
 * @author Vin Wong http://www.wangwenfeng.com
 * @date 2013-8-11 下午9:24:32
 * @version v1.0
 */
public class MainActivity extends BaseSlidingFragmentActivity implements
		OnOpenListener {
	/** 菜单 **/
	private SlidingMenu mSlidingMenu;

	/** 菜单头界面 **/
	private View mVMenuHead;

	/** 菜单列表 **/
	private ExpandableListView mElvMenu;

	/** 简单检索 **/
	private SearchSimpleFragment mSearchSimpleFragment;

	/** 全文检索 **/
	/*
	 * private SearchAdvFragment mSearchAdvFragment;
	 *//** 多字段检索 **/
	/*
	 * private SearchMoreFragment mSearchMoreFragment;
	 */

	/** 证件信息 **/
	private ReaderInfoFragment mReaderInfoFragment;

	/** 当前借阅 **/
	private BookListFragment mBookListFragment;

	/** 借阅历史 **/
	private BookHistFragment mBookHistFragment;

	/** 热门借阅 **/
	private TopLendFragment mTopLendFragment;

	/** 热门评分 **/
	private TopScoreFragment mTopScoreFragment;

	/** 热门收藏 **/
	private TopShelfFragment mTopShelfFragment;

	/** 热门图书 **/
	private TopBookFragment mTopBookFragment;

	/** 关于 **/
	private AboutFragment mAboutFragment;

	/** 登陆 **/
	private LoginFragment mLoginFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!GlobleData.hasInternet(MainActivity.this)) {
			GlobleData.showToast(getApplicationContext(), "网络连接失败，请检查网络设置！");
		}
		setBehindContentView(R.layout.slidingmenu_menu);
		initSlidingMenu();
		setContentView(R.layout.slidingmenu_content);
		mVMenuHead = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.slidingmenu_menu_head, null);
		findViewById();
		setListener();
		init();
	}

	private void findViewById() {
		mElvMenu = (ExpandableListView) findViewById(R.id.menu);
	}

	private void setListener() {
		mSlidingMenu.setOnOpenListener(this);
		mElvMenu.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int arg2, long arg3) {
				return true;
			}
		});
		mElvMenu.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				switch (groupPosition) {
				case 0:
					switch (childPosition) {
					case 0:
						mSearchSimpleFragment = new SearchSimpleFragment(
								MainActivity.this, MainActivity.this);
						changeContent(mSearchSimpleFragment);
						break;

					/*
					 * case 1: mSearchAdvFragment = new SearchAdvFragment(
					 * MainActivity.this, MainActivity.this);
					 * changeContent(mSearchAdvFragment); break;
					 * 
					 * case 2: mSearchMoreFragment = new SearchMoreFragment(
					 * MainActivity.this, MainActivity.this);
					 * changeContent(mSearchMoreFragment); break;
					 */

					}
					break;

				case 1:
					switch (childPosition) {
					case 0:
						if (GlobleData.flag) {
							mReaderInfoFragment = new ReaderInfoFragment(
									MainActivity.this, MainActivity.this);
							changeContent(mReaderInfoFragment);
							break;
						} else {
							Bundle arguments = new Bundle();
							arguments.putString("msg", "readerInfo");
							mLoginFragment = new LoginFragment(
									MainActivity.this, MainActivity.this);
							mLoginFragment.setArguments(arguments);
							changeContent(mLoginFragment);
							break;
						}

					case 1:
						if (GlobleData.flag) {
							mBookListFragment = new BookListFragment(
									MainActivity.this, MainActivity.this);
							changeContent(mBookListFragment);
							break;
						} else {
							Bundle arguments = new Bundle();
							arguments.putString("msg", "bookList");
							mLoginFragment = new LoginFragment(
									MainActivity.this, MainActivity.this);
							mLoginFragment.setArguments(arguments);
							changeContent(mLoginFragment);
							break;
						}

					case 2:
						if (GlobleData.flag) {
							mBookHistFragment = new BookHistFragment(
									MainActivity.this, MainActivity.this);
							changeContent(mBookHistFragment);
							break;
						} else {
							Bundle arguments = new Bundle();
							arguments.putString("msg", "bookHist");
							mLoginFragment = new LoginFragment(
									MainActivity.this, MainActivity.this);
							mLoginFragment.setArguments(arguments);
							changeContent(mLoginFragment);
							break;
						}
					}
					break;

				case 2:
					switch (childPosition) {
					case 0:
						mTopLendFragment = new TopLendFragment(
								MainActivity.this, MainActivity.this);
						changeContent(mTopLendFragment);
						break;

					case 1:
						mTopScoreFragment = new TopScoreFragment(
								MainActivity.this, MainActivity.this);
						changeContent(mTopScoreFragment);
						break;

					case 2:
						mTopShelfFragment = new TopShelfFragment(
								MainActivity.this, MainActivity.this);
						changeContent(mTopShelfFragment);
						break;

					case 3:
						mTopBookFragment = new TopBookFragment(
								MainActivity.this, MainActivity.this);
						changeContent(mTopBookFragment);
						break;
					}
					break;

				case 3:
					switch (childPosition) {
					case 0:
						mAboutFragment = new AboutFragment(MainActivity.this,
								MainActivity.this);
						changeContent(mAboutFragment);
						break;

					case 1:
						new AlertDialog.Builder(MainActivity.this)
								.setTitle(R.string.dialog_title)
								.setMessage(R.string.dialog_exit)
								.setPositiveButton(getString(R.string.exit),
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												defaultFinish();
												Process.killProcess(Process
														.myPid());
												System.exit(0);
											}
										})
								.setNegativeButton(getString(R.string.cancel),
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.cancel();
											}
										}).create().show();

						break;
					}
					break;
				}
				return false;
			}
		});
	}

	private void init() {
		initContent();
		initMenu();
	}

	/** 初始化菜单 **/
	private void initSlidingMenu() {
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setShadowDrawable(R.drawable.sidebar_right_shadow);
	}

	/** 初始化显示内容 **/
	private void initContent() {
		mSearchSimpleFragment = new SearchSimpleFragment(this, this);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(R.id.content, mSearchSimpleFragment);
		transaction.commit();
	}

	/** 修改显示的内容 **/
	private void changeContent(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.content, fragment);
		transaction.commit();
		showContent();
	}

	/** 初始化菜单列表内容 **/
	private void initMenu() {
		String[] mGroups = getResources().getStringArray(
				R.array.menu_group_list);
		String[] mChilds = getResources().getStringArray(
				R.array.menu_child_list);
		int[] mIcons = new int[] { R.drawable.side_bar_icon_search_simple,
				//R.drawable.side_bar_icon_search_adv,
				//R.drawable.side_bar_icon_search_more,
				R.drawable.side_bar_icon_reader_info,
				R.drawable.side_bar_icon_book_list,
				R.drawable.side_bar_icon_book_hist,
				R.drawable.side_bar_icon_top_lend,
				R.drawable.side_bar_icon_top_score,
				R.drawable.side_bar_icon_top_shelf,
				R.drawable.side_bar_icon_top_book,
				R.drawable.side_bar_icon_about, R.drawable.side_bar_icon_quit };
		List<List<Bundle>> mChildsList = new ArrayList<List<Bundle>>();
		for (int i = 0; i < mGroups.length; i++) {
			List<Bundle> list = new ArrayList<Bundle>();
			switch (i) {
			case 0:
				for (int j = 0; j < 1; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;

			case 1:
				for (int j = 1; j < 4; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;

			case 2:
				for (int j = 4; j < 8; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;

			case 3:
				for (int j = 8; j < 10; j++) {
					Bundle bundle = new Bundle();
					bundle.putString("name", mChilds[j]);
					bundle.putInt("icon", mIcons[j]);
					list.add(bundle);
				}
				break;
			}
			mChildsList.add(list);
		}
		mElvMenu.addHeaderView(mVMenuHead);
		MenuAdapter adapter = new MenuAdapter(mGroups, mChildsList);
		mElvMenu.setAdapter(adapter);
		for (int i = 0; i < mGroups.length; i++) {
			mElvMenu.expandGroup(i);
		}
	}

	private class MenuAdapter extends BaseExpandableListAdapter {
		private String[] mGroups;
		private List<List<Bundle>> mChilds;

		public MenuAdapter(String[] groups, List<List<Bundle>> childs) {
			mGroups = groups;
			mChilds = childs;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return mChilds.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ChildViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.slidingmenu_menu_child_item, null);
				holder = new ChildViewHolder();
				holder.mIcon = (ImageView) convertView
						.findViewById(R.id.menu_child_icon);
				holder.mName = (TextView) convertView
						.findViewById(R.id.menu_child_name);
				convertView.setTag(holder);
			} else {
				holder = (ChildViewHolder) convertView.getTag();
			}
			Bundle bundle = mChilds.get(groupPosition).get(childPosition);
			holder.mIcon.setImageResource(bundle.getInt("icon"));
			holder.mName.setText(bundle.getString("name"));
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return mChilds.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return mGroups[groupPosition];
		}

		@Override
		public int getGroupCount() {
			return mGroups.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			GroupViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(
						R.layout.slidingmenu_menu_group_item, null);
				holder = new GroupViewHolder();
				holder.mName = (TextView) convertView
						.findViewById(R.id.menu_group_name);
				convertView.setTag(holder);
			} else {
				holder = (GroupViewHolder) convertView.getTag();
			}
			holder.mName.setText(mGroups[groupPosition]);
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		class GroupViewHolder {
			TextView mName;
		}

		class ChildViewHolder {
			ImageView mIcon;
			TextView mName;
		}
	}

	@Override
	public void onOpen() {
		if (mSearchSimpleFragment != null) {
			mSearchSimpleFragment.slidingMenuOpen();
		}
	}

	/** 显示菜单列表 **/
	public void showMenu() {
		mSlidingMenu.showMenu();
	}

	/** 显示内容 **/
	public void showContent() {
		mSlidingMenu.showContent();
	}

}
