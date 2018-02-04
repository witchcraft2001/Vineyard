package ru.dm_dev.vineyard.presenters;

import ru.dm_dev.vineyard.views.IEditAreaView;

public interface IEditAreaPresenter {
    void init(long id);
    void save();
}
