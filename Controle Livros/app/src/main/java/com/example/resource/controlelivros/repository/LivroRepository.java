package com.example.resource.controlelivros.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.resource.controlelivros.infra.dao.IManagerBookDao;
import com.example.resource.controlelivros.infra.ControleLivrosDatabase;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.repository.asynctask.LivroAsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LivroRepository implements ILivroRepository {

    private IManagerBookDao dao;
    public LivroRepository(Context context) {
        dao = ControleLivrosDatabase.getInstance(context).dao();
    }

    @Override
    public void insert(Livro livro) {
        new LivroAsyncTask.Insert(dao).execute(livro);
        //new AsyncTaskInsert().execute(livro);
    }

    @Override
    public void update(Livro livro) {
        new LivroAsyncTask.Update(dao).execute(livro);
        //new AsyncTaskUpdate().execute(livro);
    }

    @Override
    public void delete(Livro livro) {
        //dao.delete(livro);
        new LivroAsyncTask.Delete(dao).execute(livro);
    }

    @Override
    public List<Livro> listAll() {
        try {
            //return new AsyncTaskListAll().execute().get();
            return new LivroAsyncTask.ListAll(dao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*class AsyncTaskInsert extends AsyncTask<Livro, Void, Void>{

        @Override
        protected Void doInBackground(Livro... livros) {
            dao.insert(livros[0]);
            Log.e("***TESTE***", "Foi");
            return null;
        }
    }

    class AsyncTaskUpdate extends AsyncTask<Livro, Void, Void>{

        @Override
        protected Void doInBackground(Livro... livros) {
            dao.update(livros[0]);
            Log.e("***TESTE***", "Foi");
            return null;
        }
    }

    class AsyncTaskListAll extends AsyncTask<Void, Void, List<Livro>>{

        @Override
        protected List<Livro> doInBackground(Void... voids) {
            return dao.listAll();
        }
    }*/
}
