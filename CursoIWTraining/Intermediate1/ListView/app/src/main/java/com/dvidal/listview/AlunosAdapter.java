package com.dvidal.listview;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by diegovidal on 09/04/16.
 */
public class AlunosAdapter extends BaseAdapter {

    private Context context;

    public String[] aNomes = {"Cirilo",
            "Jorge Del Salto",
            "Jaime Palilo",
            "Maria Joaquina"
    };

    public String[] aEmails = {"cirilo@carrossel.com",
            "jorge.salto@carrossel.com",
            "jp@carrossel.com",
            "mjoaquina@carrossel.com"
    };

    public int[] aImages = {R.drawable.ic_cirilo,
            R.drawable.ic_jorgedelsalto,
            R.drawable.ic_jaimepalilo,
            R.drawable.ic_mariajoaquina
    };

    public AlunosAdapter(Context ctx){
        super();
        this.context = ctx;

    }

    // Número de itens
    @Override
    public int getCount() {
        return aNomes.length;
    }

    // Conteúdo do objeto
    @Override
    public Object getItem(int position) {
        return aNomes[position];
    }

    // Redundante
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Equivalente ao CellForRowAtIndexPath
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if (convertView == null){
            LayoutInflater li = LayoutInflater.from(context);
            convertView = li.inflate(R.layout.adpter_alunos, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
            viewHolder.txtNome = (TextView) convertView.findViewById(R.id.txtNome);
            viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.txtEmail);

            convertView.setTag(viewHolder);
        }
        else{

            viewHolder = (ViewHolderItem) convertView.getTag();
        }



        viewHolder.imgFoto.setImageResource(aImages[position]);
        viewHolder.txtNome.setText(aNomes[position]);
        viewHolder.txtEmail.setText(aEmails[position]);

        return convertView;

    }



    static class ViewHolderItem {

        ImageView imgFoto;
        TextView txtNome;
        TextView txtEmail;
    }
}


