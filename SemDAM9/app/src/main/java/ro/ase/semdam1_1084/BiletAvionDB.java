package ro.ase.semdam1_1084;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {BiletAvion.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class BiletAvionDB extends RoomDatabase {
    public static final String DB_NAME = "bilete.db";
    private static BiletAvionDB instanta;

    public static BiletAvionDB getInstance(Context context) {
        if (instanta == null)
            instanta = Room.databaseBuilder(context, BiletAvionDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        return  instanta;
    }

    public abstract BileteDAO getBileteDAO();
}
