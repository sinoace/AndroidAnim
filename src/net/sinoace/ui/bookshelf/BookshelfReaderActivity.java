package net.sinoace.ui.bookshelf;

import net.sinoace.anim.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;


public class BookshelfReaderActivity extends Activity {
	/** Called when the activity is first created. */
	private ListView shelf_list;
	int[] size = new int[5];

	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bookshelf_ireader);

		shelf_list = (ListView) findViewById(R.id.shelf_list);

		ShelfAdapter adapter = new ShelfAdapter();
		shelf_list.setAdapter(adapter);
	}

	public class ShelfAdapter extends BaseAdapter {

		 
		public int getCount() {
			// TODO Auto-generated method stub
			return size.length;
		}

		 
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return size[position];
		}

		 
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		 
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View layout = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.shelf_list_item, null);

			return layout;
		}

	}

}