package net.sinoace.ui.lockscreen;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class BitmapLockService extends Service {
	private static String TAG = "ZdLockService";
	private Intent zdLockIntent = null;
	private Handler mainHandler = null;

	 
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public void onCreate() {
		super.onCreate();

		zdLockIntent = new Intent(BitmapLockService.this,
				BitmapLockScrActivity.class);
		zdLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		IntentFilter mScreenOffFilter = new IntentFilter();
		mScreenOffFilter.addAction("android.intent.action.SCREEN_OFF");
		mScreenOffFilter.addAction("android.intent.action.SCREEN_ON");
		BitmapLockService.this.registerReceiver(mScreenOffReceiver,
				mScreenOffFilter);
	}

	 
	public int onStartCommand(Intent intent, int flags, int startId) {

		return Service.START_STICKY;

	}

	 
	public void onDestroy() {
		super.onDestroy();
		BitmapLockService.this.unregisterReceiver(mScreenOffReceiver);
		startService(new Intent(BitmapLockService.this, BitmapLockService.class));
	}

	private KeyguardManager mKeyguardManager = null;
	private KeyguardManager.KeyguardLock mKeyguardLock = null;

	private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
		 
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			Log.i(TAG, intent.toString());

			if (action.equals(Intent.ACTION_TIME_TICK)
					|| action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
				mainHandler.obtainMessage(1).sendToTarget();
			}

			if (action.equals("android.intent.action.SCREEN_OFF")
					|| action.equals("android.intent.action.SCREEN_ON")) {
				mKeyguardManager = (KeyguardManager) context
						.getSystemService(Context.KEYGUARD_SERVICE);
				mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1");
				mKeyguardLock.disableKeyguard();
				startActivity(zdLockIntent);
			}
		}

	};

}
