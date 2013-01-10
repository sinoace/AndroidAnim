package net.sinoace.anima.activityswitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import net.sinoace.anim.R;

public class Activity2 extends Activity {

	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		Button bt2 = (Button) findViewById(R.id.bt2);
		bt2.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Activity2.this, Activity1.class);
				startActivity(intent);
				overridePendingTransition(R.anim.a2, R.anim.a1);
				Activity2.this.finish();
			}
		});
	}
	
	 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是返回键，并且没有重复
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
			return false;
		}
		return false;
	}
}
