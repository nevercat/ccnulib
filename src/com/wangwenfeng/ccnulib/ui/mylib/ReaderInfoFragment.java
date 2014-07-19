package com.wangwenfeng.ccnulib.ui.mylib;

import com.wangwenfeng.ccnulib.R;
import com.wangwenfeng.ccnulib.data.StudentInfo;
import com.wangwenfeng.ccnulib.ui.MainActivity;
import com.wangwenfeng.ccnulib.ui.common.LibActionBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReaderInfoFragment extends ReaderInfoFragmentBase {

	private TextView name, sex, number, barNumber, certificateDate,
			effectiveDate, expireData, readerType, grade, maxBorrow,
			sumBorrowed, workPlace, position, idNumber, tel, email, zipCode;

	public ReaderInfoFragment(Activity activity, Context context) {
		super(activity, context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.reader_info, container, false);
		findViewById();
		setListener();
		init();

		name.setText(StudentInfo.name);
		sex.setText(StudentInfo.sex);
		number.setText(StudentInfo.number);
		barNumber.setText(StudentInfo.barNumber);
		certificateDate.setText(StudentInfo.certificateDate);
		effectiveDate.setText(StudentInfo.effectiveDate);
		expireData.setText(StudentInfo.expireData);
		readerType.setText(StudentInfo.readerType);
		grade.setText(StudentInfo.grade);
		maxBorrow.setText(StudentInfo.maxBorrow);
		sumBorrowed.setText(StudentInfo.sumBorrowed);
		workPlace.setText(StudentInfo.workPlace);
		position.setText(StudentInfo.position);
		idNumber.setText(StudentInfo.idNumber);
		tel.setText(StudentInfo.tel);
		email.setText(StudentInfo.email);
		zipCode.setText(StudentInfo.zipCode);

		int toExpire = Integer.parseInt(StudentInfo.toExpire);

		if (toExpire > 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("警告");
			builder.setMessage("您有" + toExpire + "本书在5天内即将过期，请注意去当前借阅查看并记得及时归还！");
			/*builder.setPositiveButton("是",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							getFragmentManager()
									.beginTransaction()
									.replace(
											R.id.reader_info_container,
											new BookListFragment(getActivity(),
													getActivity())).commit();
						}
					});
			builder.setNegativeButton("否",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});*/
			AlertDialog ad = builder.create();
			ad.show();
		}
		return mView;
	}

	@Override
	protected void findViewById() {
		mLibActionBar = (LibActionBar) findViewById(R.id.action_bar);
		name = (TextView) findViewById(R.id.nameText);
		sex = (TextView) findViewById(R.id.sexText);
		number = (TextView) findViewById(R.id.numberText);
		barNumber = (TextView) findViewById(R.id.barNumberText);
		certificateDate = (TextView) findViewById(R.id.certificateDateText);
		effectiveDate = (TextView) findViewById(R.id.effectiveDateText);
		expireData = (TextView) findViewById(R.id.expireDataText);
		readerType = (TextView) findViewById(R.id.readerTypeText);
		grade = (TextView) findViewById(R.id.gradeText);
		maxBorrow = (TextView) findViewById(R.id.maxBorrowText);
		sumBorrowed = (TextView) findViewById(R.id.sumBorrowedText);
		workPlace = (TextView) findViewById(R.id.workPlaceText);
		position = (TextView) findViewById(R.id.positionText);
		idNumber = (TextView) findViewById(R.id.idNumberText);
		tel = (TextView) findViewById(R.id.telText);
		email = (TextView) findViewById(R.id.emailText);
		zipCode = (TextView) findViewById(R.id.zipCodeText);
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

}
