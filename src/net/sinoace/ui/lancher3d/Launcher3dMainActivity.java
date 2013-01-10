package net.sinoace.ui.lancher3d;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;

import net.sinoace.anim.R;

public class Launcher3dMainActivity extends ActivityGroup implements OnClickListener{
	private static final String TAG = "IsmsMainActivity";
	public static final String FIRST_INTENT_TAG = "first";
	public static final String SECOND_INTENT_TAG = "second";
	public static final String THIRD_INTENT_TAG = "third";
	public static final int FIRST_VIEW = 0;
	public static final int SECOND_VIEW = 1;
	public static final int THIRD_VIEW = 2;
	Launcher3dScrollLayout mRoot;
	private View mFirstView, mSecondView, mThirdView;
    
    /**
     * This field should be made private, so it is hidden from the SDK.
     * {@hide}
     */
    public LocalActivityManager mLocalActivityManager;
    
    public Handler mHandler = new Handler(){
    	 
    	public void handleMessage(Message msg) {
    		
    		super.handleMessage(msg);
    		switch (msg.what) {
			case FIRST_VIEW:
				break;
			case SECOND_VIEW:
				break;
				
			case THIRD_VIEW:
				break;
			default:
				break;
			}
    	}
    };
    
    public Launcher3dMainActivity() {
        this(true);
    }
    
    public Launcher3dMainActivity(boolean singleActivityMode) {
    	 
//        mLocalActivityManager = new LocalActivityManager(this, singleActivityMode);
    }

     
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocalActivityManager = getLocalActivityManager();
        setContentView(R.layout.launcher3d_main);
        mRoot = (Launcher3dScrollLayout) findViewById(R.id.root);
       
        initView();
    }

    
    public void initView() {

    	mRoot.removeAllViews();
    	
    	Intent firstIntent = new Intent(this, Launcher3dFirstActivity.class);
		mFirstView = activityToView(this, firstIntent, FIRST_INTENT_TAG);
		mFirstView.setTag(FIRST_INTENT_TAG);
		mRoot.addView(mFirstView);
		
		Intent secondIntent = new Intent(this, Launcher3dSecondActivity.class);
		mSecondView = activityToView(this, secondIntent, SECOND_INTENT_TAG);
		mSecondView.setTag(SECOND_INTENT_TAG);
		mRoot.addView(mSecondView);

		Intent thirdIntent = new Intent(this, Launcher3dThirdActivity.class);
		mThirdView = activityToView(this, thirdIntent, THIRD_INTENT_TAG);
		mThirdView.setTag(THIRD_INTENT_TAG);
		mRoot.addView(mThirdView);
		
    }
    
    public View activityToView(Context parent, Intent intent, String tag){

   	 	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	    Window w = mLocalActivityManager.startActivity(tag, intent);
	    View wd = w != null ? w.getDecorView() : null;
        if (wd != null) {
           wd .setVisibility(View.VISIBLE);
           wd .setFocusableInTouchMode(true);
           ((ViewGroup) wd ).setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
       }
       return wd ;

	}
    
     
    protected void onRestart() {
    	super.onRestart();

    	Log.d(TAG, "onRestart");

    }
    
     
    protected void onStart() {

    	Log.d(TAG, "onStart  this.getCurrentActivity() = " + this.getCurrentActivity());

    	super.onStart();
    	
    }
    
     
    public boolean dispatchKeyEvent(KeyEvent event) {
    	return super.dispatchKeyEvent(event);
    }

     
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
       
    }

     
    protected void onPause() {
    	
        super.onPause();

        Log.d(TAG, "onPause");
        
    }

     
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
        
    }

	 
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

    
}
