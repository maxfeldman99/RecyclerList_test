package com.example.maxfeldman.recyclerlist_test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity

{

    Button newUserBtn;

    Button userListBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile_action:
                Intent intent = new Intent(MainActivity.this,show_user_activity.class);
                startActivity(intent);
            case R.id.contacts_action:
                Intent intent2 = new Intent(MainActivity.this,ListOfUsers.class);
                startActivity(intent2);
            case R.id.icon_action:
                break;

        }
        return super.onOptionsItemSelected(item);


    }


    @Override

    protected void onCreate(Bundle savedInstanceState)

    {





        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        newUserBtn = findViewById(R.id.add);

        userListBtn = findViewById(R.id.all_users);





        newUserBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override

            public void onClick(View v)

            {

                Intent intent = new Intent(MainActivity.this,AddUserActivity.class);

                startActivity(intent);

            }

        });



        userListBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override

            public void onClick(View v)

            {

                Intent intent = new Intent(MainActivity.this, ListOfUsers.class);

                startActivity(intent);



            }

        });





    }

}
