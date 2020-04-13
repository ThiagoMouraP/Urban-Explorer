package com.aplicativo.henrique.urbanexplorer;


import android.location.Location;

import java.math.BigDecimal;

public class Restaurante extends Lugar {

    public Restaurante(){

    }
    public Restaurante(int i, String n, double d, double la, double lo){
        super(i, n, d, la, lo);
    }
    @Override
    public float getAvalicao() {
        return 0;
    }

    public  double determinarDistancia(double x, double y){
        Location a = new Location("a");
        Location b = new Location("b");
        a.setLatitude(x);
        a.setLongitude(y);
        b.setLatitude(this.getLatidute());
        b.setLongitude(this.getLongitude());
        return Double.parseDouble(""+new BigDecimal(a.distanceTo(b)/1000).setScale(2, BigDecimal.ROUND_UP));
    }
}
