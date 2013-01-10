package net.sinoace.ui.multilayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.sinoace.anim.R;

public class MultiLayerMenuActivity extends Activity {
	Activity activity;
	LinearLayout menuLayout;
	RelativeLayout bRelativeLayout;
	RelativeLayout wRelativeLayout;
	RelativeLayout fRelativeLayout;
	Button bButton;
	Button wButton;
	Button fButton;
	static int status = 0;
	
	int[] b_ids = { R.id.menu_explorer,
			R.id.menu_firefox, 
			R.id.menu_chrome,
			R.id.menu_safari,
			R.id.menu_netscape,
			R.id.menu_opera
			};
	Button[] b_btns;
	
	int[] w_ids = { R.id.menu_word,
			R.id.menu_excel, 
			R.id.menu_publisher,
			R.id.menu_outlook,
			R.id.menu_frontpage,
			R.id.menu_access
			};
	Button[] w_btns;
	
	int[] f_ids = { R.id.menu_dreamweaver,
			R.id.menu_flash, 
			R.id.menu_illustrator,
			R.id.menu_indesign,
			R.id.menu_premiere,
			R.id.menu_version
			};
	Button[] f_btns;
	
    /** Called when the activity is first created. */
     
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.multilayer_main_all);
        bRelativeLayout = (RelativeLayout) findViewById(R.id.main_a_1_rl);
        wRelativeLayout = (RelativeLayout) findViewById(R.id.main_a_2_rl);
        fRelativeLayout = (RelativeLayout) findViewById(R.id.main_a_3_rl);
    	bButton = (Button)findViewById(R.id.menu_browser);
    	wButton = (Button)findViewById(R.id.menu_windows);
    	fButton = (Button)findViewById(R.id.menu_fusion);
        menuProcess();
        setLayout();
        subbMenuProcess();
        subwMenuProcess();
        subfMenuProcess();
    }
    
    private void menuProcess() {
    	bButton.setOnClickListener(new OnClickListener() {
			 
			public void onClick(View arg0) {
				status = 1;
				setLayout();
			}        	
        });
    	wButton.setOnClickListener(new OnClickListener() {
			 
			public void onClick(View arg0) {
				status = 2;
				setLayout();
			}        	
        });
    	fButton.setOnClickListener(new OnClickListener() {
			 
			public void onClick(View arg0) {
				status = 3;
				setLayout();
			}        	
        });
    }
    
    private void setBtnEnabled(boolean b) {
		bButton.setEnabled(b);
		wButton.setEnabled(b);
		fButton.setEnabled(b);
    }
    
    private void setLayout() {
    	if (status == 0) {
			setBtnEnabled(true);
    		bRelativeLayout.setVisibility(View.GONE);
    		wRelativeLayout.setVisibility(View.GONE);
    		fRelativeLayout.setVisibility(View.GONE);
    	}
    	if (status == 1) {
			setBtnEnabled(false);
    		bRelativeLayout.setVisibility(View.VISIBLE);
    		wRelativeLayout.setVisibility(View.GONE);
    		fRelativeLayout.setVisibility(View.GONE);
    	}
    	if (status == 2) {
			setBtnEnabled(false);
    		bRelativeLayout.setVisibility(View.GONE);
    		wRelativeLayout.setVisibility(View.VISIBLE);
    		fRelativeLayout.setVisibility(View.GONE);
    	}
    	if (status == 3) {
			setBtnEnabled(false);
    		bRelativeLayout.setVisibility(View.GONE);
    		wRelativeLayout.setVisibility(View.GONE);
    		fRelativeLayout.setVisibility(View.VISIBLE);
    	}
    }
	
    private void subbMenuProcess() {
    	int len = b_ids.length;
    	b_btns = new Button[len];
    	for (int i = 0; i < len; i++) {
    		b_btns[i] = (Button)findViewById(b_ids[i]);
    		b_btns[i].setOnClickListener(new OnClickListener() {
				 
				public void onClick(View subView) {
					status = 0;
					setLayout();
					int id = subView.getId();
					for (int j = 0; j < b_ids.length; j++) {
						if (id == b_ids[j]) {
							Toast.makeText(activity, b_btns[j].getText() + " is clicked!", Toast.LENGTH_LONG).show();
						}
					}
				}        	
	        });
    	}
    }
	
    private void subwMenuProcess() {
    	int len = w_ids.length;
    	w_btns = new Button[len];
    	for (int i = 0; i < len; i++) {
    		w_btns[i] = (Button)findViewById(w_ids[i]);
    		w_btns[i].setOnClickListener(new OnClickListener() {
				 
				public void onClick(View subView) {
					status = 0;
					setLayout();
					int id = subView.getId();
					for (int j = 0; j < w_ids.length; j++) {
						if (id == w_ids[j]) {
							Toast.makeText(activity, w_btns[j].getText() + " is clicked!", Toast.LENGTH_LONG).show();
						}
					}
				}        	
	        });
    	}
    }
	
    private void subfMenuProcess() {
    	int len = f_ids.length;
    	f_btns = new Button[len];
    	for (int i = 0; i < len; i++) {
    		f_btns[i] = (Button)findViewById(f_ids[i]);
    		f_btns[i].setOnClickListener(new OnClickListener() {
				 
				public void onClick(View subView) {
					status = 0;
					setLayout();
					int id = subView.getId();
					for (int j = 0; j < f_ids.length; j++) {
						if (id == f_ids[j]) {
							Toast.makeText(activity, f_btns[j].getText() + " is clicked!", Toast.LENGTH_LONG).show();
						}
					}
				}        	
	        });
    	}
    }
}