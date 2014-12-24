package com.example.louyotedouard.test;

import android.app.Application;

/**
 * Created by PC Xavier on 24/12/2014.
 */
public class GlobalClass extends Application {
    private int idMagasin;

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
}
