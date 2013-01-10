
package net.sinoace.ui.bubblechat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import net.sinoace.anim.R;

public class ChatActivity extends Activity {
    private static final String TAG = ChatActivity.class.getSimpleName();;

    private ListView talkView;

    private Button messageButton;

    private EditText messageText;

    // private ChatMsgViewAdapter myAdapter;

    private ArrayList<ChatMsgEntity> list = new ArrayList<ChatMsgEntity>();

    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate >>>>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubblechat_main);

        talkView = (ListView) findViewById(R.id.list);
        messageButton = (Button) findViewById(R.id.MessageButton);
        messageText = (EditText) findViewById(R.id.MessageText);
        OnClickListener messageButtonListener = new OnClickListener() {

             
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Log.v(TAG, "onclick >>>>>>>>");
                String name = getName();
                String date = getDate();
                String msgText = getText();
                int RId = R.layout.bubblechat_list_say_he_item;

                ChatMsgEntity newMessage = new ChatMsgEntity(name, date, msgText, RId);
                list.add(newMessage);
                // list.add(d0);
                talkView.setAdapter(new ChatMsgViewAdapter(ChatActivity.this, list));
                messageText.setText("");
                // myAdapter.notifyDataSetChanged();
            }

        };
        messageButton.setOnClickListener(messageButtonListener);
    }

    // shuold be redefine in the future
    private String getName() {
        return getResources().getString(R.string.myDisplayName);
    }

    // shuold be redefine in the future
    private String getDate() {
        Calendar c = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(c.get(Calendar.YEAR)));
        sb.append("-");
        sb.append(String.valueOf(c.get(Calendar.MONTH)));
        sb.append("-");
        sb.append(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        return sb.toString();
    }

    // shuold be redefine in the future
    private String getText() {
        return messageText.getText().toString();
    }

    public void onDestroy() {
        Log.v(TAG, "onDestroy>>>>>>");
        // list = null;
        super.onDestroy();
    }
}
