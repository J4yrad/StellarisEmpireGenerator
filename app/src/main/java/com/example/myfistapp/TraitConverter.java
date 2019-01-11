package com.example.myfistapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class TraitConverter {
    @TypeConverter
    public static String TraitstoJson(TraitModel.TraitObject[] traits){
        Gson gson = new Gson();
        String traitJson = gson.toJson(traits);
        return  traitJson;
    }
    @TypeConverter
    public static TraitModel.TraitObject[] JsontoTraits(String traits){
        Gson gson = new Gson();
        TraitModel.TraitObject[] traitArray = gson.fromJson(traits,TraitModel.TraitObject[].class);
        return traitArray;
    }
}
