package com.projeto.henrique.urbanexplorer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;


import static com.projeto.henrique.urbanexplorer.MainActivity.lat;
import static com.projeto.henrique.urbanexplorer.MainActivity.longi;
import static com.projeto.henrique.urbanexplorer.MainActivity.user;


public class Principal extends AppCompatActivity {
    private ListView listview;
    int number = 1;
    private ArrayList<BeanClass> beanClassArrayList;
    private listViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        try{
            TextView tv = (TextView) findViewById(R.id.nome);
            if(user.getDisplayName()!=null){
                tv.setText(getString(R.string.apresentacao)+", "+user.getDisplayName()+".");
            }
            else{
                tv.setText(getString(R.string.apresentacao));
            }
            final ImageView foto = findViewById(R.id.foto);
            if(user.getPhotoUrl()!=null){
                Picasso.with(this).load(user.getPhotoUrl()).into(foto);
            }
            else{
                foto.setImageResource(R.drawable.unknown);
            }
            listview = (ListView) findViewById(R.id.listview);
            Collections.sort(Servico.getCidadesServico());
            listViewAdapter = new listViewAdapter(this, Servico.getCidadesServico());
            listview.setAdapter(listViewAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Principal.this, PontoCidade.class);
                    intent.putExtra("cidade", Servico.getCidadesServico().get(i));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        user = null;
        startActivity(intent);
        finish();


    }
    public void irMapa(View view){
        Uri gmmIntentUri = Uri.parse("geo:"+lat+","+longi);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            user = null;
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void irConquista(View view){
        Intent intent = new Intent(this, Conquista.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
 
    public void irComentario(View view){
     	// para fazer
    }
  public void irDir(View view){
        Intent intent = new Intent(this, Configuracao.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



}
