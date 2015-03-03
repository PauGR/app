package net.infobosccoma.receptes.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import net.infobosccoma.receptes.R;
import net.infobosccoma.receptes.model.sql.DatabaseConversor;
import net.infobosccoma.receptes.model.sql.ReceptesSQLiteHelper;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ReceptesSQLiteHelper helper;
    private DatabaseConversor conversor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear i emplenar base de dades si no existeix


        helper = new ReceptesSQLiteHelper(this, "recepta", null, 1);
        conversor = new DatabaseConversor(helper);

        if (!conversor.getEstatTaules("recepta")) {


            ArrayList<Recepta> receptesDeSerie = creaReceptes();

            for (int i = 0; i < receptesDeSerie.size(); i++) {
                conversor.save(receptesDeSerie.get(i));

            }

        }

        //---------------------------------------------

        TextView llicencia = (TextView) findViewById(R.id.textViewllicencia);
        llicencia.setText(R.string.llicencia);

        ImageButton plat = (ImageButton) findViewById(R.id.platBTN);
        plat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getBaseContext(), LlistatReceptes.class);

                startActivity(intent);

            }
        });


        ImageButton beguda = (ImageButton) findViewById(R.id.begudaBTN);

        beguda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getBaseContext(), LlistatBegudes.class);

                startActivity(intent);

            }
        });


        ImageButton postres = (ImageButton) findViewById(R.id.postresButton);
        ImageButton afegir = (ImageButton) findViewById(R.id.afegirButton);


        postres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LlistatPostres.class);

                startActivity(intent);
            }
        });

        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreaRecepta.class);

                startActivity(intent);
            }
        });
    }

    //crear receptes per la llista
    public ArrayList<Recepta> creaReceptes() {


        Recepta recepta1 = new Recepta(getString(R.string.titol_1));
        recepta1.setIngredients(getString(R.string.ing_1));
        recepta1.setProcediment(getString(R.string.proc_1));
        recepta1.setLink(getString(R.string.link_1));
        recepta1.setUrlImg(getString(R.string.img_1));

        recepta1.setTipus(0);

        Recepta recepta2 = new Recepta(getString(R.string.titol_2));
        recepta2.setIngredients(getString(R.string.ing_2));
        recepta2.setProcediment(getString(R.string.proc_2));
        recepta2.setLink(getString(R.string.link_2));
        recepta2.setUrlImg(getString(R.string.img_2));
        recepta2.setTipus(0);

        Recepta recepta3 = new Recepta(getString(R.string.titol_3));
        recepta3.setIngredients(getString(R.string.ing_3));
        recepta3.setProcediment(getString(R.string.proc_3));
        recepta3.setLink(getString(R.string.link_3));
        recepta3.setUrlImg(getString(R.string.img_3));

        recepta3.setTipus(0);

        ArrayList<Recepta> conjuntrecepta = new ArrayList<Recepta>();

        conjuntrecepta.add(recepta1);
        conjuntrecepta.add(recepta2);
        conjuntrecepta.add(recepta3);

        Recepta recepta5 = new Recepta(getString(R.string.titol_4));
        recepta5.setIngredients(getString(R.string.ing_4));
        recepta5.setProcediment(getString(R.string.proc_4));
        recepta5.setLink(getString(R.string.link_4));
        recepta5.setUrlImg(getString(R.string.img_4));
        recepta5.setTipus(1);

        Recepta recepta6 = new Recepta("Crumble de maduixes");
        Recepta recepta7 = new Recepta("Lemon Pie");
        Recepta recepta8 = new Recepta("Galetes de coco i avellanes");
        Recepta recepta9 = new Recepta("Brownie de poma");
        Recepta recepta10 = new Recepta("Porridge de civada");
        Recepta recepta11 = new Recepta("Pastís ràpid de taronja");
        Recepta recepta12 = new Recepta("Peres farcides de nous");

        recepta6.setTipus(1);
        recepta7.setTipus(1);
        recepta8.setTipus(1);
        recepta9.setTipus(1);
        recepta10.setTipus(1);
        recepta11.setTipus(1);
        recepta12.setTipus(1);

        conjuntrecepta.add(recepta5);
        conjuntrecepta.add(recepta6);
        conjuntrecepta.add(recepta7);
        conjuntrecepta.add(recepta8);
        conjuntrecepta.add(recepta9);
        conjuntrecepta.add(recepta10);
        conjuntrecepta.add(recepta11);
        conjuntrecepta.add(recepta12);

        Recepta recepta13 = new Recepta(getString(R.string.titol_5));
        recepta13.setIngredients(getString(R.string.ing_5));
        recepta13.setProcediment(getString(R.string.proc_5));
        recepta13.setLink(getString(R.string.link_5));
        recepta13.setUrlImg(getString(R.string.img_5));

        Recepta recepta14 = new Recepta(getString(R.string.titol_6));
        recepta14.setIngredients(getString(R.string.ing_6));
        recepta14.setProcediment(getString(R.string.proc_6));
        recepta14.setLink(getString(R.string.link_6));
        recepta14.setUrlImg(getString(R.string.img_6));


        Recepta recepta15 = new Recepta(getString(R.string.titol_7));
        recepta15.setIngredients(getString(R.string.ing_7));
        recepta15.setProcediment(getString(R.string.proc_7));
        recepta15.setLink(getString(R.string.link_7));
        recepta15.setUrlImg(getString(R.string.img_7));

        Recepta recepta16 = new Recepta(getString(R.string.titol_8));
        recepta16.setIngredients(getString(R.string.ing_8));
        recepta16.setProcediment(getString(R.string.proc_8));
        recepta16.setLink(getString(R.string.link_8));
        recepta16.setUrlImg(getString(R.string.img_8));

        recepta13.setTipus(2);
        recepta14.setTipus(2);
        recepta15.setTipus(2);
        recepta16.setTipus(2);

        conjuntrecepta.add(recepta13);
        conjuntrecepta.add(recepta14);
        conjuntrecepta.add(recepta15);
        conjuntrecepta.add(recepta16);

        return conjuntrecepta;


    }
}

