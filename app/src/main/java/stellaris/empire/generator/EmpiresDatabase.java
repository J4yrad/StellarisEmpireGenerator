package stellaris.empire.generator;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

@Database(entities = {EmpireObject.class}, version = 1)
public abstract class EmpiresDatabase extends RoomDatabase {

    public abstract EmpireDao EmpireDao();
    private static EmpiresDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
    static EmpiresDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmpiresDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                   EmpiresDatabase.class, "empires_database.db")
                                    .build();

                }
            }
        }
        return INSTANCE;
    }
}
