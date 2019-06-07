package com.example.resource.controlelivros.infra.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.resource.controlelivros.domain.Livro;

import java.util.List;

@Dao
public interface IManagerBookDao {

    @Insert
    void insert(Livro livro);

    @Update
    void update(Livro livro);

    @Delete
    void delete(Livro livro);

    @Query("SELECT * FROM livro")
    LiveData<List<Livro>> listAll();
}
