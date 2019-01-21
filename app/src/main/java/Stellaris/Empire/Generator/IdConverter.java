package Stellaris.Empire.Generator;

import android.arch.persistence.room.TypeConverter;

import java.util.UUID;

public class IdConverter {
    @TypeConverter
    public String IdtoString(UUID id){
       String idString = id.toString();
       return idString;
    }
    @TypeConverter
    public UUID StringtoUUID(String idString){
        return UUID.fromString(idString);
    }
}
