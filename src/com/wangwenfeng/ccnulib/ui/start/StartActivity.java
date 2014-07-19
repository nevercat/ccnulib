package com.wangwenfeng.ccnulib.ui.start;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.MainActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @Title StartActivity.java
 * @Package com.wangwenfeng.ccnulib.ui.start
 * @Description ��ʼ��ӭ����
 * @author Vin Wong http://www.wangwenfeng.com
 * @date 2013-12-11 ����7:44:25
 * @version v1.0
 */
public class StartActivity extends StartActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		// ��ʱ�����κβ���		
	}

	@Override
	protected void setListener() {
		// ��ʱ�����κβ���		
	}

	@Override
	protected void init() {
		// �ӳ�1500���뷢����Ϣ����Ϣ����
				handler.sendEmptyMessageDelayed(0, 1500);		
	}
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ��ת����¼����,��Ĭ�Ϸ�ʽ�رյ�ǰ����
			startActivity(MainActivity.class);
			defaultFinish();
		}
	};
}
