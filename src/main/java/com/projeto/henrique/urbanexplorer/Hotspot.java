package com.projeto.henrique.urbanexplorer;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.projeto.henrique.urbanexplorer.MainActivity.user;

public class Hotspot extends Lugar{
    private ArrayList<String> listaComentarioFoto;
    private String[] listaComentario;
    private float avalicao;
    private long quantidadeAvaliacao;
    public Hotspot() {
    }

    public Hotspot(String nome) {
        super(nome);
    }

    public Hotspot(int i, String n, double d, double la, double lo) {
        super(i,n,d,la,lo);
        listaComentarioFoto = new ArrayList<>();
        receberComentarios();
        pegarAvaliacao();
    }

    public String[] getListaComentario() {
        return listaComentario;
    }

    public void setListaComentario(String[] listaComentario) {
        this.listaComentario = listaComentario;
    }

    public long getQuantidadeAvaliacao() {
        return quantidadeAvaliacao;
    }

    public void setQuantidadeAvaliacao(long quantidadeAvaliacao) {
        this.quantidadeAvaliacao = quantidadeAvaliacao;
    }



    public float getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(float avalicao) {
        this.avalicao = avalicao;
    }

    public ArrayList<String> getListaComentarioFoto() {
        return listaComentarioFoto;
    }

    public void setListaComentarioFoto(ArrayList listaComentarioFoto) {
        this.listaComentarioFoto = listaComentarioFoto;
    }


    public void adicionarComentario(Comentario comentario) {
      // para fazer
    }


    public void receberComentarios(){
       // para fazer
    }

    public void pegarAvaliacao(){
       // para fazer
    }
    public void pegarQuantidadeAvalicao(){
        // para fazer
    }
    public String[] converterObjetoEmString( Object[] objectArr){
     return null;
     // para fazer
}
