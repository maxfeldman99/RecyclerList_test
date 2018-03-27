package com.example.maxfeldman.recyclerlist_test;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by MAX FELDMAN on 22/03/2018.
 */

public class SingletonList
{
    private static Context context;
    private static SingletonList instance = null;

    private ArrayList<User> listOfUsers;

    private SingletonList()
    {
        listOfUsers = new ArrayList<>();
        updateList();
    }

    public static SingletonList getInstance(Context Incontext)
    {
        context = Incontext;
        if(instance == null)
        {
            instance = new SingletonList();
        }
        return instance;
    }

    public void updateList()
    {
        try {
            FileInputStream inputStream = context.openFileInput("user");
            ObjectInputStream stream = new ObjectInputStream(inputStream);

            User user;
            while ((user = (User) stream.readObject()) != null) {
                listOfUsers.add(user);
            }

            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void updateFile()
    {
        try {
            FileOutputStream outputStream = context.openFileOutput("user", Context.MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);

            for (int i = 0; i < listOfUsers.size(); i++) {
                stream.writeObject(listOfUsers.get(i));
            }

            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user)
    {
        listOfUsers.add(user);
    }

    public ArrayList<User> getListOfUsers()
    {
        return listOfUsers;
    }

    public void removeUser(int pos)
    {
        listOfUsers.remove(pos);
    }

    public void removeUser(User user)
    {
        listOfUsers.remove(user);
    }

    public User getUser(int pos)
    {
        return listOfUsers.get(pos);
    }
}
