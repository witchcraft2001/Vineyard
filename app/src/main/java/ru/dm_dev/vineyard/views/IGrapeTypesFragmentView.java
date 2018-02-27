package ru.dm_dev.vineyard.views;

import android.content.Context;

import java.util.List;

import ru.dm_dev.vineyard.models.GrapeType;

public interface IGrapeTypesFragmentView {
    Context getContext();
    void showLoader();
    void hideLoader();
    void setListAdapter(List<GrapeType> list);
}
