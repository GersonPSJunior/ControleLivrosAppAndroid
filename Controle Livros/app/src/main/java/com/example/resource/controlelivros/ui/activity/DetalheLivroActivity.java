package com.example.resource.controlelivros.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.resource.controlelivros.R;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.util.ConstantsUtil;

public class DetalheLivroActivity extends AppCompatActivity {

    public static final String PAGINAS = " paginas";
    private TextView textNomeLivroDetalhe;
    private TextView textAutorLivroDetalhe;
    private TextView textDescricaoLivroDetalhe;
    private TextView textPaginasLivroDetalhe;
    private TextView textStatusLivroDetalhe;
    private TextView textValorLivroDetalhe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_livro);

        textNomeLivroDetalhe = findViewById(R.id.text_nome_livro_detalhe);
        textAutorLivroDetalhe = findViewById(R.id.text_autor_livro_detalhe);
        textDescricaoLivroDetalhe = findViewById(R.id.text_descricao_livro_detalhe);
        textPaginasLivroDetalhe = findViewById(R.id.text_paginas_livro_detalhe);
        textStatusLivroDetalhe = findViewById(R.id.text_status_livro_detalhe);
        textValorLivroDetalhe = findViewById(R.id.text_valor_livro_detalhe);
        loadExtras();
    }
    private void loadExtras(){
        Bundle extras = getIntent().getExtras();
        if(getIntent().hasExtra(ConstantsUtil.LIVRO)){
            Livro livro = (Livro) extras.getSerializable(ConstantsUtil.LIVRO);
            textValorLivroDetalhe.setText(String.valueOf(livro.getPreco()));
            textStatusLivroDetalhe.setText(livro.getStatus());
            textPaginasLivroDetalhe.setText(String.valueOf(livro.getPaginas())+ PAGINAS);
            textDescricaoLivroDetalhe.setText(livro.getDescricao());
            textNomeLivroDetalhe.setText(livro.getNome());
            textAutorLivroDetalhe.setText(livro.getAutor());
        }
    }
}
