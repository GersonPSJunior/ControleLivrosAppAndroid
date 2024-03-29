package com.example.resource.controlelivros.repository.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.infra.dao.IManagerBookDao;

import java.util.List;

public class LivroAsyncTask {

    public static class Insert extends AsyncTask<Livro, Void, Void> {

        private IManagerBookDao dao;

        public Insert(IManagerBookDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Livro... livros) {
            for (Livro livro : livros) {
                dao.insert(livro);
            }
            Log.e("***TESTE***", livros[0].getNome());
            return null;
        }
    }

    public static class Update extends AsyncTask<Livro, Void, Void> {

        private IManagerBookDao dao;

        public Update(IManagerBookDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Livro... livros) {
            for (Livro livro : livros) {
                dao.update(livro);
            }
            Log.e("***TESTE***", "Foi");
            return null;
        }
    }

    public static class Delete extends AsyncTask<Livro, Void, Void> {

        private IManagerBookDao dao;

        public Delete(IManagerBookDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Livro... livros) {
            for (Livro livro : livros) {
                dao.delete(livro);
            }
            Log.e("***TESTE***", "Foi " + livros[0].getNome());
            return null;
        }
    }

    public static class ListAll extends AsyncTask<Void, Void, List<Livro>> {

        private IManagerBookDao dao;

        public ListAll(IManagerBookDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<Livro> doInBackground(Void... voids) {
            return dao.listAll();
        }
    }
}
