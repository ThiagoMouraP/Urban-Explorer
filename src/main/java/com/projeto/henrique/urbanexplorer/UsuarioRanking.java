package com.projeto.henrique.urbanexplorer;

import java.util.regex.Pattern;

public class UsuarioRanking implements Comparable{
    private String descicao;
    private String foto;
    private int pontos;

    public UsuarioRanking(){

    }
    public UsuarioRanking(String descicao, String foto){
        this.descicao = descicao;
        this.foto = foto;
        String[] divisao = descicao.split(Pattern.quote("-"));
        String[] divisao2 = divisao[1].split(Pattern.quote(" "));
        pontos = Integer.parseInt(divisao2[1]);
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public int compareTo(Object o) {
        UsuarioRanking h = (UsuarioRanking) o;
        if (h.pontos == this.pontos) {
            return 0;
        } else if (h.pontos > this.pontos) {
            return 1;
        } else {
            return -1;
        }
    }
    public String toString(){
        return this.descicao;
    }
}
