package com.projeto.henrique.urbanexplorer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class listaRestaurantes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_restaurantes);
        Intent intent = getIntent();
        final ArrayList<Restaurante> restaurantes = (ArrayList<Restaurante>) intent.getSerializableExtra("restaurantes");
        ListView listview;
        listview = (ListView) findViewById(R.id.listview);
        Collections.sort(restaurantes);
        RestauranteAdapter restauranteAdapter = new RestauranteAdapter(this, restaurantes);
        listview.setAdapter(restauranteAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dir = "google.navigation:q="+restaurantes.get(i).getLatidute()+","+restaurantes.get(i).getLongitude()+
                        "&mode="+Servico.getNavegacao();
                Uri gmmIntentUri = Uri.parse(dir);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListaHotSpotsComentarios.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
