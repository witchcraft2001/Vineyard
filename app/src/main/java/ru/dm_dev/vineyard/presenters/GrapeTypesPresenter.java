package ru.dm_dev.vineyard.presenters;

import android.os.AsyncTask;

import com.activeandroid.query.Select;

import java.util.List;

import ru.dm_dev.vineyard.models.GrapeType;
import ru.dm_dev.vineyard.views.IGrapeTypesFragmentView;

public class GrapeTypesPresenter implements IGrapeTypesPresenter {

    private IGrapeTypesFragmentView view;
    private String searchQuery;

    @Override
    public void init(IGrapeTypesFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume() {
        new GetGrapeTypes().execute(searchQuery);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void setSearchQuery(String query) {
        this.searchQuery = query;
        Refresh();
    }

    @Override
    public void Refresh() {
        new GetGrapeTypes().execute(searchQuery);
    }

    public class GetGrapeTypes extends AsyncTask<String, Void, List<GrapeType>> {
        @Override
        protected List<GrapeType> doInBackground(String... strings) {
            publishProgress();
            if (strings[0] != null && !strings[0].isEmpty()) {
                return new Select().from(GrapeType.class).where("Name LIKE ?", "%" + strings[0] + "%").orderBy("Name").execute();
            }
            return new Select().from(GrapeType.class).orderBy("Name").execute();
        }

        @Override
        protected void onPostExecute(List<GrapeType> list) {
            view.hideLoader();
            view.setListAdapter(list);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            view.showLoader();
        }
    }
}
