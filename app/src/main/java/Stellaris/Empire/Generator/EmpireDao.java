package Stellaris.Empire.Generator;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EmpireDao {

    @Query("SELECT * FROM empire_table")
    LiveData<List<EmpireObject>> getAll();
    @Query("SELECT * FROM empire_table WHERE id = :id")
    EmpireObject getEmpire(String id);
    @Query("SELECT * FROM empire_table")
    List<EmpireObject> getAllEmpires();
    @Insert
    void insertEmpire(EmpireObject empire);
    @Query("SELECT * FROM empire_table WHERE empire_name = :name")
    List<EmpireObject> findEmpire(String name);
    @Query("DELETE FROM empire_table WHERE id = :id")
    void deleteEmpire(String id);

}

