package com.example.louyotedouard.test;

import android.app.Application;

/**
 * Created by PC Xavier on 24/12/2014.
 */
public class GlobalClass extends Application {
    private String idMagasin;

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }
}
