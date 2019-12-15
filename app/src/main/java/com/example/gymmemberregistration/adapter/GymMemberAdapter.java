package com.example.gymmemberregistration.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gymmemberregistration.model.GymMember;
import com.example.gymmemberregistration.util.Logger;

import java.util.List;

public class GymMemberAdapter extends BaseAdapter {
    private List<GymMember> gymMembers;

    public GymMemberAdapter(List<GymMember> gymMembers) {
        gymMembers = gymMembers;
    }

    @Override
    public int getCount() {
        return gymMembers.size();
    }

    @Override
    public Object getItem(int position) {
        if(gymMembers.get(position) != null){
            return gymMembers.get(position);
        } else {
            Logger.logError(new Throwable("getItem returned null."));
            return new GymMember("E", "","");
        }
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
