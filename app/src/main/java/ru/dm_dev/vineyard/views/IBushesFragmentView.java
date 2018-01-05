package ru.dm_dev.vineyard.views;

import android.content.Context;
import android.database.Cursor;

public interface IBushesFragmentView {
    Context getContext();
    void showLoader();
    void hideLoader();
    void setBushesListAdapter(Cursor cursor);
}
