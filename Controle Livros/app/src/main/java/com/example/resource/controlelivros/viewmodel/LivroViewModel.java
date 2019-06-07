package com.example.resource.controlelivros.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.resource.controlelivros.data.repository.LivroRepository;
import com.example.resource.controlelivros.domain.Livro;

import java.util.List;

public class LivroViewModel extends AndroidViewModel {

    private LivroRepository repository;
    private LiveData<List<Livro>> listLivro;

    public LivroViewModel(@NonNull Application application) {
        super(application);
        repository = new LivroRepository(application);
        listLivro = repository.getListLivro();
    }

    public LiveData<List<Livro>> getListLivro() {
        return listLivro;
    }
}
