package ru.dm_dev.vineyard.presenters;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;

import java.util.List;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.models.Bushe;
import ru.dm_dev.vineyard.models.Variety;
import ru.dm_dev.vineyard.views.IEditAreaView;
import ru.dm_dev.vineyard.views.IEditBusheView;

public class EditBushePresenter implements IEditBushePresenter{

    private final String LOG_TAG = "EditBushePresenter";
    private IEditBusheView view;
    private Bushe item;
    private List<Area> areaList;
    private List<Variety> varietyList;

    public EditBushePresenter(IEditBusheView view) {
        this.view = view;
    }

    @Override
    public void init(long id) {
        Log.d(LOG_TAG, "Init: id = " + id);
        new GetAreas().execute();
        new GetVarieties().execute();
        if (id == 0) {
            view.setTitle("Новый куст");
            item = new Bushe();
        } else {
            view.setTitle("Редактирование куста");
            new GetBushe().execute(id);
        }
    }

    @Override
    public void save() {
        item.name = view.getName();
        item.description = view.getDescription();
        item.save();
        view.goBack();
    }

    public class GetBushe extends AsyncTask<Long, Void, Bushe> {
        @Override
        protected Bushe doInBackground(Long... longs) {
            return Bushe.load(Bushe.class, longs[0]);
        }

        @Override
        protected void onPostExecute(Bushe bushe) {
            item = bushe;
            view.setName(item.name);
            view.setDescription(item.description);
        }
    }

    public class GetAreas extends AsyncTask<String, Void, List<Area>> {
        @Override
        protected List<Area> doInBackground(String... strings) {
            return new Select().from(Area.class).orderBy("Name").execute();
        }

        @Override
        protected void onPostExecute(List<Area> list) {
            areaList = list;
            view.setAreasListAdapter(list);
        }
    }

    public class GetVarieties extends AsyncTask<String, Void, List<Variety>> {
        @Override
        protected List<Variety> doInBackground(String... strings) {
            return new Select().from(Variety.class).orderBy("Name").execute();
        }

        @Override
        protected void onPostExecute(List<Variety> list) {
            varietyList = list;
            view.setVarietiesListAdapter(list);
        }
    }
}
