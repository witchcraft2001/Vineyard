package ru.dm_dev.vineyard.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.presenters.EditAreaPresenter;
import ru.dm_dev.vineyard.presenters.IEditAreaPresenter;

public class EditAreaActivity extends AppCompatActivity implements IEditAreaView{

    private EditText editName;
    private EditText editDescription;
    private IEditAreaPresenter presenter;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_area);
        editName = (EditText)findViewById(R.id.text_area_name);
        editDescription = (EditText)findViewById(R.id.text_area_description);
        presenter = new EditAreaPresenter(this);
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
}
