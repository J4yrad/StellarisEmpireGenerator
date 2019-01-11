package com.example.myfistapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

public class EmpiresAdapter extends RecyclerView.Adapter<EmpiresAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView EmpirePortrait;
        public Button deleteButton;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            EmpirePortrait= (ImageView) itemView.findViewById(R.id.EmpirePortrait);
            nameTextView = (TextView) itemView.findViewById(R.id.EmpireName);
            deleteButton = (Button) itemView.findViewById(R.id.EmpireDelete);
        }
    }
    private List<EmpireObject> EmpireList;
    public EmpiresAdapter(List<EmpireObject> empires) {
        EmpireList = empires;
    }
    @Override
    public EmpiresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.content_view_all_empires, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(EmpiresAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        EmpireObject listEmpire = EmpireList.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.nameTextView;
        ImageView EmpirePortrait = viewHolder.EmpirePortrait;
        nameTextView.setText(listEmpire.getEmpireName());
        try {
            Class res = R.mipmap.class;
            Field field = res.getField(listEmpire.getPortrait()[0].toLowerCase()+listEmpire.getPortrait()[1]);
            EmpirePortrait.setImageBitmap(BitmapFactory.decodeResource(viewHolder.itemView.getResources(), field.getInt(null)));
        }
        catch (Exception e) {
            Log.e(listEmpire.getPortrait()[0].toLowerCase()+listEmpire.getPortrait()[1]+".png", "Failure to get drawable id.", e);
        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return EmpireList.size();
    }
    public void setEmpireList(List<EmpireObject> newList){
        this.EmpireList=newList;
    }
}