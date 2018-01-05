package ru.dm_dev.vineyard.presenters;

import android.database.MatrixCursor;

import com.activeandroid.query.Select;

import java.util.List;

import ru.dm_dev.vineyard.models.Bushe;
import ru.dm_dev.vineyard.views.IBushesFragmentView;

public class BushesPresenter implements IBushesPresenter {
    private IBushesFragmentView view;

    @Override
    public void init(IBushesFragmentView view) {
        this.view = view;
        List<Bushe> list = new Select().from(Bushe.class).orderBy("Name").execute();
        view.setBushesListAdapter(list);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }
}
