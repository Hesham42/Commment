package com.example.heshammostafa.commment.DataSet;

import com.example.heshammostafa.commment.Adapter.ChatAdapter;
import com.example.heshammostafa.commment.Model.MyDataModel;
import com.example.heshammostafa.commment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeshamMostafa on 8/6/2017.
 */

public class MyData {
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
   public static List<MyDataModel> getData()
   {
        List<MyDataModel> data;

       data = new ArrayList<MyDataModel>();

       for (int i = 0; i < darawableArra.length; i++) {

           MyDataModel dataModel = new MyDataModel();
           dataModel.setImg(darawableArra[i]);
           dataModel.setNumberOFShare(0);
           dataModel.setNumComment(0);
           dataModel.setNumberOfLike(0);
           data.add(dataModel);
       }
return data;
   }



}
