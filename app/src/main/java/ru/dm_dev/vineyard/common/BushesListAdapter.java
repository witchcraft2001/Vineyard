package ru.dm_dev.vineyard.common;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BushesListAdapter extends RecyclerView.Adapter<BushesListAdapter.ViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_ACTIVITY = 1;

    private Cursor cursor;
    private Activity activity;

    public BushesListAdapter(Activity activity, Cursor cursor) {
        this.cursor = cursor;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.setRowID(cursor.getInt(0));
        holder.name.setText(cursor.getString(2));
        holder.description.setText(cursor.getString(3));
    }

    @Override
    public int getItemCount() {
        return (cursor != null) ? cursor.getCount() : 0;
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private int rowID;
        public final TextView name;
        public final TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(android.R.id.text1);
            description = (TextView)itemView.findViewById(android.R.id.text2);
        }

        public void setRowID(int rowID) {
            this.rowID = rowID;
        }
    }
}
