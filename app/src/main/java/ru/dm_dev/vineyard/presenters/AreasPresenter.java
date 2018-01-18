package ru.dm_dev.vineyard.presenters;

import com.activeandroid.query.Select;
import java.util.List;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.views.IAreasFragmentView;

public class AreasPresenter implements IAreasPresenter {
    private IAreasFragmentView view;

    @Override
    public void init(IAreasFragmentView view) {
        this.view = view;
        List<Area> list = new Select().from(Area.class).orderBy("Name").execute();
        view.setAreasListAdapter(list);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }
}
