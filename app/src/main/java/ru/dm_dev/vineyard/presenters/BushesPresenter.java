package ru.dm_dev.vineyard.presenters;

import android.database.MatrixCursor;

import ru.dm_dev.vineyard.views.IBushesFragmentView;

public class BushesPresenter implements IBushesPresenter {
    private IBushesFragmentView view;

    @Override
    public void init(IBushesFragmentView view) {
        this.view = view;
        MatrixCursor mc = new MatrixCursor(new String[]{"_id", "photo", "sort", "name", "event", "area"});

        mc.addRow(new Object[]{ 1, "", "vin1", "vinograd 1", "", ""});
        mc.addRow(new Object[]{ 2, "", "vin2", "vinograd 2", "", ""});
        mc.addRow(new Object[]{ 3, "", "vin3", "vinograd 3", "", ""});
        view.setBushesListAdapter(mc);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }
}
