package com.example.resource.controlelivros.data.repository;

import com.example.resource.controlelivros.domain.Livro;

public interface ILivroRepository {

    void insert(Livro livro);

    void update(Livro livro);

    void delete(Livro livro);

    //LiveData<List<Livro>> listAll();
}
