package com.example.myfistapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class FlagConverter {
    @TypeConverter
    public static String FlagtoJson(String[] flag){
        Gson gson = new Gson();
        String flagJson = gson.toJson(flag);
        return  flagJson;
    }
    @TypeConverter
    public static String[] JsontoFlag(String ethics){
        Gson gson = new Gson();
        String[] flagArray = gson.fromJson(ethics,String[].class);
        return flagArray;
    }
}