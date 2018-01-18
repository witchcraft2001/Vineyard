package ru.dm_dev.vineyard.views;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ru.dm_dev.vineyard.models.Area;

public interface IAreasFragmentView {
    Context getContext();
    void showLoader();
    void hideLoader();
    void setAreasListAdapter(List<Area> list);
}
