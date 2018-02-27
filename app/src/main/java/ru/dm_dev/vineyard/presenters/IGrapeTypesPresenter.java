package ru.dm_dev.vineyard.presenters;

import ru.dm_dev.vineyard.common.BaseFragmentPresenter;
import ru.dm_dev.vineyard.views.IGrapeTypesFragmentView;

public interface IGrapeTypesPresenter extends BaseFragmentPresenter<IGrapeTypesFragmentView> {
    void onResume();
    void onPause();
    void setSearchQuery(String query);
    void Refresh();
}
