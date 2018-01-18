package ru.dm_dev.vineyard.presenters;

import android.database.MatrixCursor;

import ru.dm_dev.vineyard.views.IAreasFragmentView;

public class AreasPresenter implements IAreasPresenter {
    private IAreasFragmentView view;

    @Override
    public void init(IAreasFragmentView view) {
        this.view = view;
        MatrixCursor mc = new MatrixCursor(new String[]{"_id", "photo", "sort", "name", "event", "area"});

        mc.addRow(new Object[]{ 1, "", "ar1", "area 1", "", ""});
        mc.addRow(new Object[]{ 2, "", "ar2", "area 2", "", ""});
        mc.addRow(new Object[]{ 3, "", "ar3", "area 3", "", ""});
        view.setAreasListAdapter(mc);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }
}
