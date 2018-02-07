package ru.dm_dev.vineyard.presenters;

import ru.dm_dev.vineyard.common.BaseFragmentPresenter;
import ru.dm_dev.vineyard.views.IAreasFragmentView;
import ru.dm_dev.vineyard.views.IBushesFragmentView;

public interface IAreasPresenter extends BaseFragmentPresenter<IAreasFragmentView> {
    void onResume();
    void onPause();
    void setSearchQuery(String query);
    void Refresh();
}
