package net.infobosccoma.receptes.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.infobosccoma.receptes.R;
import net.infobosccoma.receptes.model.sql.DatabaseConversor;
import net.infobosccoma.receptes.model.sql.ReceptesSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by Pau on 08/02/15.
 */
public class LlistatPostres extends Activity {

    ArrayAdapter<Recepta> adapter;
    ArrayList<Recepta> conjuntrecepta;
    private ListView llista;

    private ReceptesSQLiteHelper helper;
    private DatabaseConversor conversor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistat_receptes);

        helper = new ReceptesSQLiteHelper(this, "recepta", null, 1);
        conversor = new DatabaseConversor(helper);

        conjuntrecepta = conversor.getReceptes(1);

        llista = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<Recepta>(this, android.R.layout.simple_list_item_1, conjuntrecepta);

        llista.setAdapter(adapter);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("recepta", conjuntrecepta.get(position));

                Intent i = new Intent(getBaseContext(), DetallRecepta.class);
                i.putExtras(bundle);
                startActivity(i);


            }
        });

        llista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        llista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB


                final int checkedCount = llista.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Seleccionats");
                // Calls toggleSelection method from ListViewAdapter Class


                if (checked) {
                    llista.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.blau));
                } else {
                    llista.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.fons));
                }
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.borrar:

                        SparseBooleanArray selected = llista.getCheckedItemPositions();

                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Recepta selecteditem = conjuntrecepta.get(selected.keyAt(i));

                                adapter.remove(selecteditem);
                                conjuntrecepta.remove(selecteditem);
                                conversor.remove(selecteditem);
                            }
                        }

                        Toast.makeText(getBaseContext(), "borrat", Toast.LENGTH_LONG).show();

                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB

                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_long, menu);


                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.


                for (int i = 0; i < conjuntrecepta.size(); i++) {

                    llista.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.fons));

                }
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_llistat_receptes, menu);
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


    //crear receptes per la llista
    public ArrayList<Recepta> creaReceptes() {

        return conjuntrecepta;

    }

}






