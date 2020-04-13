package com.aplicativo.henrique.urbanexplorer;

public class Emblema {
    private String descricao;
    private int imagem;
    private Long pontosNecessarios;


    public Long getPontosNecessarios() {
        return pontosNecessarios;
    }

    public void setPontosNecessarios(Long pontosNecessarios) {
        this.pontosNecessarios = pontosNecessarios;
    }


    public Emblema(){};

    public Emblema(String descricao, int imagem, Long p){
        this.descricao = descricao;
        this.imagem = imagem;
        this.pontosNecessarios = p;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
