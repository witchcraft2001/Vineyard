package ru.dm_dev.vineyard.presenters;

import ru.dm_dev.vineyard.common.BaseFragmentPresenter;
import ru.dm_dev.vineyard.views.IBushesFragmentView;

public interface IBushesPresenter extends BaseFragmentPresenter<IBushesFragmentView> {
    void onResume();
    void onPause();
}
