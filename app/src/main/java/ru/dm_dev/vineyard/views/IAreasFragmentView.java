package ru.dm_dev.vineyard.views;

import android.content.Context;
import android.database.Cursor;

public interface IAreasFragmentView {
    Context getContext();
    void showLoader();
    void hideLoader();
    void setAreasListAdapter(Cursor cursor);
}
