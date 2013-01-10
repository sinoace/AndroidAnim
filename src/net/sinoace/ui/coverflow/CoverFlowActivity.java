package net.sinoace.ui.coverflow;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;


public class CoverFlowActivity extends Activity {
	ImageView imageView;

	/** Called when the activity is first created. */
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.myuidemo);
		// imageView = (ImageView) findViewById(R.id.img);
		//
		// AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.1, 1);
		// alphaAnimation.setDuration(3000);
		// alphaAnimation.setAnimationListener(new AnimationListener() {
		//  
		// public void onAnimationStart(Animation animation) {
		// }
		//
		//  
		// public void onAnimationRepeat(Animation animation) {
		// }
		//
		//  
		// public void onAnimationEnd(Animation animation) {
		// imageView.setVisibility(View.GONE);
		// }
		// });
		//
		// imageView.setAnimation(alphaAnimation);
		// imageView.setVisibility(View.VISIBLE);
		CoverFlow cf = new CoverFlow(this);
		// cf.setBackgroundResource(R.drawable.shape);
		cf.setBackgroundColor(Color.BLACK);
		cf.setAdapter(new ImageAdapter(this));
		ImageAdapter imageAdapter = new ImageAdapter(this);
		cf.setAdapter(imageAdapter);
		// cf.setAlphaMode(false);
		// cf.setCircleMode(false);
		cf.setSelection(2, true);
		cf.setAnimationDuration(1000);
		setContentView(cf);
	}
}
