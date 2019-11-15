package com.projeto.henrique.urbanexplorer;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.projeto.henrique.urbanexplorer.MainActivity.user;


public class ComentarioHotspot extends AppCompatActivity {
    private ListView listView;
    private ComentarioAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_hotspot);
        listView = findViewById(R.id.listview3);
        Intent intent = getIntent();
        Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        try{
            arrayAdapter = new ComentarioAdapter(this, hotspot.getListaComentario(), hotspot.getListaComentarioFoto());
            listView.setAdapter(arrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        TextView textView = (TextView)findViewById(R.id.veja);
        textView.setText("Veja comentários sobre "+hotspot.getNome());
    }
    public void postarComentario(View view){
        EditText editText = findViewById(R.id.comentario);
        String texto = editText.getText().toString();
        Comentario comentario = new Comentario(user.getDisplayName(), texto, ""+user.getPhotoUrl());
        Intent intent = getIntent();
        final Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        try {
            hotspot.adicionarComentario(comentario);
            Toast.makeText(this, getString(R.string.comentariopostado), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Servico.setCidadesServico(Servico.criarCidadesServico());
        Intent intent2 = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListaHotSpotsComentarios.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void avaliar(View view){
        Intent intent = getIntent();
        final Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        RatingBar ratingBar = findViewById(R.id.rating);
        float valor = ratingBar.getRating();
        Float avaliacao = (valor + hotspot.getAvalicao())/hotspot.getQuantidadeAvaliacao();
        Toast.makeText(this, "Avaliado", Toast.LENGTH_LONG).show();
        try {
            Map<String, Float> mapa = new HashMap<>();
            mapa.put("avaliacao", avaliacao);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            db.collection(hotspot.getNome() + "avaliacao").document("avaliacao").set(mapa);
            aumentarQuantidadeAvalicao();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Servico.setCidadesServico(Servico.criarCidadesServico());
        Intent intent2 = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
    }
    public void aumentarQuantidadeAvalicao(){
        Intent intent = getIntent();
        final Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        hotspot.setQuantidadeAvaliacao(hotspot.getQuantidadeAvaliacao()+1);
        try {
            Map<String, Long> mapa = new HashMap<>();
            mapa.put("quantidade", hotspot.getQuantidadeAvaliacao());
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            db.collection(hotspot.getNome() + "quantidade").document("quantidade").set(mapa);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void irSite(View view){
        Intent intent = getIntent();
        final Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        if(!hotspot.getLinkSite().equals("sem link")){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hotspot.getLinkSite()));
            startActivity(browserIntent);
        }
        else{
            Toast.makeText(this, "Site não cadastrado", Toast.LENGTH_LONG).show();
        }
    }
    public void irRestaurentesProximos(View view){
        Intent intent = getIntent();
        Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        ArrayList<Restaurante> restaurantes = (ArrayList<Restaurante>)intent.getSerializableExtra("restaurantes");
        for(int i = 0; i< restaurantes.size(); i++){
            hotspot.inserirRestaunteProximo(restaurantes.get(i));
        }
        Intent intent2 = new Intent(this, listaRestaurantes.class);
        intent2.putExtra("restaurantes",hotspot.getRestaurantesProximos());
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        finish();
    }
    public void irEvento(View view){
        Intent intent = getIntent();
        Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        if(hotspot.getEventos().size()!=0){
            Intent intent2 = new Intent(this, ListaEvento.class);
            intent2.putExtra("eventos",hotspot.getEventos());
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent2);
            finish();
        }
        else{
            Toast.makeText(this, "Não há eventos atualmente neste lugar", Toast.LENGTH_LONG).show();
        }
    }




}
