package ru.dm_dev.vineyard.presenters;

import android.util.Log;

import com.activeandroid.query.Select;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.views.IEditAreaView;

public class EditAreaPresenter implements IEditAreaPresenter{

    private final String LOG_TAG = "EditAreaPresenter";
    private IEditAreaView view;
    private Area item;

    public EditAreaPresenter(IEditAreaView view) {
        this.view = view;
    }

    @Override
    public void init(long id) {
        Log.d(LOG_TAG, "Init: id = " + id);
        if (id == 0) {
            view.setTitle("Новый участок");
            item = new Area();
        } else {
            view.setTitle("Редактирование участка");
            item = Area.load(Area.class, id);
            view.setName(item.name);
            view.setDescription(item.description);
        }
    }

    @Override
    public void save() {
        item.name = view.getName();
        item.description = view.getDescription();
        item.save();
        view.goBack();
    }
}
