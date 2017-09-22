package com.example.heshammostafa.commment.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heshammostafa.commment.CommentAndReplay;
import com.example.heshammostafa.commment.Model.MessageModel;
import com.example.heshammostafa.commment.R;
import com.example.heshammostafa.commment.dataSenderByInterface.CommentUpdateModel;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.heshammostafa.commment.R.drawable.y;


/**
 * Created by HeshamMostafa on 8/6/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context = null;
    ArrayList<MessageModel> chatList = null;
    int pos;

    RelativeLayout relativeLayout;
    PopupWindow popupWindow;
    View popupView;
    int mCurrentX, mCurrentY;

    public void setPos(int pos) {
        this.pos = pos;
    }


    public void delete(int position) {
        chatList.remove(position);
//        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    private final int SENT_MESSAGE = 0, RECEIVED_MESSAGE = 1;

    public ChatAdapter(Context context, ArrayList<MessageModel> chatList) {
        this.context = context;
        this.chatList = chatList;
    }


    /***
     *
     * @param position
     * @return the view type of the item at the position for the
     * purpose of recycling view
     *
     * By default it returns zero showing a single view type for the adapter.
     */
    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getSender()) {
            return SENT_MESSAGE;
        } else {
            return RECEIVED_MESSAGE;

        }
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        //Based on view type decide which type of view to supply with viewHolder
        switch (viewType) {
            case SENT_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message, parent, false);

                break;

            case RECEIVED_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message, parent, false);
                break;
        }
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, final int position) {
        MessageModel model = chatList.get(position);
        holder.texttosend.setText(model.getMessage());
        holder.date.setText(model.getTime());
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.popup, null);

                popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                Button btnClose = (Button) popupView.findViewById(R.id.btnClose);

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CommentUpdateModel.getInstance().DeleteComment(pos);
                        delete(position);

                        popupWindow.dismiss();
                    }
                });
                Rect rc = new Rect();
                view.getWindowVisibleDisplayFrame(rc);
                int[] xy = new int[2];
                view.getLocationInWindow(xy);
                rc.offset(xy[0], xy[1]);
                int x = rc.left, y = rc.top;

                mCurrentX = x;
                mCurrentY = y;
                popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, mCurrentX, mCurrentY);
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    private float mDx;
                    private float mDy;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = event.getAction();
                        if (action == MotionEvent.ACTION_DOWN) {
                            mDx = mCurrentX - event.getRawX();
                            mDy = mCurrentY - event.getRawY();
                        } else if (action == MotionEvent.ACTION_MOVE) {
                            mCurrentX = (int) (event.getRawX() + mDx);
                            mCurrentY = (int) (event.getRawY() + mDy);
                            popupWindow.update(mCurrentX, mCurrentY, -1, -1);
                        }
                        return true;
                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    /*
    * Here we have kept ID's of all the child row elements same.
    * But we can also create to different viewHolder classes
    * for different child rows.
    */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttosend;
        TextView date;
        ImageView Delete;

        public ViewHolder(View itemView) {
            super(itemView);
            texttosend = (TextView) itemView.findViewById(R.id.message);
            date = (TextView) itemView.findViewById(R.id.date);
            Delete = (ImageView) itemView.findViewById(R.id.Delete);

        }
    }

}