package com.example.resource.controlelivros.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.resource.controlelivros.R;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.repository.LivroRepository;
import com.example.resource.controlelivros.util.ConstantsUtil;

import java.util.Arrays;
import java.util.List;

import static android.provider.MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;

public class CadastroActivity extends AppCompatActivity {

    private Livro livro;
    private Spinner spinnerStatusCadastro;
    private EditText editLivro;
    private EditText editAutor;
    private EditText editPaginas;
    private EditText editValor;
    private EditText editDescricao;
    private ImageView imageCapaLivro;
    private String spinnerValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        spinnerStatusCadastro = findViewById(R.id.spinner_status_cadastro);
        List<String> lista = Arrays.asList(ConstantsUtil.SELECIONE_STATUS,
                ConstantsUtil.LISTA_DE_COMPRA, ConstantsUtil.COMPRADO,
                ConstantsUtil.LIDO, ConstantsUtil.EMPRESTADO);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lista);
        spinnerStatusCadastro.setAdapter(adapter);

        editAutor = findViewById(R.id.edit_autor_livro_cadastro);
        editDescricao = findViewById(R.id.edit_descricao_livro_cadastro);
        editLivro = findViewById(R.id.edit_nome_livro_cadastro);
        editPaginas = findViewById(R.id.edit_pagina_livro_cadastro);
        editValor = findViewById(R.id.edit_preco_livro_cadastro);
        imageCapaLivro = findViewById(R.id.image_capa_livro_cadastro);
        Button buttonTirarFoto = findViewById(R.id.button_tirar_foto);
        buttonTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGaleria = new Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI);
                intentGaleria.setType("image/*");
                startActivityForResult(intentGaleria, ConstantsUtil.REQUEST_CODE_CAMERA);
            }
        });
        if(getIntent().getExtras() != null && getIntent().hasExtra(ConstantsUtil.EDIT_LIVRO)){
            livro = (Livro) getIntent().getExtras().getSerializable(ConstantsUtil.EDIT_LIVRO);
            recebeValores(livro);
        }
        spinnerStatusCadastro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValor = (String) spinnerStatusCadastro.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstantsUtil.REQUEST_CODE_CAMERA){
            if(resultCode==Activity.RESULT_OK){
                assert data != null;
                Uri uri = data.getData();
                //String teste = getImagePath(uri);
                Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();
                imageCapaLivro.setImageURI(uri);
            }
        }
    }

    /*private String getImagePath(Uri contentUri){
        String[] campo = {MediaStore.Images.Media.DATA};
        return "";
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_confirmado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_resumo_compra_ic_confirmar:
                try {
                    if(getIntent().getExtras()==null) {
                        cadastrarNovoLivro();
                        livro.insert();
                        Toast.makeText(this, livro.getNome(), Toast.LENGTH_LONG).show();
                        finish();
                    } else if(getIntent().hasExtra(ConstantsUtil.EDIT_LIVRO)){
                        livro = new Livro(
                                livro.getId(),
                                editLivro.getText().toString(),
                                editDescricao.getText().toString(),
                                Integer.valueOf(editPaginas.getText().toString()),
                                Double.valueOf(editValor.getText().toString()),
                                editAutor.getText().toString(), spinnerValor
                        );
                        livro.setLivroRepository(new LivroRepository(this));
                        livro.update();
                        //.alteraLivro(id, livro);
                        finish();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(this, ConstantsUtil.DIGITE_TODOS_OS_VALORES, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
        }
        return true;
    }

    private void recebeValores(Livro livro) {
        editValor.setText(String.valueOf(livro.getPreco()));
        editPaginas.setText(String.valueOf(livro.getPaginas()));
        editLivro.setText(livro.getNome());
        editAutor.setText(livro.getAutor());
        editDescricao.setText(livro.getDescricao());
    }

    private void cadastrarNovoLivro() throws Exception {
        livro = new Livro(null,
                editLivro.getText().toString(),
                editDescricao.getText().toString(),
                Integer.valueOf(editPaginas.getText().toString()),
                Double.valueOf(editValor.getText().toString()),
                editAutor.getText().toString(), spinnerValor
        );
        livro.setLivroRepository(new LivroRepository(this));

    }
}