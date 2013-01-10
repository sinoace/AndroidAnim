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

import net.sinoace.anima.activityswitch.Activity1;
import net.sinoace.ui.bookpage.BookpageActivity;
import net.sinoace.ui.bookshelf.BookshelfReaderActivity;
import net.sinoace.ui.bubblechat.ChatActivity;
import net.sinoace.ui.coverflow.CoverFlowActivity;
import net.sinoace.ui.drawroll.DrawRollActivity;
import net.sinoace.ui.lancher3d.Launcher3dMainActivity;
import net.sinoace.ui.listviewgridview.ListViewActivity;
import net.sinoace.ui.loadingview.LoadingViewMainActivity;
import net.sinoace.ui.lockscreen.BitmapLockScrActivity;
import net.sinoace.ui.multilayer.MultiLayerMenuActivity;
import net.sinoace.ui.path.PathButtonActivity;
import net.sinoace.ui.photostore.Gallery;
import net.sinoace.ui.pickview.WheelDemo;
import net.sinoace.ui.qiyi.QiyiMainActivity;
import net.sinoace.ui.qqlogin.QqLoginActivity;
import net.sinoace.ui.qqtab.QqtabActivity;
import net.sinoace.ui.radiogroup.RelativeRadioGroupActivity;
import net.sinoace.ui.ucweb.UcwebDemoActivity;
import net.sinoace.ui.viewpager.ViewPagerActivity;
import net.sinoace.ui.waterfall.WaterfallMainActivity;

public class UIMainActivity extends Activity implements OnItemClickListener,
		OnItemSelectedListener {

	private ListView mylist;
	private ArrayAdapter<String> arrayAdapter;
	private String[] contentString = new String[] { "Activity切换动画",
			" ViewPager多页面滑动切换以及动画效果", "仿QQtab切换动画", "仿Path照片分享软件的Button动画效果",
			"图片浏览器PhotoStore", " 自定义PopupWindow动画效果", "多层菜单的实例",
			"3D launcher效果", "瀑布流加载图片效果", "仿三星9100锁屏界面", "自定义RadioGroup效果",
			"仿苹果的pickview", "仿ucweb的菜单", "放大滚动效果", "阅读器页面翻页效果", "书架效果",
			"气泡式聊天模式", "启动界面效果","QQ登录界面","奇艺主界面","Listview嵌入Gridview" };

	 
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
			cls = Activity1.class;
		} else if (index == 1) {
			cls = ViewPagerActivity.class;
		} else if (index == 2) {
			cls = QqtabActivity.class;
		} else if (index == 3) {
			cls = PathButtonActivity.class;
		} else if (index == 4) {
			cls = Gallery.class;
		} else if (index == 5) {
			cls = DrawRollActivity.class;
		} else if (index == 6) {
			cls = MultiLayerMenuActivity.class;
		} else if (index == 7) {
			cls = Launcher3dMainActivity.class;
		} else if (index == 8) {
			cls = WaterfallMainActivity.class;
		} else if (index == 9) {
			cls = BitmapLockScrActivity.class;
		} else if (index == 10) {
			cls = RelativeRadioGroupActivity.class;
		} else if (index == 11) {
			cls = WheelDemo.class;
		} else if (index == 12) {
			cls = UcwebDemoActivity.class;
		} else if (index == 13) {
			cls = CoverFlowActivity.class;
		} else if (index == 14) {
			cls = BookpageActivity.class;
		} else if (index == 15) {
			cls = BookshelfReaderActivity.class;
		} else if (index == 16) {
			cls = ChatActivity.class;
		} else if (index == 17) {
			cls = LoadingViewMainActivity.class;
		} else if (index == 18){
			cls = QqLoginActivity.class;
		} else if (index == 19 ){
			cls = QiyiMainActivity.class;
		} else if (index == 20){
			cls = ListViewActivity.class;
		}
		if (cls != null) {
			Intent intent = new Intent();
			intent.setClass(this, cls);
			startActivity(intent);
		}
	}

}
