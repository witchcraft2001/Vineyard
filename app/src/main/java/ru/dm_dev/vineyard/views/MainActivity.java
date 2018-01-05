package ru.dm_dev.vineyard.views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.activeandroid.query.Select;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.models.Bushe;
import ru.dm_dev.vineyard.models.GrapeType;
import ru.dm_dev.vineyard.models.Variety;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String web_link = "https://vineyard.dm-dev.ru/";
    AboutFragment aboutFragment;
    HandbookFragment handbookFragment;
    EventsFragment eventsFragment;
    BushesFragment bushesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bushesFragment = new BushesFragment();
        aboutFragment = new AboutFragment();
        handbookFragment = new HandbookFragment();
        eventsFragment = new EventsFragment();

//        GrapeType t = new GrapeType();
//        t.name = "Столовый";
//        t.save();
//        Variety v = new Variety();
//        v.name = "Памяти Негруля";
//        v.description = "";
//        v.grapeType = t;
//        v.color = "Синий";
//        v.save();
//        Variety v1 = new Variety();
//        v1.name = "Новый подарок Запорожью";
//        v1.description = "НПЗ";
//        v1.grapeType = t;
//        v1.color = "Белый";
//        v1.save();
//        Area a = new Area();
//        a.name = "Мой виноградник";
//        a.description = "";
//        a.save();
//        Bushe b = new Bushe();
//        b.name = "Памяти Негруля, куст 1";
//        b.area = a;
//        b.variety = v;
//        b.save();
//        Bushe b1 = new Bushe();
//        b1.name = "НПЗ, куст 1";
//        b1.variety = v1;
//        b1.area = a;
//        b1.save();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        setTitle(item.getTitle());

        Fragment fragment = null;

        if (id == R.id.nav_bush) {
            fragment = bushesFragment;
        } else if (id == R.id.nav_gallery) {
            fragment = aboutFragment;
        } else if (id == R.id.nav_journal) {
            fragment = eventsFragment;
        } else if (id == R.id.nav_handbook) {
            fragment = handbookFragment;
        } else if (id == R.id.nav_about) {
            fragment = aboutFragment;
        } else if (id == R.id.nav_settings) {
            fragment = aboutFragment;
        } else if (id == R.id.nav_web) {
            openWebSite();
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openWebSite() {
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(web_link));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
