package com.example.resource.controlelivros.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resource.controlelivros.R;
import com.example.resource.controlelivros.domain.Livro;
import com.example.resource.controlelivros.data.repository.LivroRepository;
import com.example.resource.controlelivros.ui.activity.CadastroActivity;
import com.example.resource.controlelivros.ui.adapter.listener.OnItemClickListener;
import com.example.resource.controlelivros.util.ConstantsUtil;
import com.example.resource.controlelivros.util.DataUtil;

import java.util.List;

public class ListDashboardAdapter extends RecyclerView.Adapter<ListDashboardAdapter.DashboardViewHolder> {

    private Context context;
    private List<Livro> lista;
    private OnItemClickListener onItemClickListener;

    public ListDashboardAdapter(Context context, List<Livro> lista) {
        this.context = context;
        this.lista = lista;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ListDashboardAdapter.DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_livro, viewGroup, false);

        return new DashboardViewHolder(viewCriada);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDashboardAdapter.DashboardViewHolder viewHolder, int i) {
        Livro livro = lista.get(i);
        viewHolder.vincula(livro);
    }

    public Livro getLivro(int position){
        return lista.get(position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{

        private final TextView textNomeLivro;
        private final TextView textDataLeitura;
        private final TextView textPrecoLivro;
        private final TextView textStatusItem;
        private final ImageView imageCapaLivroItem;
        private Livro livro;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            textNomeLivro = itemView.findViewById(R.id.text_nome_livro_dashboard);
            textDataLeitura = itemView.findViewById(R.id.text_data_leitura_dashboard);
            textPrecoLivro = itemView.findViewById(R.id.text_preco_livro_dashboard);
            textStatusItem = itemView.findViewById(R.id.text_status_item);
            imageCapaLivroItem = itemView.findViewById(R.id.image_capa_livro_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(livro);
                }
            });
            itemView.setOnCreateContextMenuListener(this);
        }

        public void vincula(Livro livro) {
            textNomeLivro.setText(livro.getNome());
            textPrecoLivro.setText(String.valueOf(livro.getPreco()));
            textDataLeitura.setText(DataUtil.periodoEmTexto());
            textStatusItem.setText(livro.getStatus());
            //imageCapaLivroItem.setImageBitmap(gerenciaLivro.getLivro().getCapa());
            this.livro = livro;
        }

        public Livro getLivro() {
            return livro;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(Menu.NONE, 1, 1, "Editar");
            edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(context, livro.getNome(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, CadastroActivity.class);
                    intent.putExtra(ConstantsUtil.EDIT_LIVRO, livro);
                    context.startActivity(intent);
                    return true;
                }
            });
        }

        /*private MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case 1:
                        Toast.makeText(context, gerenciaLivro.getLivro().getNome(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, CadastroActivity.class);
                        intent.putExtra("edita_livro", gerenciaLivro);
                        context.startActivity(intent);
                        break;
                }
                return true;
            }
        };*/

    }

    public void remove(final int adapterPosition, final Livro livro) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando Livro")
                .setMessage("Tem certeza que deseja deletar esse livros?")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //GerenciarLivroDao.remolveLivro(adapterPosition);
                        new LivroRepository(context).delete(livro);
                        notifyItemRemoved(adapterPosition);
                    }
                })
                .setNegativeButton("não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyDataSetChanged();
                    }
                })
                .show();
    }
}


        /*Toast.makeText(context, "hdgahdgshagd", Toast.LENGTH_LONG).show();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Intent intent = new Intent(context, CadastroActivity.class);
        intent.putExtra("adita_livro", gerenciaLivro);
        context.startActivity(intent);*/