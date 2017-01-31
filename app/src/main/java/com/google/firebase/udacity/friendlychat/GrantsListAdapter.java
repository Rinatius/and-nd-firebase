package com.google.firebase.udacity.friendlychat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GrantsListAdapter extends BaseAdapter {

    private ArrayList<Grant> grantsListData;
    private LayoutInflater layoutInflater;

    public GrantsListAdapter(Context aContext, ArrayList<Grant> listData) {
        this.grantsListData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return grantsListData.size();
    }

    @Override
    public Object getItem(int position) {
        return grantsListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.one_grant_in_list_layout, null);
            holder = new ViewHolder();
            holder.grantNameView = (TextView) convertView.findViewById(R.id.grantTitle);
            holder.grantTagsView = (TextView) convertView.findViewById(R.id.grantTags);
            holder.grantDeadlineView = (TextView) convertView.findViewById(R.id.grantDeadline);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.grantNameView.setText(grantsListData.get(position).getGrantName());
        holder.grantTagsView.setText(grantsListData.get(position).getTags());
        holder.grantDeadlineView.setText(grantsListData.get(position).getDeadline());
        return convertView;
    }

    public void add(Grant grant) {
        grantsListData.add(grant);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView grantNameView;
        TextView grantTagsView;
        TextView grantDeadlineView;
    }
}
