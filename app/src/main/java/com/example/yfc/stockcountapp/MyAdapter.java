package com.example.yfc.stockcountapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YFC on 2016/6/11.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater myInflater;
    ArrayList<OilData> list = null;

    public MyAdapter(Context ctxt, ArrayList<OilData> list) {
        myInflater = LayoutInflater.from(ctxt);
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // convertView load the data
        // ViewHolder method, no need to create View everytime , save memory
        ViewHolder viewHolder;
        View viewHolder1, viewHolder2, viewHolder3, viewHolder4, viewHolder5;
        if (convertView == null) {

            // get listitem adapter view
            convertView = myInflater.inflate(R.layout.preferential_listview_adapter, null);

            viewHolder1 = convertView
                    .findViewById(R.id.myAdapter_oilTextView);
//            viewHolder2 = convertView.findViewById(R.id.myAdapter_priceSituation_textView);
//            viewHolder4 = convertView.findViewById(R.id.myAdapter_discountTextView);
            viewHolder5 = convertView.findViewById(R.id.myAdapter_resultSituation_textView);



            // construct the listitem view
            viewHolder = new ViewHolder((TextView) viewHolder1,
                   (TextView) viewHolder5);
            // (ImageView)convertView.findViewById(
            // R.id.MyAdapter_ImageView_icon),

            // set state of adapter
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set icon
        // switch(position){
        // case MainActivity.MyListView_camera:
        // viewTag.icon.setBackgroundResource(R.drawable.i_searchsiew);
        // break;
        // case MainActivity.MyListView_album:
        // viewTag.icon.setBackgroundResource(R.drawable.ic_action3);
        // break;
        // case MainActivity.MyListView_map:
        // viewTag.icon.setBackgroundResource(R.drawable.ic_playview);
        // break;
        // }

        // set text
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.result.setText(list.get(position).getResult());

        // viewHolder.title.setText(list[position]);

        return convertView;

    }


    // ViewHolder method, no need to create View everytime , save memory
    class ViewHolder {

        private TextView title;

        private TextView result;


        public ViewHolder(TextView title, TextView result) {

            this.title = title;

            this.result=result;


        }
    }





}
