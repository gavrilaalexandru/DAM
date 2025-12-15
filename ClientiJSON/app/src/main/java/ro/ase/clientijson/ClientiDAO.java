package ro.ase.clientijson;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientiDAO {

    @Insert
    void insert(Client client);

    @Insert
    void insert(List<Client> clientList);

    @Query("select * from clienti")
    List<Client> getAll();

    @Query("delete from clienti")
    void deleteAll();

    @Delete
    void delete(Client client);

    @Update
    void update(Client client);

    @Query("select * from clienti order by pret asc")
    List<Client> sortareDupaCamp();

    @Query("select * from clienti where tipAbonament =:abonament")
    List<Client> clientiCuAnumitAbonament(String abonament);
}
