package net.infobosccoma.receptes.model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.infobosccoma.receptes.R;

import java.net.URL;


public class DetallRecepta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_recepta);


        TextView titol = (TextView) findViewById(R.id.titol_1);
        WebView ingredients = (WebView) findViewById(R.id.ingredients_1);
        WebView procediment = (WebView) findViewById(R.id.procediment_1);
        Button link = (Button) findViewById(R.id.link);

        TextView titoling = (TextView) findViewById(R.id.titolingredients);
        TextView titoproc = (TextView) findViewById(R.id.titolprocediment);


        titoling.setText(R.string.ingredients);
        titoproc.setText(R.string.procediment);


        Recepta r = (Recepta) getIntent().getExtras().getSerializable("recepta");

        titol.setText(r.getTitol());

        ingredients.loadData(r.getIngredients(), "text/html; charset=utf-8", "utf-8");
        procediment.loadData(r.getProcediment(), "text/html; charset=utf-8", "utf-8");


        ImageView imatge = (ImageView) findViewById(R.id.imatge);
        ProgressBar p = (ProgressBar) findViewById(R.id.progressBar);


        try {
            URL enllaç = new URL(r.getUrlImg());

            imatge.setTag(enllaç);
            new ThreadImatge(p).execute(imatge);

        } catch (Exception e) {
            e.printStackTrace();
        }


        final String internet = r.getLink();

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectat = new Intent(Intent.ACTION_VIEW, Uri.parse(internet));
                startActivity(connectat);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detall_recepta, menu);
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
