package com.example.maxfeldman.recyclerlist_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAX FELDMAN on 20/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    ArrayList<User> userList;
    Context context;

    myClicker myClickListener;

    public RecyclerAdapter(Context context, ArrayList<User> list)
    {
        this.context = context;
        this.userList = list;
    }

    public void setMyClickListener(myClicker listener) /// for clicklistener
    {
        this.myClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_rec_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }


    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position)
    {

        holder.imageView.setImageBitmap(userList.get(position).getPhoto());
        holder.nameTv.setText(userList.get(position).getName()+"");
    }

    @Override
    public int getItemCount()
    {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTv;
        ImageView imageView;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            nameTv = itemView.findViewById(R.id.list_item_text_view);
            imageView = itemView.findViewById(R.id.list_image_view);

            itemView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    myClickListener.onPersonClick(v,getAdapterPosition());

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v)
                {
                    myClickListener.onPersonLongClick(v,getAdapterPosition());
                    return true;
                }
            });

        }
    }

    public interface myClicker
    {
        void onPersonClick(View view, int position);
        void onPersonLongClick(View view, int position);

    }
}
