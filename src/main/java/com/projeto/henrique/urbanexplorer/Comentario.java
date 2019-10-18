package com.projeto.henrique.urbanexplorer;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

public class Comentario {

    private String userName;
    private String mensagem;
    private String foto;

    public Comentario(){

    }
    public Comentario(String user, String mensagem, String foto){
        this.userName = user;
        this.mensagem = mensagem;
        this.foto = foto;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String user) {
        this.userName = user;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    public String toString(){
        if(userName == null){
            return "Anônimo: "+mensagem;
        }
        if(userName.equals("")){
            return mensagem;
        }
        return userName+": "+mensagem+ " λ"+foto;
    }

}
