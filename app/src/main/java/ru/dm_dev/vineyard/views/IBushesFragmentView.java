package ru.dm_dev.vineyard.views;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ru.dm_dev.vineyard.models.Bushe;

public interface IBushesFragmentView {
    Context getContext();
    void showLoader();
    void hideLoader();
    void setBushesListAdapter(List<Bushe> list);
}
