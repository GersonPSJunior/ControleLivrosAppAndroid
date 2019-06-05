package com.example.resource.controlelivros.ui.adapter.helper.callback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.resource.controlelivros.ui.adapter.ListDashboardAdapter;

public class DashboardTouchHelperCallback extends ItemTouchHelper.Callback {

    private ListDashboardAdapter adapter;

    public DashboardTouchHelperCallback(ListDashboardAdapter adapter) {

        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        int marcacaoDeDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacaoDeMover = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(marcacaoDeMover, marcacaoDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        adapter.remove(position, adapter.getLivro(position));
    }

//    @Override
//    public void onSwiped(@NonNull ListDashboardAdapter.DashboardViewHolder viewHolder, int i) {
//        adapter.remove(viewHolder.getAdapterPosition(), viewHolder.getLivro());
//    }
}
