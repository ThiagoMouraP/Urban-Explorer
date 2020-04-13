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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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


public class ListaHotSpotsComentarios extends AppCompatActivity {
    private listViewAdapterAvaliacao  listViewAdapter;
    private ListView listview;
    private ArrayList<Lugar> listahotspots = new ArrayList<>();
    ProgressDialog pd;
    Cidade city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hot_spots_comentarios);
        city = (Cidade)Servico.retornarCidades().get(0) ;
        new ListaHotSpotsComentarios.JsonTask().execute("https://urbanweb.herokuapp.com/androidlerhotspot.php?id="+city.getId());
    }
    public void onBackPressed() {
        super.onBackPressed();

        Servico.cidadesServico = new ArrayList<>();

        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ListaHotSpotsComentarios.this);
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
                final JSONArray hotspots= obj.getJSONArray("hotspot");
                final int n = hotspots.length();
                for (int i = 0; i < n; ++i) {
                    final JSONObject hotspot = hotspots.getJSONObject(i);
                    Hotspot h = new Hotspot(hotspot.getString("nome"));
                    h.setDistancia(determinarDistancia(Double.parseDouble(hotspot.getString( "latitude")), Double.parseDouble(hotspot.getString( "longitude"))));
                    h.setImg(hotspot.getString("imagem"));
                    h.setLatidute(Double.parseDouble(hotspot.getString( "latitude")));
                    h.setLongitude(Double.parseDouble(hotspot.getString( "longitude")));
                    h.setLinkSite(hotspot.getString("site"));
                    h.setId(Integer.parseInt(hotspot.getString("id")));
                    h.setIdcidade(Integer.parseInt(hotspot.getString("idcidade")));
                    listahotspots.add(h);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            listview = (ListView) findViewById(R.id.listview);
            Collections.sort(listahotspots);
            listViewAdapter = new listViewAdapterAvaliacao(ListaHotSpotsComentarios.this, listahotspots);
            Collections.sort(listahotspots);
            listview.setAdapter(listViewAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ListaHotSpotsComentarios.this, ComentarioHotspot.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("hotspot",listahotspots.get(i));
                    intent.putExtra("restaurantes", city.getRestaurantes());
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

}
