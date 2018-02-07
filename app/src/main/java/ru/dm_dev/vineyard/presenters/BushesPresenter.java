package ru.dm_dev.vineyard.presenters;

import android.database.MatrixCursor;
import android.os.AsyncTask;

import com.activeandroid.query.Select;

import java.util.List;

import ru.dm_dev.vineyard.models.Bushe;
import ru.dm_dev.vineyard.views.IBushesFragmentView;

public class BushesPresenter implements IBushesPresenter {
    private IBushesFragmentView view;
    private String searchQuery;

    @Override
    public void init(IBushesFragmentView view) {
        this.view = view;
    }

    public class GetBushes extends AsyncTask<String, Void, List<Bushe>> {
        @Override
        protected List<Bushe> doInBackground(String... strings) {
            publishProgress();
            if (strings[0] != null && !strings[0].isEmpty()) {
                return new Select().from(Bushe.class).where("Name LIKE ?", "%" + strings[0] + "%").orderBy("Name").execute();
            }
            return new Select().from(Bushe.class).orderBy("Name").execute();
        }

        @Override
        protected void onPostExecute(List<Bushe> list) {
            view.hideLoader();
            view.setBushesListAdapter(list);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            view.showLoader();
        }
    }

    @Override
    public void onResume() {
        new GetBushes().execute("");
    }

    @Override
    public void onPause() {

    }

    @Override
    public void Refresh() {
        new GetBushes().execute(searchQuery);
    }

    @Override
    public void setSearchQuery(String query) {
        this.searchQuery = query;
        Refresh();
    }
}
