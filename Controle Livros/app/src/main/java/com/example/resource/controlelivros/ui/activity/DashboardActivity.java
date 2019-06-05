package com.example.resource.controlelivros.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.resource.controlelivros.R;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.repository.LivroRepository;
import com.example.resource.controlelivros.ui.adapter.ListDashboardAdapter;
import com.example.resource.controlelivros.ui.adapter.helper.callback.DashboardTouchHelperCallback;
import com.example.resource.controlelivros.ui.adapter.listener.OnItemClickListener;
import com.example.resource.controlelivros.util.ConstantsUtil;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private ListDashboardAdapter adapter;
    private List<Livro> lista;
    private RecyclerView recyclerviewDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button button = findViewById(R.id.button_dashboard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        setTitle("Gerenciador de Livros");
        //registerForContextMenu(recyclerviewDashboard);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraRecyclerview();
    }

    /*@Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(GerenciaLivro gerenciaLivro) {
                        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                        Intent intent = new Intent(DashboardActivity.this, CadastroActivity.class);
                        intent.putExtra("adita_livro", gerenciaLivro);
                        posicao = info.position;
                        startActivityForResult(intent, 321);
                    }
                });
                return false;
            }
        });
    }*/

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 123){
            if(resultCode == RESULT_OK){
                if(data.hasExtra("cadastro")) {
                    Livro livro = (Livro) data.getSerializableExtra("cadastro");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, livro.getStatus(),Toast.LENGTH_LONG).show();
                }
            }
        }*/
        /*if(requestCode == 321){
            if(resultCode == RESULT_OK){
                if(data.hasExtra("edita_cadastro")) {
                    GerenciaLivro gerenciaLivro = (GerenciaLivro) data.getSerializableExtra("edita_cadastro");
                    lista.set(posicao, gerenciaLivro);
                    adapter.notifyDataSetChanged();
                }
            }
        }*/
    //}

    private void configuraRecyclerview() {
        recyclerviewDashboard = findViewById(R.id.recyclerview_dashboard);
        try {
            lista = new LivroRepository(this).listAll();
            configuraAdapter(recyclerviewDashboard);
            //lista = LivroDao.getGerenciaLivroDao();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            e.printStackTrace();
        }

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
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DashboardTouchHelperCallback(adapter));
//        itemTouchHelper.attachToRecyclerView(recyclerviewDashboard);

    }

    private void configuraAdapter(RecyclerView recyclerviewDashboard) {
        adapter = new ListDashboardAdapter(this, lista);
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
