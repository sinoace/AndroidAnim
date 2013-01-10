package net.sinoace.anima.activityswitch;

import java.util.ArrayList;
import java.util.List;

import net.sinoace.anim.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Activity1 extends Activity {

	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		Button bt = (Button) findViewById(R.id.bt);
		final Spinner mAnimSp = (Spinner) findViewById(R.id.animation_sp);
		Button mButton = (Button) findViewById(R.id.bt);

		// 通过资源文件获取Spinner填充内容
		String[] ls = getResources().getStringArray(R.array.anim_type);
		List<String> list = new ArrayList<String>();
		// 把数组内容填充 到集合
		for (int i = 0; i < ls.length; i++) {
			list.add(ls[i]);
		}
		ArrayAdapter<String> animType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		animType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mAnimSp.setAdapter(animType);
		mAnimSp.setSelection(0);

		mButton.setOnClickListener(new OnClickListener() {
			 
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Activity1.this, Activity2.class);
				startActivity(intent);

				switch (mAnimSp.getSelectedItemPosition()) {
				case 0:
					/*
					 * 注意：此方法只能在startActivity和finish方法之后调用。
					 * 第一个参数为第一个Activity离开时的动画，第二参数为所进入的Activity的动画效果
					 */
					//淡入淡出效果
					overridePendingTransition(R.anim.fade, R.anim.hold);
					break;
				case 1:
					//放大淡出效果
					overridePendingTransition(R.anim.my_scale_action,
							R.anim.my_alpha_action);
					break;
				case 2:
					//转动淡出效果1
					overridePendingTransition(R.anim.scale_rotate,
							R.anim.my_alpha_action);
					break;
				case 3:
					//转动淡出效果2
					overridePendingTransition(R.anim.scale_translate_rotate,
							R.anim.my_alpha_action);
					break;
				case 4:
					//左上角展开淡出效果
					overridePendingTransition(R.anim.scale_translate,
							R.anim.my_alpha_action);
					break;
				case 5:
					//压缩变小淡出效果
					overridePendingTransition(R.anim.hyperspace_in,
							R.anim.hyperspace_out);
					break;
				case 6:
					//右往左推效果
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
					break;
				case 7:
					//下往上推
					overridePendingTransition(R.anim.push_up_in,
							R.anim.push_up_out);
					break;
				case 8:
					//左右交叉
					overridePendingTransition(R.anim.slide_left,
							R.anim.slide_right);
					break;
				case 9:
					//放大淡出效果
					overridePendingTransition(R.anim.wave_scale,
							R.anim.my_alpha_action);
					break;
				case 10:
					//缩小效果
					overridePendingTransition(R.anim.zoom_enter,
							R.anim.zoom_exit);
					break;
				case 11:
					//上下交错
					overridePendingTransition(R.anim.slide_up_in,
							R.anim.slide_down_out);
					break;
				}
			}
		});
	}
}