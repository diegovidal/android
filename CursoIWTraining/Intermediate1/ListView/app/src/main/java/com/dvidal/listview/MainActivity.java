package com.dvidal.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lstAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAlunos = (ListView) findViewById(R.id.lstAlunos);
        lstAlunos.setAdapter(new AlunosAdapter(this));

        lstAlunos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Maneira 1
//        TextView txtView = (TextView)  view.findViewById(R.id.txtNome);
//        Log.d("General", "Clicou no " + txtView.getText().toString());

        //Maneira 2
        Adapter aluAdapter = parent.getAdapter();
        Log.d("General", "Clicou no " + aluAdapter.getItem(position).toString());
    }
}
