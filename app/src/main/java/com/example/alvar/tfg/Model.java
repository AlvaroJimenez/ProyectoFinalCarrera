package com.example.alvar.tfg;

import android.graphics.drawable.Drawable;

/**
 * Created by alvar on 18/01/2016.
 */
public class Model {

    private int icon;
    private String name;

    public Model(String name)
    {

        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
