package net.infobosccoma.receptes.model.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pauguillaumes on 20/02/15.
 */
public class ReceptesSQLiteHelper extends SQLiteOpenHelper {


    private final String SQL_CREATE_RECEPTES = "CREATE TABLE recepta (" +
            "	codi INTEGER PRIMARY KEY, " +
            "	titol TEXT, " +
            "	procediment TEXT, " +
            "   ingredients TEXT, " +
            "   link TEXT, " +
            "   imatge TEXT," +
            "   tipus INTEGER )";
    private String tipus;


    public ReceptesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(SQL_CREATE_RECEPTES);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS recepta");
        // Es crea la nova versió de la taula
        db.execSQL(SQL_CREATE_RECEPTES);


    }


}
