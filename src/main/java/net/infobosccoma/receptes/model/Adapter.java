package net.infobosccoma.receptes.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.infobosccoma.receptes.R;

import java.util.ArrayList;

/**
 * Created by Pau on 06/02/15.
 */
public class Adapter extends ArrayAdapter<Recepta> {

    private ArrayList<Recepta> dades;

    Adapter(Activity context, ArrayList<Recepta> dades) {
        super(context, R.layout.activity_llistat_receptes, dades);
        this.dades = dades;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;
        Vista vista;

        if (element == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            element = inflater.inflate(R.layout.activity_llistat_receptes, null);

            vista = new Vista();
            vista.titol = (TextView) element.findViewById(R.id.llistaReceptes);


            element.setTag(vista);
        } else {
            vista = (Vista) element.getTag();
        }

        vista.titol.setText(dades.get(position).getTitol());


        return element;
    }
}


class Vista {
    public TextView titol;

}


