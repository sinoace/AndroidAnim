package net.sinoace.ui.drawroll;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import net.sinoace.anim.R;

public class DrawRollActivity extends Activity {
	private View view;
	private Button btn;
	private PopupWindow mPopupWindow;
	private View[] btns;

	/** Called when the activity is first created. */
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawroll);
		btn = (Button) this.findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopupWindow(btn);
			}

		});

		initPopupWindow(R.layout.drawroll_popwindow);

	}

	private void initPopupWindow(int resId) {
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		view = mLayoutInflater.inflate(resId, null);

		mPopupWindow = new PopupWindow(view, 400, LayoutParams.WRAP_CONTENT);
		mPopupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.drawroll_bg_frame));
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		mPopupWindow.update();
		mPopupWindow.setTouchable(true);
		mPopupWindow.setFocusable(true);

		btns = new View[3];
		btns[0] = view.findViewById(R.id.btn_0);
		btns[1] = view.findViewById(R.id.btn_1);
		btns[2] = view.findViewById(R.id.btn_2);
		btns[0].setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// doSomething
			}
		});
		btns[1].setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// doSomething
			}
		});
		btns[2].setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// doSomething
			}
		});
	}

	private void showPopupWindow(View view) {
		if (!mPopupWindow.isShowing()) {
			mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
		}
	}

	public Bitmap setRotate(int resId) {
		Matrix mFgMatrix = new Matrix();
		Bitmap mFgBitmap = BitmapFactory.decodeResource(getResources(), resId);
		mFgMatrix.setRotate(180f);
		return mFgBitmap = Bitmap.createBitmap(mFgBitmap, 0, 0,
				mFgBitmap.getWidth(), mFgBitmap.getHeight(), mFgMatrix, true);
	}
}