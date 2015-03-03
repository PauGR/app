package net.infobosccoma.receptes.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import net.infobosccoma.receptes.R;
import net.infobosccoma.receptes.model.sql.DatabaseConversor;
import net.infobosccoma.receptes.model.sql.ReceptesSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class CreaRecepta extends Activity {

    TextView titol;
    TextView ing;
    TextView prog;
    Button guardar;
    Spinner spinner;
    TextView t;
    boolean receptaOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_recepta);
        receptaOk = false;

        titol = (TextView) findViewById(R.id.editTitol);
        ing = (TextView) findViewById(R.id.editIngredients);
        prog = (TextView) findViewById(R.id.editProcediment);
        guardar = (Button) findViewById(R.id.guardarButton);
        spinner = (Spinner) findViewById(R.id.spinner);


        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("recepta");
        spinnerArray.add("postres");
        spinnerArray.add("beguda");


        //cal dir-li al constructor la layout d'estils (text_Sp)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.text_sp, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner
                .setAdapter(adapter);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stitol = titol.getEditableText().toString();
                String singredients = ing.getEditableText().toString();
                String sprocediment = prog.getEditableText().toString();


                if (stitol.length() != 0 && singredients.length() != 0 && singredients.length() != 0) {
                    String[] splitIngredients = singredients.split("\n");
                    String[] splitProcediment = sprocediment.split("\n");

                    singredients = "<ul>";
                    for (int i = 0; i < splitIngredients.length; i++) {
                        singredients += "<li> " + splitIngredients[i] + " </li>";

                    }
                    singredients += " </ul>";

                    sprocediment = "<ul>";
                    for (int i = 0; i < splitProcediment.length; i++) {
                        sprocediment += "<li> " + splitProcediment[i] + " </li>";

                    }
                    sprocediment += " </ul>";

                    Recepta r = new Recepta(stitol);
                    r.setIngredients(singredients);
                    r.setProcediment(sprocediment);

                    //-------------------------------


                    ReceptesSQLiteHelper helper = new ReceptesSQLiteHelper(getBaseContext(), "recepta", null, 1);


                    //-------------------------------

                    String selected = spinner.getSelectedItem().toString();
                    DatabaseConversor conversor = new DatabaseConversor(helper);

                    Toast.makeText(getBaseContext(), getText(R.string.correcte), Toast.LENGTH_LONG).show();

                    if (selected.equals("recepta")) {

                        Intent i = new Intent(getBaseContext(), LlistatReceptes.class);

                        r.setTipus(0);
                        conversor.save(r);
                        startActivity(i);

                    } else if (selected.equals("postres")) {


                        Intent i = new Intent(getBaseContext(), LlistatPostres.class);
                        r.setTipus(1);
                        conversor.save(r);
                        startActivity(i);

                    } else {
                        Intent i = new Intent(getBaseContext(), LlistatBegudes.class);
                        r.setTipus(2);
                        conversor.save(r);
                        startActivity(i);

                    }
                } else {
                    Toast.makeText(getBaseContext(), "error: hi ha camps buits", Toast.LENGTH_LONG).show();
                }


            }


        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crea_recepta, menu);


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
}
