package com.aplicativo.henrique.urbanexplorer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.aplicativo.henrique.urbanexplorer.MainActivity.determinarDistancia;
import static com.aplicativo.henrique.urbanexplorer.MainActivity.user;


public class ComentarioHotspot extends AppCompatActivity {
    private ArrayList<Comentario> comentarios= new ArrayList<>();
    private ListView listView;
    private ComentarioAdapter arrayAdapter;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_hotspot);
        Intent intent = getIntent();
        Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        TextView textView = (TextView)findViewById(R.id.veja);
        textView.setText("Veja comentários sobre "+hotspot.getNome());
        new ComentarioHotspot.LerComentario().execute("https://urbanweb.herokuapp.com/androidlercomentario.php?id="+hotspot.getId());
    }
    public void postarComentario(View view){
        Intent intent = getIntent();
        final Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        EditText editText = findViewById(R.id.comentario);
        String texto = editText.getText().toString();
        Comentario comentario = new Comentario(user.getDisplayName(), texto, ""+user.getPhotoUrl());
        new ComentarioHotspot.PostarComentario().execute("https://urbanweb.herokuapp.com/androidrecebercomentario.php?" +
                "username="+user.getDisplayName()+"&foto="+user.getPhotoUrl()+"&mensagem="+texto+"&idhotspot="+hotspot.getId());

        /*
        Intent intent2 = new Intent(this, ComentarioHotspot.class);
        intent2.putExtra("hotspot",hotspot);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        finish();
         */
        comentarios.add(comentario);

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
        Servico.cidadesServico = new ArrayList<>();
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
        Intent intent2 = new Intent(this, listaRestaurantes.class);
        intent2.putExtra("hotspot",hotspot);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        finish();



    }
    public void irEvento(View view){
        Intent intent = getIntent();
        Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
        Intent intent2 = new Intent(this, ListaEvento.class);
        intent2.putExtra("hotspotid", hotspot.getId());
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        finish();

    }
    private class PostarComentario extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ComentarioHotspot.this);
            pd.setMessage("Carregando");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            Toast.makeText(ComentarioHotspot.this, "Comentário postado", Toast.LENGTH_LONG).show();
        }
    }
    private class LerComentario extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ComentarioHotspot.this);
            pd.setMessage("Carregando");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            try{
                final JSONObject obj = new JSONObject(result);
                final JSONArray comentario = obj.getJSONArray("comentario");
                final int n = comentario.length();
                for (int i = 0; i < n; ++i) {
                    final JSONObject opiniao = comentario.getJSONObject(i);
                    Comentario o = new Comentario();
                    o.setFoto(opiniao.getString("foto"));
                    o.setMensagem(opiniao.getString("mensagem"));
                    o.setUser(opiniao.getString("username"));
                    comentarios.add(o);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            listView = findViewById(R.id.listview3);
            Intent intent = getIntent();
            Hotspot hotspot = (Hotspot)intent.getSerializableExtra("hotspot");
            try{
                arrayAdapter = new ComentarioAdapter(ComentarioHotspot.this, comentarios);
                listView.setAdapter(arrayAdapter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
