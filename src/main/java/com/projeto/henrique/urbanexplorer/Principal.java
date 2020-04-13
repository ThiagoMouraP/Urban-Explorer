package com.aplicativo.henrique.urbanexplorer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import static com.aplicativo.henrique.urbanexplorer.MainActivity.lat;
import static com.aplicativo.henrique.urbanexplorer.MainActivity.longi;
import static com.aplicativo.henrique.urbanexplorer.MainActivity.user;


public class Principal extends AppCompatActivity {
    ProgressDialog pd;
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
            new JsonTask().execute("https://urbanweb.herokuapp.com/androidlercidade.php");
            MainActivity.pegarNavegacao();
            MainActivity.pegarPonto();
            listview = (ListView) findViewById(R.id.listview);
            Collections.sort(Servico.getCidadesServico());

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Principal.this, PontoCidade.class);
                    intent.putExtra("cidade", Servico.getCidadesServico().get(i));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
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
    public void irDir(View view){
        Intent intent = new Intent(this, Configuracao.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void irComentario(View view){
        Intent intent = new Intent(this, ListaHotSpotsComentarios.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Principal.this);
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
                final JSONArray cidades = obj.getJSONArray("cidades");
                final int n = cidades.length();
                for (int i = 0; i < n; ++i) {
                    final JSONObject cidade = cidades.getJSONObject(i);
                    Cidade c = new Cidade(cidade.getString("nome"));
                    c.setDistancia(determinarDistancia(Double.parseDouble(cidade.getString( "latitude")), Double.parseDouble(cidade.getString( "longitude"))));
                    c.setImg(cidade.getString("imagem"));
                    c.setId(Integer.parseInt(cidade.getString("id")));
                    Servico.cidadesServico.add(c);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Collections.sort(Servico.cidadesServico);
            listViewAdapter = new listViewAdapter(Principal.this, Servico.getCidadesServico());
            listview.setAdapter(listViewAdapter);
        }
    }


}
