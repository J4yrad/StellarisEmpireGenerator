package com.example.myfistapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class EthicsConverter {
    @TypeConverter
    public static String EthicstoJson(String[] ethics){
        Gson gson = new Gson();
        String ethicsJson = gson.toJson(ethics);
        return  ethicsJson;
    }
    @TypeConverter
    public static String[] Jsontoethics(String ethics){
        Gson gson = new Gson();
        String[] ethicsArray = gson.fromJson(ethics,String[].class);
        return ethicsArray;
    }
}
