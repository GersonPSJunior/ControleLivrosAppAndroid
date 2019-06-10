package com.example.resource.controlelivros.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.resource.controlelivros.data.repository.ILivroRepository;

import java.io.Serializable;

@Entity(tableName = "livro")
public class Livro implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String nome;
    private String descricao;
    private Integer paginas;
    private Double preco;
    private String status;
    private String autor;

    @Ignore
    private ILivroRepository livroRepository;

    public Livro(Long id, String nome, String descricao, Integer paginas, Double preco, String autor, String status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.paginas = paginas;
        this.preco = preco;
        this.autor = autor;
        this.status = status;
    }

    public void insert(){
        livroRepository.insert(this);
    }

    public void update(){
        livroRepository.update(this);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public Double getPreco() {
        return preco;
    }

    public String getAutor() {
        return autor;
    }

    public String getStatus() {
        return status;
    }

    public void setLivroRepository(ILivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
}
