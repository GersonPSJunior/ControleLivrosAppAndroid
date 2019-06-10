package com.example.resource.controlelivros.data.repository.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.infra.dao.IManagerBookDao;

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
}
