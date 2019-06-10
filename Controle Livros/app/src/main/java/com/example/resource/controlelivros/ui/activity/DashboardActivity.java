package com.example.resource.controlelivros.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.example.resource.controlelivros.R;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.ui.adapter.ListDashboardAdapter;
import com.example.resource.controlelivros.ui.adapter.listener.OnItemClickListener;
import com.example.resource.controlelivros.util.ConstantsUtil;
import com.example.resource.controlelivros.viewmodel.LivroViewModel;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private ListDashboardAdapter adapter;
    private LivroViewModel livroViewModel;
    private RecyclerView recyclerviewDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle("Gerenciador de Livros");

        livroViewModel = ViewModelProviders.of(this).get(LivroViewModel.class);
        Button button = findViewById(R.id.button_dashboard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        adapter = new ListDashboardAdapter(this);
        livroViewModel.getListLivro().observe(this, new Observer<List<Livro>>() {
            @Override
            public void onChanged(@Nullable List<Livro> livros) {
                adapter.setLista(livros);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraRecyclerview();
    }

    private void configuraRecyclerview() {
        recyclerviewDashboard = findViewById(R.id.recyclerview_dashboard);
        configuraAdapter(recyclerviewDashboard);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                adapter.remove(position, adapter.getLivro(position));
            }
        }).attachToRecyclerView(recyclerviewDashboard);
    }

    private void configuraAdapter(RecyclerView recyclerviewDashboard) {
        recyclerviewDashboard.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Livro livro) {
                Intent intent = new Intent(DashboardActivity.this, DetalheLivroActivity.class);
                intent.putExtra(ConstantsUtil.LIVRO, livro);
                startActivity(intent);
            }
        });
    }
}
