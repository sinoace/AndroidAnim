package net.sinoace.anim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import net.sinoace.anim.R;

import net.sinoace.anim.drawableanimation.FrameAnimActivity;
import net.sinoace.anim.tweenanimation.TweenAnimMainActivity;
import net.sinoace.anima.activityswitch.Activity1;

public class AnimMainActivity extends Activity implements OnItemClickListener,
		OnItemSelectedListener {
	private ListView mylist;
	private ArrayAdapter<String> arrayAdapter;
	private String[] contentString = new String[] { "Tween Animation",
			"Frame Animation", "Property Animation" };

	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_activity_main);
		init();
	}

	private void init() {
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, contentString);
		mylist = (ListView) findViewById(R.id.ListView01);
		mylist.setAdapter(arrayAdapter);
		mylist.setOnItemClickListener(this);
		mylist.setOnItemSelectedListener(this);
		mylist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	 
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	 
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		setTitle(arrayAdapter.getItem(arg2));
		mylist.setItemChecked(arg2, true);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	 
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		Class<?> cls = null;
		if (index == 0) {
			cls = TweenAnimMainActivity.class;
		} else if (index == 1) {
			cls = FrameAnimActivity.class;
		} else {
			
		}
		if (cls != null) {
			Intent intent = new Intent();
			intent.setClass(this, cls);
			startActivity(intent);
		}
	}

}
