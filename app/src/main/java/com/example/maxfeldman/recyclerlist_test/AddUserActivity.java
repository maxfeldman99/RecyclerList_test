package com.example.maxfeldman.recyclerlist_test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class AddUserActivity extends Activity {

    final int PICTURE_REQUEST_CODE = 1;

    EditText name_et;
    EditText id_et;
    EditText age_et;
    ImageView imageView;

    User user;

    Bitmap photo;

    Button photo_btn;
    Button save_user_btn;

    ArrayList<User> userArrayList;

    SingletonList listOfUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        getReferences();

        photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhotoIntent,PICTURE_REQUEST_CODE);

            }
        });


        save_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_et.getText().toString();
                String id = id_et.getText().toString();
                String age = age_et.getText().toString();


                user = new User(name,id,age,photo);

                user.setPhoto(photo);
                listOfUsers.addUser(user);



            }
        });


    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICTURE_REQUEST_CODE)
        {
          if(resultCode == RESULT_OK)
          {

              Bundle extras = data.getExtras();
              photo = (Bitmap) extras.get("data");
              imageView.setImageBitmap(photo);

          }

        }
    }

    private void getReferences(){
        name_et = findViewById(R.id.new_user_edit_text);
        id_et = findViewById(R.id.new_id_edit_text);
        age_et = findViewById(R.id.new_age_edit_text);

        photo_btn = findViewById(R.id.new_photoBtn);
        save_user_btn = findViewById(R.id.new_saveBtn);

        imageView = findViewById(R.id.new_image);

        userArrayList = new ArrayList<>();
        listOfUsers = SingletonList.getInstance(this);


    }

    @Override
    protected void onPause() {
        listOfUsers.updateFile();
        super.onPause();
    }
}
