package com.example.maxfeldman.recyclerlist_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MAX FELDMAN on 22/03/2018.
 */

public class show_user_activity extends Activity {

    TextView name;
    TextView id;
    TextView age;


    ImageView imageView;

    User u;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user_info);

        name = findViewById(R.id.user_name_tv);
        age = findViewById(R.id.user_age_tv);
        id = findViewById(R.id.user_id_tv);

        imageView = findViewById(R.id.user_photo);

        u = (User) getIntent().getExtras().get("user");


        name.setText(u.getName());
        age.setText(u.getAge());
        id.setText(u.getId());

        imageView.setImageBitmap(u.getPhoto());


    }

}
