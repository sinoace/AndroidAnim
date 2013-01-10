package net.sinoace.ui.qqlogin;

import net.sinoace.anim.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;


public class QqLoginActivity extends Activity {
	boolean isConnected;

	/** Called when the activity is first created. */
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qqlogin_main);

		isConnected = NetWorkStatus();		
	}

	private boolean NetWorkStatus() {
		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		cwjManager.getActiveNetworkInfo();

		if (cwjManager.getActiveNetworkInfo() != null) {
			netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
			netSataus = true;
		}

		if (netSataus == false) {
			Builder b = new AlertDialog.Builder(this).setTitle("û�п��õ�����")
					.setMessage("�뿪��GPRS��WIFI��������");
			b.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					Intent mIntent = new Intent("/");
					ComponentName comp = new ComponentName(
							"com.android.settings",
							"com.android.settings.WirelessSettings");
					mIntent.setComponent(comp);
					mIntent.setAction("android.intent.action.VIEW");
					startActivityForResult(mIntent, 0); // �����������ɺ���Ҫ�ٴν��в�����������д�������룬�����ﲻ����д
				}
			}).setNeutralButton("ȡ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					dialog.cancel();
				}
			}).show();
		}
		return netSataus;
	}
}