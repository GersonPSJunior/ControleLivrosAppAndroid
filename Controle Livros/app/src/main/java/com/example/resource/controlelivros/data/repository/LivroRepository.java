package com.example.resource.controlelivros.data.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.resource.controlelivros.infra.dao.IManagerBookDao;
import com.example.resource.controlelivros.infra.ControleLivrosDatabase;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.data.repository.asynctask.LivroAsyncTask;

import java.util.List;

public class LivroRepository implements ILivroRepository {

    private final IManagerBookDao dao;
    private LiveData<List<Livro>> listLivro;


    public LivroRepository(Context context) {
        dao = ControleLivrosDatabase.getInstance(context).dao();
        listLivro = dao.listAll();
    }

    public LiveData<List<Livro>> getListLivro() {
        return listLivro;
    }

    @Override
    public void insert(Livro livro) {
        new LivroAsyncTask.Insert(dao).execute(livro);
    }

    @Override
    public void update(Livro livro) {
        new LivroAsyncTask.Update(dao).execute(livro);
    }

    @Override
    public void delete(Livro livro) {
        new LivroAsyncTask.Delete(dao).execute(livro);
    }
}
