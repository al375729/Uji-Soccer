package com.example.ujisoccer;

import android.content.Context;

import androidx.room.Room;


public class Model {

 private Context context;

 AppDatabase db = Room.databaseBuilder(context,
         AppDatabase.class, "ligas").build();
 UserDao dao = db.getDao();



}
