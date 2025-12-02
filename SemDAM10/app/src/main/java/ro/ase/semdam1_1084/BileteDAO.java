package ro.ase.semdam1_1084;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BileteDAO {

    @Insert
    void insert(BiletAvion biletAvion);

    @Insert
    void insert(List<BiletAvion> biletAvionList);

    @Query("select * from bilete")
    List<BiletAvion> getAll();

    @Query("delete from bilete")
    void deleteAll();

    @Delete
    void delete(BiletAvion biletAvion);

    @Update
    void update(BiletAvion biletAvion);

    @Query("select * from bilete where companie =:companie")
    List<BiletAvion> getBileteByCompanie(String companie);
}
