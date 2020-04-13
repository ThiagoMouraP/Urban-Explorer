package com.aplicativo.henrique.urbanexplorer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.aplicativo.henrique.urbanexplorer.MainActivity.user;


public class Configuracao extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private CheckBox carro, bicicleta, publico, ape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcao);
        TextView tv = (TextView) findViewById(R.id.score);
        tv.setText(getString(R.string.nav));
        final ImageView foto = findViewById(R.id.foto);
        if(user.getPhotoUrl()!=null){
            Picasso.with(this).load(user.getPhotoUrl()).into(foto);
        }
        else{
            foto.setImageResource(R.drawable.unknown);
        }
        carro = (CheckBox) findViewById(R.id.carro);
        bicicleta = (CheckBox) findViewById(R.id.bicicleta);
        publico = (CheckBox) findViewById(R.id.publico);
        ape = (CheckBox) findViewById(R.id.ape);
        if(Servico.getNavegacao()==null){
            carro.setChecked(true);
        }
        else{
            if(Servico.getNavegacao().equals("d")){
                carro.setChecked(true);
            }
            if(Servico.getNavegacao().equals("w")){
                ape.setChecked(true);
            }
            if(Servico.getNavegacao().equals("r")){
                publico.setChecked(true);
            }
            if(Servico.getNavegacao().equals("b")){
                bicicleta.setChecked(true);
            }
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        String[] linguas = new String[] {"","English", "Português", "Español"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, linguas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void onCheckboxClicked(View view) {

        switch (view.getId()) {

            case R.id.carro:

                bicicleta.setChecked(false);
                publico.setChecked(false);
                ape.setChecked(false);
                atualizarNavegacao("d");
                break;

            case R.id.bicicleta:

                carro.setChecked(false);
                publico.setChecked(false);
                ape.setChecked(false);
                atualizarNavegacao("b");
                break;

            case R.id.publico:

                carro.setChecked(false);
                bicicleta.setChecked(false);
                ape.setChecked(false);
                atualizarNavegacao("r");
                break;
            case R.id.ape:

                carro.setChecked(false);
                bicicleta.setChecked(false);
                publico.setChecked(false);
                atualizarNavegacao("w");
                break;


        }
    }
    public static void atualizarNavegacao(String opcao){
        try {
            Map<String, String> mapa = new HashMap<>();
            Servico.setNavegacao(opcao);
            mapa.put("navegacao", Servico.getNavegacao());
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            db.collection(user.getUid()).document("nav").set(mapa);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Servico.cidadesServico = new ArrayList<>();
        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        switch (item) {
            case "English":
                switchLocal(this, "en");
                onBackPressed();
                break;
            case "Português":
                switchLocal(this, "pt");
                onBackPressed();
                break;
            case "Español":
                switchLocal(this, "es");
                onBackPressed();
                break;
            default:
                switchLocal(this, "pt");
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public static void switchLocal(Context context, String lcode) {
        if (lcode.equalsIgnoreCase(""))
            return;
        Resources resources = context.getResources();
        Locale locale = new Locale(lcode);
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
    public void mandarSugestao(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"henrique.rodrigues@uniriotec.br", "thiago.parracho@uniriotec.br", "victor.wirz@uniriotec.br"});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.sugestaoApp));
        i.putExtra(Intent.EXTRA_TEXT   , getString(R.string.meunome)+" "+user.getDisplayName()+" "+getString(R.string.tenhosugestao));
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
