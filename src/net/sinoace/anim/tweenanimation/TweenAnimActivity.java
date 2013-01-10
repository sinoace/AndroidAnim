package net.sinoace.anim.tweenanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.sinoace.anim.R;
import net.sinoace.util.Constants;

public class TweenAnimActivity extends Activity {

	/** Called when the activity is first created. */
	protected Animation animation;
	private int[] ID;
	private int the_Animation_ID = 0;
	private ImageView mTweenImage;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween);
		ID = new int[] { R.anim.myanimation_set, R.anim.alpha,
				R.anim.scale, R.anim.translate,
				R.anim.rotate, R.anim.alpha_scale,
				R.anim.alpha_translate, R.anim.alpha_rotate,
				R.anim.scale_translate, R.anim.scale_rotate,
				R.anim.translate_rotate, R.anim.alpha_scale_translate,
				R.anim.alpha_scale_rotate, R.anim.alpha_translate_rotate,
				R.anim.scale_translate_rotate,
				R.anim.alpha_scale_translate_rotate, R.anim.myown_design };
		mTweenImage = (ImageView)findViewById(R.id.tween_image);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		if (bundle != null) {
			int index = (Integer) bundle.get(Constants.INDEX);
			load_start_Animation(index);
		}

	}

	private void load_start_Animation(int i) {

		the_Animation_ID = ID[i];
		if (the_Animation_ID != 0) {
			animation = AnimationUtils.loadAnimation(this, the_Animation_ID);
			mTweenImage.startAnimation(animation);
		}
	}
}