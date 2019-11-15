package com.projeto.henrique.urbanexplorer;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.io.Serializable;

public class Evento implements Serializable {
    private String nome;
    private String periodo;
    private String descricao;
    private int imagem;
    private Long pessoasConfirmadas;
    public Evento(){

    }
    public Evento(String nome, String periodo, String descricao, int imagem){
        this.nome = nome;
        this.periodo = periodo;
        this.descricao = descricao;
        this.imagem = imagem;
        pegarPessoasConfirmadas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public Long getPessoasConfirmadas() {
        return pessoasConfirmadas;
    }

    public void setPessoasConfirmadas(Long pessoasConfirmadas) {
        this.pessoasConfirmadas = pessoasConfirmadas;
    }
    public String toString(){
        return getNome()+"\n"+getDescricao()+"\n"+getPeriodo()+"\n"+"Pessoas confirmadas: "+pessoasConfirmadas;
    }
    public void pegarPessoasConfirmadas(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        DocumentReference doc = db.collection("eventos").document(this.getNome());
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Object[] os = documentSnapshot.getData().values().toArray();
                    pessoasConfirmadas = (Long)os[0];
                } catch (Exception e) {
                    pessoasConfirmadas = new Long(0);
                }

            }
        });
    }
}
