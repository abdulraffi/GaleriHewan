package org.d3if_cool.ehcoslam.galerihewan;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raffi Zidni on 16/03/2017.
 */

public class AdapterHewan extends ArrayAdapter<Hewan> {

    ArrayList<String> idkey = new ArrayList<>();
    public AdapterHewan(Context context,DatabaseReference mDatabase) {
        super(context, 0, new ArrayList<Hewan>());
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                idkey.add(dataSnapshot.getKey());
                add(dataSnapshot.getValue(Hewan.class));
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getItem(idkey.indexOf(dataSnapshot.getKey())).setNama(dataSnapshot.getValue(Hewan.class).nama);
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addChildEventListener(childEventListener);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        }
        Hewan hewan = getItem(position);
        TextView nama = (TextView)convertView;
        nama.setText(hewan.getNama());
        return convertView;
    }

    public ArrayList<String> getIdkey() {
        return idkey;
    }
}
