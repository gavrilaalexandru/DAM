package ro.ase.clientijson;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Client.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class ClientDB extends RoomDatabase {
    public static final String DB_NAME = "clienti.db";
    private static ClientDB instanta;

    public static ClientDB getInstance(Context context) {
        if (instanta == null)
            instanta = Room
                    .databaseBuilder(context, ClientDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        return instanta;
    }

    public abstract ClientiDAO getBileteDAO();
}
