package ru.dm_dev.vineyard.presenters;

import android.os.AsyncTask;

import com.activeandroid.query.Select;
import java.util.List;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.views.IAreasFragmentView;

public class AreasPresenter implements IAreasPresenter {
    private IAreasFragmentView view;

    @Override
    public void init(IAreasFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume() {
        new GetAreas().execute("");
    }

    public class GetAreas extends AsyncTask<String, Void, List<Area>> {
        @Override
        protected List<Area> doInBackground(String... strings) {
            publishProgress();
            return new Select().from(Area.class).orderBy("Name").execute();
        }

        @Override
        protected void onPostExecute(List<Area> list) {
            view.hideLoader();
            view.setAreasListAdapter(list);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            view.showLoader();
        }
    }

    @Override
    public void onPause() {

    }
}
