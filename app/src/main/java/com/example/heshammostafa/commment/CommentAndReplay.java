package com.example.heshammostafa.commment;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.heshammostafa.commment.Adapter.ChatAdapter;
import com.example.heshammostafa.commment.Model.MessageModel;
import com.example.heshammostafa.commment.dataSenderByInterface.CommentUpdateModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.heshammostafa.commment.R.layout.activity_comment_and_replay;

public class CommentAndReplay extends AppCompatActivity
        implements View.OnClickListener

{
    ArrayList<MessageModel> chatlist = null;
    ChatAdapter adapter = null;
    RecyclerView chat_list;
    Button receive_button, send_button;
    int posstion = 0;
//    String popUpContents[];
//    public  static PopupWindow popupWindowDogs;


//    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_comment_and_replay);

//        =====================================
//        List<String> popuplist = new ArrayList<String>();
//        popuplist.add("Delete");
//        popuplist.add("Edite");
//        // convert to simple array
//        popUpContents = new String[popuplist.size()];
//        popuplist.toArray(popUpContents);
//
//        /*
//         * initialize pop up window
//         */
//        popupWindowDogs = popupWindowDogs();
////        ==============================



        posstion = Integer.parseInt(getIntent().getStringExtra("position"));
        send_button = (Button) findViewById(R.id.send_button);
        chat_list = (RecyclerView) findViewById(R.id.chat_list);
        receive_button = (Button) findViewById(R.id.receive_button);
        send_button.setOnClickListener(this);
        receive_button.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        /*
         * When stackFromEnd is true the list fills its content
         * starting from the bottom of the view.
         */
        layoutManager.setStackFromEnd(true);
        chat_list.setLayoutManager(layoutManager);
        chatlist = new ArrayList<>();
        adapter = new ChatAdapter(this, chatlist);
        adapter.setPos(posstion);
        chat_list.setAdapter(adapter);

    }
//
//    private PopupWindow popupWindowDogs() {
//
//        // initialize a pop up window type
//        PopupWindow popupWindow = new PopupWindow(this);
//
//        // the drop down list is a list view
//        ListView listViewDogs = new ListView(this);
//
//        // set our adapter and pass our pop up window contents
//        listViewDogs.setAdapter(dogsAdapter(popUpContents));
//
//        // set the item click listener
//        listViewDogs.setOnItemClickListener(new
//                ChatAdapter.DogsDropdownOnItemClickListener());
//
//        // some other visual settings
//        popupWindow.setFocusable(true);
//        popupWindow.setWidth(250);
//        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//
//        // set the list view as pop up window content
//        popupWindow.setContentView(listViewDogs);
//        return popupWindow;
//    }
    /*
       * adapter where the list values will be set
       */
//    private ArrayAdapter<String> dogsAdapter(String dogsArray[]) {
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                dogsArray
//        ) {
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
//                // setting the ID and text for every items in the list
//
//                String text = getItem(position);
//
//                // visual settings for the list item
//                TextView listItem = new TextView(CommentAndReplay.this);
//
//                listItem.setText(text);
//                listItem.setTag(position);
//                listItem.setTextSize(22);
//                listItem.setPadding(10, 10, 10, 10);
//                listItem.setTextColor(Color.WHITE);
//
//                return listItem;
//            }
//        };
//
//        return adapter;
//    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        EditText editText = (EditText) findViewById(R.id.texttosend);
        String text = null;
        if (editText != null) {
            text = editText.getText().toString().trim();
        }

//        count will give us the position(last) where we will insert item
        int count = adapter.getItemCount();

        if (text != null && text.length() != 0) {
            MessageModel model;
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            String time = dateFormat.format(calendar.getTime());
            model = new MessageModel();
            model.setMessage(text);
            model.setSender(true);
            model.setTime(time);

            if (id == R.id.send_button) {
                model.setSender(true);
                CommentUpdateModel.getInstance().addComment(posstion);
            } else if (id == R.id.receive_button) {
                model.setSender(false);
            }

            chatlist.add(model);
            adapter.notifyDataSetChanged();
            /*
            * void scrollToPosition(int position) tells layout manager to scroll recyclerView
            * to given position
            */
            chat_list.scrollToPosition(count);
            editText.setText("");
        }
    }
    /**
     * Created by HeshamMostafa on 8/6/2017.
     */


}