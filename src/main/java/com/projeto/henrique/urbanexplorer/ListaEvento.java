package com.projeto.henrique.urbanexplorer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.projeto.henrique.urbanexplorer.MainActivity.user;

public class ListaEvento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        ListView listView = findViewById(R.id.listview);
        Intent intent = getIntent();
        final ArrayList<Evento> eventos = ( ArrayList<Evento>)intent.getSerializableExtra("eventos");
        EventoAdapter arrayAdapter = new EventoAdapter(this, eventos);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(eventos.get(i).getLinkFacebook().equals("sem link")){
                    Toast.makeText(ListaEvento.this, "Site não cadastrado", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(eventos.get(i).getLinkFacebook()));
                    startActivity(browserIntent);
                }
            }
        });

    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListaHotSpotsComentarios.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void marcarPresenca(Evento evento){
        Map<String, Long> mapa = new HashMap<>();
        mapa.put(evento.getNome(), evento.getPessoasConfirmadas()+1);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        db.collection("eventos").document(evento.getNome()).set(mapa);
        evento.setPessoasConfirmadas(evento.getPessoasConfirmadas()+1);
    }

}
