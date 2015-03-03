package net.infobosccoma.receptes.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Pau on 05/02/15.
 */
public class Recepta implements Serializable {

    private String titol;
    private String procediment;
    private String ingredients;
    private String urlImg;
    private String link;
    private Bitmap bitmap;
    private int tipus;
    private long index;

    public Recepta(String titol) {
        this.titol = titol;

    }

    public int getTipus() {
        return tipus;
    }

    public void setTipus(int tipus) {
        this.tipus = tipus;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProcediment() {
        return procediment;
    }

    public void setProcediment(String procediment) {
        this.procediment = procediment;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }


    public String toString() {
        return this.titol;
    }
}
