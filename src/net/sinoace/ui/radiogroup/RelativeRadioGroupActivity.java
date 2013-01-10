package net.sinoace.ui.radiogroup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import net.sinoace.anim.R;
import net.sinoace.ui.radiogroup.RadioGroup.OnCheckedChangeListener;

public class RelativeRadioGroupActivity extends Activity {
	
	private RadioGroup feedback;
	
    /** Called when the activity is first created. */
     
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);
        feedback = (RadioGroup) findViewById(R.id.feedback_radio_group);
        feedback.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			 
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId != -1) {
					RadioButton rb = (RadioButton) group.findViewById(checkedId);
					Toast.makeText(getApplicationContext(), (String) rb.getTag(), Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
}