package com.android.dfr.tibetan_dfr.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.widget.BaseAdapter;

/**
 * Created by MinhThanh on 11/26/17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.dfr.tibetan_dfr.R;
import com.android.dfr.tibetan_dfr.SearchActivity;
import com.android.dfr.tibetan_dfr.SingleItemView;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<WorldPopulation> worldpopulationlist = null;
    private ArrayList<WorldPopulation> arraylist;

    public ListViewAdapter(Context context, ArrayList<WorldPopulation> worldpopulationlist) {
        mContext = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class ViewHolder {
        TextView textBook;
        TextView idText;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public WorldPopulation getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            holder.textBook = (TextView) view.findViewById(R.id.textBook);
            holder.idText = (TextView) view.findViewById(R.id.idText);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.textBook.setText(worldpopulationlist.get(position).getLineText());
        holder.idText.setText(worldpopulationlist.get(position).getIdText());
        // Listen for ListView Item Click
//        holder.textBook.setMaxHeight(50);

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        }
        else
        {
            for (WorldPopulation wp : arraylist)
            {
                if (wp.getLineText() != null){
                    if (wp.getLineText().toLowerCase(Locale.getDefault()).contains(charText))
                    {
                        worldpopulationlist.add(wp);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}
