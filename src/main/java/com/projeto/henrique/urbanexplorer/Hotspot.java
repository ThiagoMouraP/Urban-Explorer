package com.aplicativo.henrique.urbanexplorer;


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

import static com.aplicativo.henrique.urbanexplorer.MainActivity.user;

public class Hotspot extends Lugar{
    private ArrayList<String> listaComentarioFoto;
    private String[] listaComentario;
    private float avalicao;
    private long quantidadeAvaliacao;
    private String linkSite;
    private ArrayList<Restaurante> restaurantesProximos;
    public static final double PROXIMIDADERESTUARENTE = 1.3;
    private ArrayList<Evento> eventos;
    private int id;
    private int idcidade;
    public Hotspot() {
    }

    public Hotspot(String nome) {
        super(nome);
        pegarAvaliacao();
        receberComentarios();
    }

    public Hotspot(int i, String n, double d, double la, double lo) {
        super(i,n,d,la,lo);
        listaComentarioFoto = new ArrayList<>();
        receberComentarios();
        pegarAvaliacao();
        linkSite = "sem link";
        restaurantesProximos = new ArrayList<>();
        eventos = new ArrayList<>();
    }
    public Hotspot(int i, String n, double d, double la, double lo, String ls) {
        super(i,n,d,la,lo);
        listaComentarioFoto = new ArrayList<>();
        receberComentarios();
        pegarAvaliacao();
        linkSite = ls;
        restaurantesProximos = new ArrayList<>();
        eventos = new ArrayList<>();
    }
    public Hotspot(int i, String n, double d, double la, double lo, String ls, ArrayList<Evento> e) {
        super(i,n,d,la,lo);
        listaComentarioFoto = new ArrayList<>();
        receberComentarios();
        pegarAvaliacao();
        linkSite = ls;
        restaurantesProximos = new ArrayList<>();
        eventos = e;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(int idcidade) {
        this.idcidade = idcidade;
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

    public String getLinkSite() {
        return linkSite;
    }

    public void setLinkSite(String linkSite) {
        this.linkSite = linkSite;
    }

    public void adicionarComentario(Comentario comentario) {
        Map<String, String> mapa = new HashMap<>();
        mapa.put(user.getUid(), comentario.toString());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        db.collection(this.getNome()).document(user.getUid()).set(mapa);
    }


    public void receberComentarios(){
        /*
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        db.collection(this.getNome())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Object> os = new ArrayList<>();
                        if (task.isSuccessful()) {
                            try{
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    os.add(document.getData().toString());
                                }
                                listaComentario = converterObjetoEmString( os.toArray());
                            }catch (Exception e){
                                String[] a =  {"Não há comentários"};
                                listaComentario = a;
                            }

                        }
                    }
                });

         */
    }


    public void pegarAvaliacao(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        DocumentReference doc = db.collection(this.getNome() + "avaliacao").document("avaliacao");
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Object[] os = documentSnapshot.getData().values().toArray();
                    Double aux = (Double) os[0];
                    avalicao = Float.parseFloat(""+aux);
                    pegarQuantidadeAvalicao();
                } catch (Exception e) {
                    avalicao = new  Float(0);
                    quantidadeAvaliacao = new Long(1);
                }

            }
        });
    }
    public void pegarQuantidadeAvalicao(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        DocumentReference doc = db.collection(this.getNome() + "quantidade").document("quantidade");
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    Object[] os = documentSnapshot.getData().values().toArray();
                    quantidadeAvaliacao = (Long)os[0];
                } catch (Exception e) {
                    quantidadeAvaliacao = new Long(1);
                }

            }
        });
    }
    public String[] converterObjetoEmString( Object[] objectArr){
        String[] strArr = new String[objectArr.length];
        for(int i = 0 ; i < objectArr.length ; i ++){
            try {
                strArr[i] = objectArr[i].toString();
                String[] aux1 = strArr[i].split("=");
                String[] aux2 = aux1[1].split((Pattern.quote("}")));
                String[] aux3 = aux2[0].split((Pattern.quote("λ")));
                strArr[i] = aux3[0];
                try{
                    listaComentarioFoto.add(aux3[1]);
                }catch (Exception e){
                    listaComentarioFoto.add("");
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return strArr;
    }
    public void inserirRestaunteProximo(Restaurante restaurante){
        if(restaurante.determinarDistancia(this.getLatidute(), this.getLongitude())<PROXIMIDADERESTUARENTE){
            restaurantesProximos.add(restaurante);
        }
    }

    public ArrayList<Restaurante> getRestaurantesProximos() {
        return restaurantesProximos;
    }

    public void setRestaurantesProximos(ArrayList<Restaurante> restaurantesProximos) {
        this.restaurantesProximos = restaurantesProximos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }
}
