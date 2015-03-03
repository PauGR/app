package net.infobosccoma.receptes.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pau on 06/02/15.
 */
public class ThreadImatge extends AsyncTask<ImageView, Void, Bitmap> {


    Bitmap resultat;
    ProgressBar p;
    ImageView imageView = null;
    private URL url;

    public ThreadImatge(ProgressBar p) {
        this.p = p;
    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];

        //onProgressUpdate();


        return descarregar(imageView.getTag().toString());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
        this.resultat = result;
        p.setVisibility(View.INVISIBLE);


    }

//---------------------------------------------------------------------------


    //-----------------------------------------------------------------------------


    private Bitmap descarregar(String url) {

        Bitmap bmp = null;
        try {
            URL ulrn = new URL(url);
            HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            if (null != bmp)

                return bmp;

        } catch (Exception e) {
        }
        return bmp;
    }

}
