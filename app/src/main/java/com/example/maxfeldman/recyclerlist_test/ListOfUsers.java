package com.example.maxfeldman.recyclerlist_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by MAX FELDMAN on 20/03/2018.
 */

public class ListOfUsers extends Activity {

    ArrayList<User> list;
    RecyclerAdapter myAdapter;

    RecyclerView recyclerView;
    GridLayoutManager mLayoutManager;

    ActionMode.Callback mActionModeCallback;

    SingletonList listOfUsers;

    int UserPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);


        list = new ArrayList<>();
        recyclerView = findViewById(R.id.my_recycler_view);

        listOfUsers = SingletonList.getInstance(this);

        mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter(this,listOfUsers.getListOfUsers());

        recyclerView.setAdapter(adapter);




        adapter.setMyClickListener(new RecyclerAdapter.myClicker() {
            @Override
            public void onPersonClick(View view, int position)
            {
                User user = listOfUsers.getUser(position);
                Intent intent = new Intent(ListOfUsers.this,show_user_activity.class);
                intent.putExtra("user",user);

                startActivity(intent);
            }

            @Override
            public void onPersonLongClick(View view, int position)
            {
                view.startActionMode(mActionModeCallback);
                UserPos = position;
            }
        });



        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                if(direction == ItemTouchHelper.LEFT)
                {
                    int pos = viewHolder.getAdapterPosition();
                    listOfUsers.removeUser(pos);
                    adapter.notifyItemRemoved(pos);
                }
            }
        };

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);


    }


    @Override
    protected void onPause() {

        super.onPause();
    }



}
