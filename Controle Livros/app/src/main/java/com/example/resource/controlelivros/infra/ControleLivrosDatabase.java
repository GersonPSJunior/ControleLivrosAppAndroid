package com.example.resource.controlelivros.infra;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.resource.controlelivros.infra.dao.IManagerBookDao;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.util.ConstantsUtil;

@Database(entities = {Livro.class}, version = 2, exportSchema = false)
public abstract class ControleLivrosDatabase extends RoomDatabase {

    private static ControleLivrosDatabase instance;

    public abstract IManagerBookDao dao();

    public static synchronized ControleLivrosDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ControleLivrosDatabase.class, ConstantsUtil.DATABASE)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
