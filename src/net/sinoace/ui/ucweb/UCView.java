package net.sinoace.ui.ucweb;

import java.util.ArrayList;
import java.util.HashMap;

import net.sinoace.anim.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

/**
 * 在oncreate里面要初始化UCView
 * ucView = new UCView(this);
		ucView.setMenuNameArray(menu_name_array);
		ucView.setMenuImageArray(menu_image_array);
		ucView.setMenuMoreNameArray(menu_name_array2);
		ucView.setMenuMoreImageArray(menu_image_array2);
		ucView.showUcView(this);
 * 使用示例如下，主要是要覆盖这两个方法
 * 	 
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
 * @author chenxiang
 *
 */
public class UCView {

	View view;
	AlertDialog dialog;
	AlertDialog.Builder builder;
	GridView menuGrid;
	private boolean isMore = true;
	private final int ITEM_0 = 0;
	private final int ITEM_1 = 1;
	private final int ITEM_2 = 2;
	private final int ITEM_3 = 3;
	private final int ITEM_MORE = 11;
	String[] menuNameArray;
	int[] menuImageArray;
	String[] menuMoreNameArray;
	int[] menuMoreImageArray;
	final Context mContext;

	public UCView(Context context) {
		this.mContext = context;
		builder = new AlertDialog.Builder(context);
	}

	public void setMenuNameArray(String[] arg0) {
		menuNameArray = arg0;
	}

	public void setMenuImageArray(int[] arg0) {
		menuImageArray = arg0;
	}

	public void setMenuMoreNameArray(String[] arg0) {
		menuMoreNameArray = arg0;
	}

	public void setMenuMoreImageArray(int[] arg0) {
		menuMoreImageArray = arg0;
	}

	public void showUcView(Context context) {
		view = View.inflate(context, R.layout.uc_gridview_menu, null);
		dialog = builder.create();
		dialog.setView(view);
		dialog.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_MENU)
					dialog.dismiss();
				return false;
			}
		});
		menuGrid = (GridView) view.findViewById(R.id.gridview);
		menuGrid.setAdapter(getMenuAdapter(mContext, menuNameArray,
				menuImageArray));
		menuGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case ITEM_0:

					break;
				case ITEM_1:

					break;
				case ITEM_2:

					break;
				case ITEM_3:

					break;
				case ITEM_MORE:
					if (isMore) {
						menuGrid.setAdapter(getMenuAdapter(mContext,
								menuMoreNameArray, menuMoreImageArray));
						isMore = false;
					} else {
						menuGrid.setAdapter(getMenuAdapter(mContext,
								menuNameArray, menuImageArray));
						isMore = true;
					}
					menuGrid.invalidate();
					menuGrid.setSelection(ITEM_MORE);
					break;
				}

			}
		});
	}

	private SimpleAdapter getMenuAdapter(Context context,
			String[] menuNameArray, int[] imageResourceArray) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < menuNameArray.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(context, data,
				R.layout.uc_item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}

	public AlertDialog getDialog() {
		return dialog;
	}

	public void setDialogBuilder(AlertDialog.Builder builder) {
		this.builder = builder;
	}

	public AlertDialog.Builder getDialogBuilder() {
		return builder;
	}

}
