package net.sinoace.ui.ucweb;

import java.util.ArrayList;
import java.util.HashMap;

import net.sinoace.anim.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class UcwebDemoActivity extends Activity {
	UCView ucView;
	ListView listView;
	GridView menuGrid, toolbarGrid;
	View menuView;

	private final int TOOLBAR_ITEM_PAGEHOME = 0;
	private final int TOOLBAR_ITEM_BACK = 1;
	private final int TOOLBAR_ITEM_FORWARD = 2;
	private final int TOOLBAR_ITEM_NEW = 3;
	private final int TOOLBAR_ITEM_MENU = 4;
	int[] menu_image_array = { R.drawable.menu_search,
			R.drawable.menu_filemanager, R.drawable.menu_downmanager,
			R.drawable.menu_fullscreen, R.drawable.menu_inputurl,
			R.drawable.menu_bookmark, R.drawable.menu_bookmark_sync_import,
			R.drawable.menu_sharepage, R.drawable.menu_quit,
			R.drawable.menu_nightmode, R.drawable.menu_refresh,
			R.drawable.menu_more };
	String[] menu_name_array = { "Search", "Filemanager", "Download",
			"Fullscreen", "Inputurl", "Bookmark", "Bookmark_sync", "Share",
			"Quit", "NightModeʽ", "Refresh", "More" };
	int[] menu_image_array2 = { R.drawable.menu_auto_landscape,
			R.drawable.menu_penselectmodel, R.drawable.menu_page_attr,
			R.drawable.menu_novel_mode, R.drawable.menu_page_updown,
			R.drawable.menu_checkupdate, R.drawable.menu_checknet,
			R.drawable.menu_refreshtimer, R.drawable.menu_syssettings,
			R.drawable.menu_help, R.drawable.menu_about, R.drawable.menu_return };
	String[] menu_name_array2 = { "Landscape", "SelectModelʽ", "Pageʽ",
			"Modeʽ", "Updown", "Update", "CheckNet", "Refreshtimer", "Setting",
			"Help", "About", "Return" };

	int[] menu_toolbar_image_array = { R.drawable.controlbar_homepage,
			R.drawable.controlbar_backward_enable,
			R.drawable.controlbar_forward_enable, R.drawable.controlbar_window,
			R.drawable.controlbar_showtype_list };
	String[] menu_toolbar_name_array = { "Home", "Back", "Forward", "Window",
			"List" };

	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uc_main1);
		ucView = new UCView(this);
		ucView.setMenuNameArray(menu_name_array);
		ucView.setMenuImageArray(menu_image_array);
		ucView.setMenuMoreNameArray(menu_name_array2);
		ucView.setMenuMoreImageArray(menu_image_array2);
		ucView.showUcView(this);

		toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
		toolbarGrid.setBackgroundResource(R.drawable.channelgallery_bg);
		toolbarGrid.setNumColumns(5);
		toolbarGrid.setGravity(Gravity.CENTER);
		toolbarGrid.setVerticalSpacing(10);
		toolbarGrid.setHorizontalSpacing(10);
		toolbarGrid.setAdapter(getMenuAdapter(menu_toolbar_name_array,
				menu_toolbar_image_array));
		toolbarGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(UcwebDemoActivity.this,
						menu_toolbar_name_array[arg2], Toast.LENGTH_SHORT)
						.show();
				switch (arg2) {
				case TOOLBAR_ITEM_PAGEHOME:
					break;
				case TOOLBAR_ITEM_BACK:

					break;
				case TOOLBAR_ITEM_FORWARD:

					break;
				case TOOLBAR_ITEM_NEW:

					break;
				case TOOLBAR_ITEM_MENU:
					// menuDialog.show();
					break;
				}
			}
		});

		listView = (ListView) findViewById(R.id.ListView_catalog);
		listView.setAdapter(getMenuAdapter(menu_name_array2, menu_image_array2));

	}

	 
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("menu");
		return super.onCreateOptionsMenu(menu);
	}

	 
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (ucView.getDialog() == null) {
			ucView.setDialogBuilder(new AlertDialog.Builder(this));
			ucView.getDialogBuilder().setView(menuView).show();
		} else {
			ucView.getDialog().show();
		}
		return false;
	}

	private SimpleAdapter getMenuAdapter(String[] menuNameArray,
			int[] imageResourceArray) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < menuNameArray.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
				R.layout.uc_item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}
}