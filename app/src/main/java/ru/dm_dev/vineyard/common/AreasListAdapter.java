package ru.dm_dev.vineyard.common;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.models.Area;

public class AreasListAdapter extends RecyclerView.Adapter<AreasListAdapter.ViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_ACTIVITY = 1;

    private List<Area> list;
    private Activity activity;

    public AreasListAdapter(Activity activity, List<Area> list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_two_rows_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setRowID(list.get(position).getId());
        holder.name.setText(list.get(position).name);
        holder.description.setText(list.get(position).description);
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public void swapList(List<Area> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private long rowID;
        public final TextView name;
        public final TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
        }

        public void setRowID(long rowID) {
            this.rowID = rowID;
        }
    }
}
