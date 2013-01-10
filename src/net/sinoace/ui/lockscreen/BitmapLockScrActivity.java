package net.sinoace.ui.lockscreen;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.sinoace.anim.R;

public class BitmapLockScrActivity extends Activity {
	/** Called when the activity is first created. */
	private LinearLayout linearLayout1 = null;
	private SurfaceView sfv;
	private Canvas canvas;
	SurfaceHolder sfh;
	ImageView imageView = null;
	ImageView imageView2 = null;
	TextView timeTextView = null;
	TextView dateTextView = null;
	TextView netWorkTextView = null;
	TextView voltageTextView = null;

	private String mTime, mDate;
	private IntentFilter mIntentFilter;
	ImageView timeImageView1 = null;
	ImageView timeImageView2 = null;
	ImageView timeImageView3 = null;
	ImageView timeImageView4 = null;
	ImageView timeImageView5 = null;
	private static final int LCD_WIDTH = 480;
	private static final int LCD_HEIGHT = 800;
	private static final int MOVE_X_UNLOCK = 200;
	private static final int MOVE_Y_UNLOCK = 300;

	private static final int TEXT_SIZE = 25;

	private static final int bgX = 0;
	private static final int bgY = 0;
	private static final int bgW = 480;
	private static final int bgH = 801;

	private static final int voltageX = 20;
	private static final int voltageY = 430;
	private static final int voltageW = 480;
	private static final int voltageH = 60;

	private static final int mNetX = 20;
	private static final int mNetY = 500;
	private static final int mNetW = 480;
	private static final int mNetH = 60;

	private static final int mTimeX = 20;
	private static final int mTimeY = 570;
	private static final int mTimeW = 69;
	private static final int mTimeH = 98;

	private static final int mDateX = 20;
	private static final int mDateY = 680;
	private static final int mDateW = 480;
	private static final int mDateH = 100;

	public static boolean isFirstIn = true;
	AbsoluteLayout ab;
	AbsoluteLayout.LayoutParams imageParams;
	AbsoluteLayout.LayoutParams timeParams1;
	AbsoluteLayout.LayoutParams dateParams1;
	AbsoluteLayout.LayoutParams netParams1;
	AbsoluteLayout.LayoutParams voltageParams1;

	AbsoluteLayout.LayoutParams timeImageParams1;
	AbsoluteLayout.LayoutParams timeImageParams2;
	AbsoluteLayout.LayoutParams timeImageParams3;
	AbsoluteLayout.LayoutParams timeImageParams4;
	AbsoluteLayout.LayoutParams timeImageParams5;
	private Context mContext = null;

	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = BitmapLockScrActivity.this;

		mIntentFilter = new IntentFilter();
		mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

		startService(new Intent(BitmapLockScrActivity.this,
				BitmapLockService.class));
		if (isFirstIn == true) {
			Toast.makeText(mContext, getString(R.string.lock_scr_ini), 2000)
					.show();
			isFirstIn = false;
			this.finish();

		}
		showObsolute();
		// initViews();

