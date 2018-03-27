package com.example.maxfeldman.recyclerlist_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String id;
    private String age;


    private transient Bitmap photo;

    public User(String name, String id, String age, Bitmap photo) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.photo = photo;
    }

    public User(){};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }


    private void writeObject(java.io.ObjectOutputStream stream) throws IOException

    {

        photo.compress(Bitmap.CompressFormat.JPEG,100,stream);

        stream.defaultWriteObject();

    }



    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {

        photo = BitmapFactory.decodeStream(stream);

        stream.defaultReadObject();

    }



}


