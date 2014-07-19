package com.wangwenfeng.ccnulib.ui.start;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.ui.MainActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @Title StartActivity.java
 * @Package com.wangwenfeng.ccnulib.ui.start
 * @Description 开始欢迎闪屏
 * @author Vin Wong http://www.wangwenfeng.com
 * @date 2013-12-11 下午7:44:25
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
		// 暂时不做任何操作		
	}

	@Override
	protected void setListener() {
		// 暂时不做任何操作		
	}

	@Override
	protected void init() {
		// 延迟1500毫秒发送消息到消息队列
				handler.sendEmptyMessageDelayed(0, 1500);		
	}
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 跳转到登录界面,并默认方式关闭当前界面
			startActivity(MainActivity.class);
			defaultFinish();
		}
	};
}