		new TimeThread().start();
		playSond();
	}

	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerLock;
	AudioManager mAudioManager;
	public int floatstreamVolume = 0;

	private void playSond() {

		if (mediaPlayerLock == null) {
			mediaPlayerLock = MediaPlayer.create(this, R.raw.lock);
			mediaPlayerLock.setLooping(false);

			mediaPlayerLock.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mAudioManager = (AudioManager) this
					.getSystemService(Context.AUDIO_SERVICE);
			int maxVolume = mAudioManager
					.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

			floatstreamVolume = mAudioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume,
					0);

			if (!mediaPlayerLock.isPlaying()) {
				mediaPlayerLock.start();
			}
			mediaPlayerLock
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						//  
						/* �����ļ���������¼� */
						public void onCompletion(MediaPlayer arg0) {
							try {

								mediaPlayerLock.release();

							} catch (Exception e) {

							}
						}
					});
		}

	}

	private void initViews() {
		linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
		// sfv = (SurfaceView) this.findViewById(R.id.SurfaceView01);
		linearLayout1.setBackgroundColor(Color.TRANSPARENT);
		// sfv.setBackgroundColor(Color.TRANSPARENT);
		// sfh = sfv.getHolder();

		// Canvas canvas = sfh.lockCanvas(new Rect(0,
		// 0,getWindowManager().getDefaultDisplay().getWidth(),
		// getWindowManager().getDefaultDisplay().getHeight()));
		// canvas.drawColor(Color.TRANSPARENT);

	}

	private Context getContext() {
		return mContext;
	}

	private void showObsolute() {
		ab = new AbsoluteLayout(this);
		ab.setBackgroundColor(Color.TRANSPARENT); // ���ò��ֵ���ɫ
		setContentView(ab);// �Ѳ��ּӵ�activity����
		// Add imageView
		imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.lockscreen_bslockbg);
		imageParams = new AbsoluteLayout.LayoutParams(bgW, bgH, bgX, bgY);
		ab.addView(imageView, imageParams);

		// Add voltage
		voltageTextView = new TextView(this);
		voltageTextView.setText("");
		voltageTextView.setTextSize(TEXT_SIZE);
		voltageTextView.setTextColor(Color.WHITE);
		voltageParams1 = new AbsoluteLayout.LayoutParams(voltageW, voltageH,
				voltageX, voltageY);
		ab.addView(voltageTextView, voltageParams1);
		// Add NetWorkName
		netWorkTextView = new TextView(this);
		netWorkTextView.setText(getNetWorkName());
		netWorkTextView.setTextSize(TEXT_SIZE);
		netWorkTextView.setTextColor(Color.WHITE);
		netParams1 = new AbsoluteLayout.LayoutParams(mNetW, mNetH, mNetX, mNetY);
		ab.addView(netWorkTextView, netParams1);
		// Add time

		Date now = new Date();
		/*
		 * mTime=DateFormat.getTimeFormat(getContext()).format(now);
		 * //mDate=DateFormat.getDateFormat(getContext()).format(now);
		 * timeTextView= new TextView(this); timeTextView.setText(mTime);
		 * timeTextView.setTextSize(100); timeTextView.setTextScaleX((float)1);
		 * timeTextView.setTextColor(Color.WHITE); timeParams1 = new
		 * AbsoluteLayout.LayoutParams(mTimeW,mTimeH,mTimeX,mTimeY);
		 * ab.addView(timeTextView,timeParams1);
		 */
		setTimeImage();

		// Add date
		SimpleDateFormat format1;
		if (Locale.getDefault().getLanguage().equals("zh")) {
			format1 = new SimpleDateFormat("MM-dd-EEEE", Locale.CHINA);
		} else {
			format1 = new SimpleDateFormat("MM-dd-EEEE", Locale.ENGLISH);
		}
		mDate = format1.format(now);

		dateTextView = new TextView(this);
		dateTextView.setText(mDate);
		dateTextView.setTextSize(TEXT_SIZE);
		dateTextView.setTextColor(Color.WHITE);
		dateParams1 = new AbsoluteLayout.LayoutParams(mDateW, mDateH, mDateX,
				mDateY);
		ab.addView(dateTextView, dateParams1);
	}

	// ��ʼ����ʱ��
	private int resId = 0;

	private int getImageResource(int index) {
		char c = 0;
		Date now = new Date();
		SimpleDateFormat format1;
		/*
		 * ContentResolver cv = this.getContentResolver(); String strTimeFormat
		 * = android.provider.Settings.System.getString(cv,
		 * android.provider.Settings.System.TIME_12_24);
		 * 
		 * if(strTimeFormat!=null) { if(strTimeFormat.equals("24")) { format1
		 * =new SimpleDateFormat("HH:mm:ss"); } else { format1 =new
		 * SimpleDateFormat("hh:mm:ss"); } } else { format1 =new
		 * SimpleDateFormat("hh:mm:ss"); }
		 */
		format1 = new SimpleDateFormat("HH:mm:ss");
		mTime = format1.format(now);
		// mTime=DateFormat.getTimeFormat(getContext()).format(now);
		c = mTime.charAt(index);
		switch (c) {
		case '0':
			resId = R.drawable.lockscreen_t0;
			break;
		case '1':
			resId = R.drawable.lockscreen_t1;
			break;
		case '2':
			resId = R.drawable.lockscreen_t2;
			break;
		case '3':
			resId = R.drawable.lockscreen_t3;
			break;
		case '4':
			resId = R.drawable.lockscreen_t4;
			break;
		case '5':
			resId = R.drawable.lockscreen_t5;
			break;
		case '6':
			resId = R.drawable.lockscreen_t6;
			break;
		case '7':
			resId = R.drawable.lockscreen_t7;
			break;
		case '8':
			resId = R.drawable.lockscreen_t8;
			break;
		case '9':
			resId = R.drawable.lockscreen_t9;
			break;
		default:
			resId = R.drawable.lockscreen_t8;
			break;
		}
		return resId;
	}

	private void setTimeImageResource() {
		timeImageView1.setImageResource(getImageResource(0));
		timeImageView2.setImageResource(getImageResource(1));
		timeImageView3.setImageResource(R.drawable.lockscreen_tm);
		timeImageView4.setImageResource(getImageResource(3));
		timeImageView5.setImageResource(getImageResource(4));
	}

	private void setTimeImage() {

		timeImageView1 = new ImageView(this);
		timeImageView2 = new ImageView(this);
		timeImageView3 = new ImageView(this);
		timeImageView4 = new ImageView(this);
		timeImageView5 = new ImageView(this);
		setTimeImageResource();

		timeImageParams1 = new AbsoluteLayout.LayoutParams(mTimeW, mTimeH,
				mTimeX, mTimeY);
		timeImageParams2 = new AbsoluteLayout.LayoutParams(mTimeW, mTimeH,
				mTimeX + mTimeW, mTimeY);
		timeImageParams3 = new AbsoluteLayout.LayoutParams(mTimeW, mTimeH,
				mTimeX + 2 * mTimeW, mTimeY);
		timeImageParams4 = new AbsoluteLayout.LayoutParams(mTimeW, mTimeH,
				mTimeX + 3 * mTimeW, mTimeY);
		timeImageParams5 = new AbsoluteLayout.LayoutParams(mTimeW, mTimeH,
				mTimeX + 4 * mTimeW, mTimeY);

		ab.addView(timeImageView1, timeImageParams1);
		ab.addView(timeImageView2, timeImageParams2);
		ab.addView(timeImageView3, timeImageParams3);
		ab.addView(timeImageView4, timeImageParams4);
		ab.addView(timeImageView5, timeImageParams5);
	}

	// ��ȡ��Ӫ��
	private String getNetWorkName() {
		String netWorkName = null;
		TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String operator = telManager.getSimOperator();
		if (operator != null) {
			if (operator.equals("46000") || operator.equals("46002")
					|| operator.equals("46007")) {
				netWorkName = getString(R.string.cmcc);// �й��ƶ�
			} else if (operator.equals("46001")) {
				netWorkName = getString(R.string.unicom);// �й���ͨ
			} else if (operator.equals("46003")) {
				netWorkName = getString(R.string.dianxin);// �й����
			} else {
				netWorkName = getString(R.string.no_service);
			}
		}

		return netWorkName;

	}

	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			return handleActionDownEvenet(event);
		case MotionEvent.ACTION_MOVE:
			handleActionMoveEvenet(event);
			break;
		case MotionEvent.ACTION_UP:
			handleActionUpEvent(event);
			break;
		// case MotionEvent.ACTION_CANCEL:
		// handleActionUpEvent(event);
		// break;
		// case MotionEvent.ACTION_OUTSIDE:
		// handleActionUpEvent(event);
		// break;
		default:
			redrawStatic();
			break;
		}
		return super.onTouchEvent(event);
	}

	private void redrawStatic() {

		// bg�ָ�
		penDownPointx = -1;
		penDownPointy = -1;
		isHit = false;
		imageParams.x = bgX;
		imageParams.y = bgY;
		imageView.setLayoutParams(imageParams);

		// voltage
		voltageParams1.x = voltageX;
		voltageParams1.y = voltageY;
		voltageTextView.setLayoutParams(voltageParams1);

		// netWork�ָ�
		netParams1.x = mNetX;
		netParams1.y = mNetY;
		netWorkTextView.setLayoutParams(netParams1);
		// time�ָ�
		timeImageParams1.x = mTimeX;
		timeImageParams1.y = mTimeY;
		timeImageView1.setLayoutParams(timeImageParams1);

		timeImageParams2.x = mTimeX + mTimeW;
		timeImageParams2.y = mTimeY;
		timeImageView2.setLayoutParams(timeImageParams2);

		timeImageParams3.x = mTimeX + 2 * mTimeW;
		timeImageParams3.y = mTimeY;
		timeImageView3.setLayoutParams(timeImageParams3);

		timeImageParams4.x = mTimeX + 3 * mTimeW;
		timeImageParams4.y = mTimeY;
		timeImageView4.setLayoutParams(timeImageParams4);

		timeImageParams5.x = mTimeX + 4 * mTimeW;
		timeImageParams5.y = mTimeY;
		timeImageView5.setLayoutParams(timeImageParams5);
		/*
		 * timeParams1.x=mTimeX; timeParams1.y=mTimeY;
		 * timeTextView.setLayoutParams(timeParams1);
		 */
		// date�ָ�
		dateParams1.x = mDateX;
		dateParams1.y = mDateY;
		dateTextView.setLayoutParams(dateParams1);
	}

	private boolean isHit = false;
	private int penDownPointx = -1;
	private int penDownPointy = -1;

	private boolean handleActionDownEvenet(MotionEvent event) {

		penDownPointx = (int) event.getX();
		penDownPointy = (int) event.getY();
		isHit = true;
		return isHit;
	}

	private void handleActionMoveEvenet(MotionEvent event) {
		int left, right, bottom, up;
		int x = (int) event.getX();
		int y = (int) event.getY();

		if (isHit == true) {
			// bg
			imageParams.x = x - penDownPointx + bgX;
			imageParams.y = y - penDownPointy + bgY;
			imageView.setLayoutParams(imageParams);

			// voltage
			voltageParams1.x = voltageX + x - penDownPointx;
			voltageParams1.y = voltageY + y - penDownPointy;
			voltageTextView.setLayoutParams(voltageParams1);
			// netWork
			netParams1.x = mNetX + x - penDownPointx;
			netParams1.y = mNetY + y - penDownPointy;
			netWorkTextView.setLayoutParams(netParams1);
			// time
			timeImageParams1.x = x - penDownPointx + mTimeX;
			timeImageParams1.y = y - penDownPointy + mTimeY;
			timeImageView1.setLayoutParams(timeImageParams1);

			timeImageParams2.x = x - penDownPointx + mTimeX + mTimeW;
			timeImageParams2.y = y - penDownPointy + mTimeY;
			timeImageView2.setLayoutParams(timeImageParams2);

			timeImageParams3.x = x - penDownPointx + mTimeX + 2 * mTimeW;
			timeImageParams3.y = y - penDownPointy + mTimeY;
			timeImageView3.setLayoutParams(timeImageParams3);

			timeImageParams4.x = x - penDownPointx + mTimeX + 3 * mTimeW;
			timeImageParams4.y = y - penDownPointy + mTimeY;
			timeImageView4.setLayoutParams(timeImageParams4);

			timeImageParams5.x = x - penDownPointx + mTimeX + 4 * mTimeW;
			timeImageParams5.y = y - penDownPointy + mTimeY;
			timeImageView5.setLayoutParams(timeImageParams5);
			/*
			 * timeParams1.x=x-penDownPointx+mTimeX;
			 * timeParams1.y=y-penDownPointy+mTimeY;
			 * timeTextView.setLayoutParams(timeParams1);
			 */
			// date
			dateParams1.x = x - penDownPointx + mDateX;
			dateParams1.y = y - penDownPointy + mDateY;
			dateTextView.setLayoutParams(dateParams1);
		}

	}

	public void onDestroy() {
		if (mediaPlayer != null) {

			// mediaPlayer.stop();
			// mediaPlayer.release();
		}
		super.onDestroy();
	}

	private void handleActionUpEvent(MotionEvent event) {
		int moveX, moveY;
		int x = (int) event.getX();
		int y = (int) event.getY();
		moveX = Math.abs(x - penDownPointx);
		moveY = Math.abs(y - penDownPointy);

		if (isHit == false) {
			penDownPointx = -1;
			penDownPointy = -1;
			return;
		}

		if ((moveX > MOVE_X_UNLOCK) || (moveY > MOVE_Y_UNLOCK)) {
			penDownPointx = -1;
			penDownPointy = -1;
			// Toast.makeText(mContext,getString(R.string.unlock_success),
			// 1000).show();

			if (mediaPlayer == null) {
				mediaPlayer = MediaPlayer.create(this, R.raw.unlock);
				mediaPlayer.setLooping(false);
				if (!mediaPlayer.isPlaying()) {
					mediaPlayer.start();
				}
				mediaPlayer
						.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
							//  
							/* �����ļ���������¼� */
							public void onCompletion(MediaPlayer arg0) {
								try {

									mediaPlayer.release();

								} catch (Exception e) {

								}
							}
						});
			}
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
					floatstreamVolume, 0);
			BitmapLockScrActivity.this.finish();
		} else {
			redrawStatic();
		}

	}

	public class TimeThread extends Thread {
		 
		public void run() {
			do {
				try {
					Thread.sleep(1000 * 10);
					Message msg = new Message();
					msg.what = 1;
					mHandler.sendMessage(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (true);
		}
	}

	private Handler mHandler = new Handler() {
		 
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				Date now = new Date();
				mTime = DateFormat.getTimeFormat(getContext()).format(now);
				// mDate=DateFormat.getDateFormat(getContext()).format(now);
				// SimpleDateFormat format1 =new
				// SimpleDateFormat("yyyy��MM��dd�� EEEE",Locale.CHINA);
				SimpleDateFormat format1;
				if (Locale.getDefault().getLanguage().equals("zh")) {
					format1 = new SimpleDateFormat("MM-dd-EEEE", Locale.CHINA);
				} else {
					format1 = new SimpleDateFormat("MM-dd-EEEE", Locale.ENGLISH);
				}
				mDate = format1.format(now);
				dateTextView.setText(mDate);
				// timeTextView.setText(mTime);
				setTimeImageResource();
				break;

			default:
				break;
			}
		}
	};

	private String BatteryV, BatteryT, BatteryStatus, BatteryStatus2,
			BatteryTemp;
	private double currentVoltage = 0, totalVoltage;
	private double strIntVoltage;
	private int chargeStatus = 0;

	protected void onResume() {
		super.onResume();
		// ע����Ϣ������
		registerReceiver(mIntentReceiver, mIntentFilter);
	}

	// ������Ϣ������
	private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
		 
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// Ҫ�����ǲ�������Ҫ�������Ϣ
			if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
				// ��ص���������

				Log.d("Battery", "" + intent.getIntExtra("level", 0));
				currentVoltage = intent.getIntExtra("level", 0);
				// ����������
				Log.d("Battery", "" + intent.getIntExtra("scale", 0));
				totalVoltage = intent.getIntExtra("scale", 0);
				// ��ط���
				DecimalFormat df1 = new DecimalFormat("##%");
				strIntVoltage = currentVoltage / totalVoltage;

				chargeStatus = intent.getIntExtra("status",
						BatteryManager.BATTERY_STATUS_UNKNOWN);

				if (chargeStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
					// voltageTextView.setText("");
					voltageTextView.setText(df1.format(strIntVoltage) + "("
							+ getString(R.string.charge_in) + ")");
				} else {
					voltageTextView.setText("");
				}
				Log.d("Battery", "" + intent.getIntExtra("voltage", 0));
				// ����¶�
				Log.d("Battery", "" + intent.getIntExtra("temperature", 0));

				// ���״̬��������һ������
				// BatteryManager.BATTERY_STATUS_CHARGING ��ʾ�ǳ��״̬
				// BatteryManager.BATTERY_STATUS_DISCHARGING �ŵ���
				// BatteryManager.BATTERY_STATUS_NOT_CHARGING δ���
				// BatteryManager.BATTERY_STATUS_FULL �����
				Log.d("Battery",
						""
								+ intent.getIntExtra("status",
										BatteryManager.BATTERY_STATUS_UNKNOWN));

				// ������� BatteryManager.BATTERY_PLUGGED_AC
				// ��ʾ�ǳ�������������ֵ����ʾ�� USB
				Log.d("Battery", "" + intent.getIntExtra("plugged", 0));

				// ��ؽ������������Ҳ��һ������
				// BatteryManager.BATTERY_HEALTH_GOOD ����
				// BatteryManager.BATTERY_HEALTH_OVERHEAT ����
				// BatteryManager.BATTERY_HEALTH_DEAD û��
				// BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE ���ѹ
				// BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE δ֪����
				Log.d("Battery",
						""
								+ intent.getIntExtra("health",
										BatteryManager.BATTERY_HEALTH_UNKNOWN));
			}
		}
	};

	// ���ε�Back��

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

			return true;
		} else
			return super.onKeyDown(keyCode, event);

	}

	// ���ε�Home��
	public void onAttachedToWindow() {
		this.getWindow().setType(
				WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
		super.onAttachedToWindow();
	}

}
