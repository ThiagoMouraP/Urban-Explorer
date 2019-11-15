package com.projeto.henrique.urbanexplorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class ListaHotSpotsComentarios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hot_spots_comentarios);
        final Cidade city = (Cidade)Servico.retornarCidades().get(0) ;
        ListView listview;
        listview = (ListView) findViewById(R.id.listview);
        Collections.sort(city.getHotspots());
        listViewAdapterAvaliacao listViewAdapterAvaliacao = new listViewAdapterAvaliacao(ListaHotSpotsComentarios.this, city.getHotspots());
        listview.setAdapter(listViewAdapterAvaliacao);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaHotSpotsComentarios.this, ComentarioHotspot.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("hotspot",city.getHotspots().get(i));
                intent.putExtra("restaurantes", city.getRestaurantes());
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}
