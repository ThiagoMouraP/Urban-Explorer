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

import static com.aplicativo.henrique.urbanexplorer.MainActivity.determinarDistancia;

public class listaRestaurantes extends AppCompatActivity {

    private RestauranteAdapter restauranteAdapter;
    private ListView listview;
    private ArrayList<Restaurante> restaurantes= new ArrayList<>();
    ProgressDialog pd;
    Hotspot hotspot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_restaurantes);
        Intent intent = getIntent();
        hotspot = (Hotspot) intent.getSerializableExtra("hotspot");
        new listaRestaurantes.JsonTask().execute("https://urbanweb.herokuapp.com/androidlerrestaurante.php?id="+hotspot.getIdcidade());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ListaHotSpotsComentarios.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(listaRestaurantes.this);
            pd.setMessage("Please wait");
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
                final JSONArray comida = obj.getJSONArray("Carregando");
                final int n = comida.length();
                for (int i = 0; i < n; ++i) {
                    final JSONObject restaurante = comida.getJSONObject(i);
                    Restaurante r = new Restaurante();
                    r.setNome(restaurante.getString("nome"));
                    r.setDistancia(determinarDistancia(Double.parseDouble(restaurante.getString( "latitude")), Double.parseDouble(restaurante.getString( "longitude"))));
                    r.setImg(restaurante.getString("imagem"));
                    r.setLatidute(Double.parseDouble(restaurante.getString( "latitude")));
                    r.setLongitude(Double.parseDouble(restaurante.getString( "longitude")));
                    if(r.determinarDistancia(hotspot.getLatidute(), hotspot.getLongitude())<Hotspot.PROXIMIDADERESTUARENTE){
                        restaurantes.add(r);
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
            listview = (ListView) findViewById(R.id.listview);
            Collections.sort(restaurantes);
            restauranteAdapter = new RestauranteAdapter(listaRestaurantes.this, restaurantes);
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
    }
}
