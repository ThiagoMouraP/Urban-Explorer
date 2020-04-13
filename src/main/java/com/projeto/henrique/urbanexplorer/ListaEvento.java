package com.aplicativo.henrique.urbanexplorer;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

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

import static com.aplicativo.henrique.urbanexplorer.MainActivity.determinarDistancia;
import static com.aplicativo.henrique.urbanexplorer.MainActivity.user;

public class ListaEvento extends AppCompatActivity {
    private EventoAdapter arrayAdapter;
    private ListView listview;
    private ArrayList<Evento> eventos = new ArrayList<>();
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        Intent intent = getIntent();
        final int hotspotid = (int) intent.getSerializableExtra("hotspotid") ;
        new ListaEvento.JsonTask().execute("https://urbanweb.herokuapp.com/androidlerevento.php?id="+hotspotid);
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
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ListaEvento.this);
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
                final JSONArray eventosJson= obj.getJSONArray("evento");
                final int n = eventosJson.length();
                for (int i = 0; i < n; ++i) {
                    final JSONObject evento = eventosJson.getJSONObject(i);
                    Evento e = new Evento();
                    e.setNome(evento.getString("nome"));
                    e.setImg(evento.getString("imagem"));
                    e.setLinkFacebook(evento.getString("site"));
                    eventos.add(e);



                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ListView listView = findViewById(R.id.listview);
            arrayAdapter = new EventoAdapter(ListaEvento.this, eventos);
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
    }
}
