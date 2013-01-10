package net.sinoace.anim.tweenanimation;

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
import net.sinoace.util.Constants;

public class TweenAnimMainActivity extends Activity implements OnItemClickListener,
		OnItemSelectedListener {

	private ListView mylist;
	private ArrayAdapter<String> arrayAdapter;
	private String[] contentString = new String[] { "示例", "透明动画", "伸缩动画",
			"移动动画", "旋转动画", "透明_伸缩", "透明_移动", "透明_旋转", "伸缩_移动", "伸缩_旋转",
			"移动_旋转", "透明_伸缩_移动", "透明_伸缩_旋转", "透明_移动_旋转", "伸缩_移动_旋转",
			"透明_伸缩_移动_旋转", "myown_Design " };

	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
		return super.onTouchEvent(event);
	}

	 
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
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
		Intent intent = new Intent(this,TweenAnimActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.INDEX, index);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
