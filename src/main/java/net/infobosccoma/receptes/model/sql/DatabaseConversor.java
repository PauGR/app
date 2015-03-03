package net.infobosccoma.receptes.model.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.infobosccoma.receptes.model.Recepta;

import java.util.ArrayList;

/**
 * Created by pauguillaumes on 20/02/15.
 */
public class DatabaseConversor {

    SQLiteDatabase db;
    long index;
    private ReceptesSQLiteHelper helper;

    public DatabaseConversor(ReceptesSQLiteHelper helper) {
        this.helper = helper;


    }

    public int getIndex() {
        return (int) index;
    }

    public long save(Recepta recepta) {
        index = -1;
        // s'agafa l'objecte base de dades en mode escriptura
        db = helper.getWritableDatabase();
        // es crea un objecte de diccionari (clau,valor) per indicar els valors a afegir

        ContentValues dades = new ContentValues();

        dades.put("titol", recepta.getTitol());
        dades.put("procediment", recepta.getProcediment());
        dades.put("ingredients", recepta.getIngredients());
        dades.put("link", recepta.getLink());
        dades.put("imatge", recepta.getUrlImg());
        dades.put("tipus", recepta.getTipus());


        try {
            index = db.insertOrThrow("recepta", null, dades);
            // volem veure en el log el que passa
            // Log.e("recepta", dades.toString() + " afegit amb codi " + index);

        } catch (Exception e) {
            // volem reflectir en ellog que hi ha hagut un error
            Log.e("recepta", e.getMessage());
        }
        return index;
    }


    /**
     * Esborra el titular passat per paràmetre
     *
     * @param t el titular a esborrar
     * @return la quantitat de titulars eliminats
     */

    public boolean remove(Recepta t) {
        // obtenir l'objecte BD en mode esriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("recepta", "codi=" + t.getIndex(), null) > 0;
    }


    /**
     * Esborra tots els titulars de la taula
     *
     * @return
     */
    public boolean removeAll() {
        // obtenir l'objecte BD en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("recepta", null, null) > 0;
    }


    public boolean getEstatTaules(String tipus) {
        Cursor cursor;
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM recepta", null);

        //
        return cursor.moveToFirst() == true;

    }


    public ArrayList<Recepta> getReceptes(int tipusRecepta) {
        db = helper.getReadableDatabase();


        ArrayList<Recepta> data = new ArrayList<Recepta>();
        Cursor cursor;

        // cursor = db.rawQuery("SELECT * FROM recepta", null);  //obsolet!!

        //-----------------------------------------------------


        cursor = db.query(true, "recepta",
                new String[]{"codi", "titol", "procediment", "ingredients", "link", "imatge", "tipus"},
                "tipus = " + tipusRecepta, null, null, null, null, null);


        //---------------------------------------------------------


        while (cursor.moveToNext()) {
            Recepta temp = new Recepta(cursor.getString(1));

            temp.setProcediment(cursor.getString(2));

            temp.setIngredients(cursor.getString(3));
            temp.setUrlImg(cursor.getString(3));
            temp.setLink(cursor.getString(4));

            temp.setIndex(cursor.getInt(0));

            data.add(temp);

        }
        cursor.close();
        db.close();
        return data;

    }


}
