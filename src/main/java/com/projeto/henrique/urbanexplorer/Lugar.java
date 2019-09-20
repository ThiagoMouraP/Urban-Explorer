package com.projeto.henrique.urbanexplorer;

import java.io.Serializable;

public abstract class Lugar implements Comparable, Serializable {
    private int imagem;
    private String nome;
    private double latidute;
    private double longitude;
    private double distancia;
    public Lugar(){}

    public Lugar(String nome){
        this.nome = nome;
    }

    public Lugar(int i, String n, double d, double la, double lo){
        imagem = i;
        nome = n;
        distancia = d;
        latidute = la;
        longitude = lo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getLatidute() {
        return latidute;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(Object o) {
        Lugar h = (Lugar) o;
        if (h.distancia == this.distancia) {
            return 0;
        } else if (h.distancia > this.distancia) {
            return -1;
        } else {
            return 1;
        }
    }
    public abstract float getAvalicao();

}
