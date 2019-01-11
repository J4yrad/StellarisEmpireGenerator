package com.example.myfistapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class PortraitConverter {
    @TypeConverter
    public static String PortraittoJson(String[] portrait){
        Gson gson = new Gson();
        String portraitJson = gson.toJson(portrait);
        return  portraitJson;
    }
    @TypeConverter
    public static String[] JsontoPortrait(String Portrait){
        Gson gson = new Gson();
        String[] traitArray = gson.fromJson(Portrait,String[].class);
        return traitArray;
    }
}
