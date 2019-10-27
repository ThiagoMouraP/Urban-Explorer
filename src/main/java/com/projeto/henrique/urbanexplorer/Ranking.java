package com.projeto.henrique.urbanexplorer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class Ranking extends AppCompatActivity {
    private ArrayList<UsuarioRanking> listaUsuario = new ArrayList<>();
    private ArrayList<String> lista = new ArrayList<>();
    private ArrayList<String> listaFoto = new ArrayList<>();
    private ListView listView;
    private RankingAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        listView = findViewById(R.id.listview3);
        receberRanking();
    }
    public void receberRanking(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        db.collection("ranking")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<String> os = new ArrayList<>();
                        if (task.isSuccessful()) {
                            try{
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    os.add(document.getData().toString());
                                }
                                converterObjetoEmString( os.toArray());

                            }catch (Exception e){
                                lista.add("Não há ranking");
                                listaFoto.add("");
                            }

                        }
                    }
                });
    }
    public void converterObjetoEmString( Object[] objectArr){
        String[] strArr = new String[objectArr.length];
        for(int i = 0 ; i < objectArr.length ; i ++){
            try {
                strArr[i] = objectArr[i].toString();
                String[] aux1 = strArr[i].split("=");
                String[] aux2 = aux1[1].split((Pattern.quote("}")));
                String[] aux3 = aux2[0].split((Pattern.quote("λ")));
                strArr[i] = aux3[0];
                try{
                    UsuarioRanking usuarioRanking = new UsuarioRanking(aux3[0],aux3[1]);
                    listaUsuario.add(usuarioRanking);
                    lista.add(aux3[0]+ " "+getString(R.string.pontos));
                    listaFoto.add(aux3[1]);
                }catch (Exception e){
                    listaFoto.add("");
                    lista.add("Não");
                    e.printStackTrace();
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        Collections.sort(listaUsuario);
        arrayAdapter = new RankingAdapter(this, listaUsuario);
        listView.setAdapter(arrayAdapter);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Conquista.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
