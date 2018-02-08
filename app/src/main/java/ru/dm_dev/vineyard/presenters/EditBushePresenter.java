package ru.dm_dev.vineyard.presenters;

import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.models.Bushe;
import ru.dm_dev.vineyard.models.Variety;
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
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try
        {
            date = format.parse(view.getPlantedAt());
        }
        catch (Exception e)
        {
            //todo: добавить вывод ошибки о некорректности даты
            ;
        }
        if (date == null) return;
        item.PlantedAt = date.getTime();
        Variety variety = null;
        item.variety = view.getSelectedVariety();
        item.area = view.getSelectedArea();
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
            view.setSelectedArea(getAreaPosition(item.area.getId()));
            view.setSelectedVariety(getVarietyPosition(item.variety.getId()));
            long date = new Date(item.PlantedAt).getTime();
            if (date == 0) {
                date = new Date().getTime();
            }
            view.setPlantedAt(new SimpleDateFormat("dd.MM.yyyy").format(new Date(date)));
        }
    }

    private int getAreaPosition(long id)
    {
        for (int i = 0; i < areaList.size(); i++) {
            if (areaList.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    private int getVarietyPosition(long id)
    {
        for (int i = 0; i < varietyList.size(); i++) {
            if (varietyList.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    private Variety getVariety(int position)
    {
        return (varietyList.size() <= position) ? varietyList.get(position) : null;
    }

    private Area getArea(int position)
    {
        return (areaList.size() <= position) ? areaList.get(position) : null;
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
