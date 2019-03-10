package stellaris.empire.generator;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class CivicConverter {
    @TypeConverter
    public static String CivicstoJson(CivicModel.CivicObject[] civics){
        Gson gson = new Gson();
        String civicJson = gson.toJson(civics);
        return  civicJson;
    }
    @TypeConverter
    public static CivicModel.CivicObject[] JsontoCivics(String civics){
        Gson gson = new Gson();
        CivicModel.CivicObject[] civicArray = gson.fromJson(civics,CivicModel.CivicObject[].class);
        return civicArray;
    }
}
