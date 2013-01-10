
package net.sinoace.ui.lockscreen;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
	public void onReceive(Context ctx, Intent intent) {
		String action = "android.intent.action.BOOT_COMPLETED";
		if (intent.getAction().equals(action)) {
			//Toast.makeText(ctx, "LOCK SERVICE", 1000).show();
			Intent ss = new Intent(ctx, BitmapLockScrActivity.class);
			ss.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ctx.startActivity(ss);
			//Toast.makeText(ctx, "LOCK SERVICE", 1000).show();
		}
	}
}