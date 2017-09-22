package com.example.heshammostafa.commment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.heshammostafa.commment.Adapter.MyAdapterRec;
import com.example.heshammostafa.commment.Model.MyDataModel;
import com.example.heshammostafa.commment.dataSenderByInterface.CommentUpdateModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements
        CommentUpdateModel.OnCommentAddedListener {
    private  RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView;
    private int position;
    private int repliesCount;
    List<MyDataModel> data;

    int i = 0;
    static int[] darawableArra =
            {
                    R.drawable.e,
                    R.drawable.q,
                    R.drawable.r,
                    R.drawable.t,
                    R.drawable.u,
                    R.drawable.w,
                    R.drawable.y
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommentUpdateModel.getInstance().setListener(this);
//        -------------------------------------

        data = new ArrayList<MyDataModel>();

        for (int i = 0; i < darawableArra.length; i++) {

            MyDataModel dataModel = new MyDataModel();
            dataModel.setImg(darawableArra[i]);
            dataModel.setNumberOFShare(0);
            dataModel.setNumComment(0);
            dataModel.setNumberOfLike(0);
            data.add(dataModel);
        }
///////------------------------------------------
        adapter = new MyAdapterRec(this, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    public void commentAdded(int position)
    {
        int comments =data.get(position).getNumComment();
        comments++;
        data.get(position).setNumComment(comments);
        adapter.notifyDataSetChanged();
        Log.d("Guinness","Coumment = "+comments);
    }

    @Override
    public void commentRemoved(int position, int repliesCount) {

    }

    @Override
    public void commentDelete(int position) {
        int comments =data.get(position).getNumComment();
        comments--;
        data.get(position).setNumComment(comments);
        adapter.notifyDataSetChanged();
        Log.d("Guinness","Coumment = "+comments);
    }
}
