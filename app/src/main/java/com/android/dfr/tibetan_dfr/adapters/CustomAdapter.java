package com.android.dfr.tibetan_dfr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import com.android.dfr.tibetan_dfr.R;
import com.android.dfr.tibetan_dfr.models.TransactionObject;

/**
 * Created by MinhThanh on 11/27/17.
 */

public class CustomAdapter extends BaseAdapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;

    private List<TransactionObject> mData = null;
    private ArrayList<TransactionObject> arraylist;
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public CustomAdapter(Context context, ArrayList<TransactionObject> arraylist) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arraylist = new ArrayList<TransactionObject>();
        this.arraylist.addAll(arraylist);
    }

    public void addItem(final TransactionObject item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final TransactionObject item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public TransactionObject getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.listview_item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.idText);
                    holder.txtValue = (TextView) convertView.findViewById(R.id.textBook);
                    break;
                case TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.section_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.section_headertv);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(rowType == TYPE_ITEM){
            holder.textView.setText(mData.get(position).gettName());
            holder.txtValue.setText(""+mData.get(position).getAmount());
        }else if(rowType == TYPE_HEADER){
            holder.textView.setText(mData.get(position).gettName());
        }

        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mData.clear();
        if (charText.length() == 0) {
            mData.addAll(arraylist);
        }
        else
        {
            for (TransactionObject wp : arraylist)
            {
                if (wp.gettName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    mData.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView textView;
        public TextView txtValue;
    }
}
