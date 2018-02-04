package ru.dm_dev.vineyard.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.models.Variety;
import ru.dm_dev.vineyard.presenters.EditBushePresenter;
import ru.dm_dev.vineyard.presenters.IEditBushePresenter;

public class EditBusheActivity extends AppCompatActivity implements IEditBusheView {
    private EditText editName;
    private EditText editDescription;
    private IEditBushePresenter presenter;
    private Spinner areasSpinner;
    private Spinner varietiesSpinner;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bushe);
        editName = (EditText)findViewById(R.id.text_name);
        editDescription = (EditText)findViewById(R.id.text_description);
        areasSpinner = (Spinner)findViewById(R.id.area);
        varietiesSpinner = (Spinner)findViewById(R.id.variety);
        presenter = new EditBushePresenter(this);
        Intent intent = getIntent();
        id = intent.getLongExtra("Id", 0);
        presenter.init(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        boolean found = false;
        switch (id) {
            case R.id.save:
                presenter.save();
                found = true;
                break;
        }

        if (found) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setSubtitle(title);
    }

    @Override
    public void setName(String name) {
        editName.setText(name);
    }

    @Override
    public void setDescription(String description) {
        editDescription.setText(description);
    }

    @Override
    public String getName() {
        return editName.getText().toString();
    }

    @Override
    public String getDescription() {
        return editDescription.getText().toString();
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void setAreasListAdapter(List<Area> list) {
        ArrayAdapter<Area> adapter = new ArrayAdapter<Area>(this,android.R.layout.simple_spinner_item,list);
        areasSpinner.setAdapter(adapter);
    }

    @Override
    public void setVarietiesListAdapter(List<Variety> list) {
        ArrayAdapter<Variety> adapter = new ArrayAdapter<Variety>(this,android.R.layout.simple_spinner_item,list);
        varietiesSpinner.setAdapter(adapter);
    }
}
