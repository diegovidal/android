package com.dvidal.temas;

import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem mi = menu.findItem(R.id.action_search);
        SearchView search = (SearchView) mi.getActionView();
        search.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showToast("Clicou no Settings");
                return true;

            case R.id.action_lighbulb:
                showToast("Clicou no Lighbulb");
                return true;

            case R.id.action_theaters:
                showToast("Clicou no Theaters");
                return true;
            default:
                // Padrão é false
                return super.onOptionsItemSelected(item);
        }

        // Padrão é false
        //return super.onOptionsItemSelected(item);

    }

    public void showToast(String toast){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("Escrevendo: ", newText);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("Enviou: ", query);
        return false;
    }
}
