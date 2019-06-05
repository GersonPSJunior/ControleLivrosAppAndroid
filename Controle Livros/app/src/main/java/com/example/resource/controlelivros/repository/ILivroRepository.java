package com.example.resource.controlelivros.repository;

import com.example.resource.controlelivros.domain.Livro;

import java.util.List;

public interface ILivroRepository {

    void insert(Livro livro);

    void update(Livro livro);

    void delete(Livro livro);

    List<Livro> listAll();
}
