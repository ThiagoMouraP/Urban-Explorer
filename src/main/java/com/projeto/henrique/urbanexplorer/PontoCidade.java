package com.projeto.henrique.urbanexplorer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;




import static com.projeto.henrique.urbanexplorer.MainActivity.user;
import static com.projeto.henrique.urbanexplorer.MainActivity.lat;
import static com.projeto.henrique.urbanexplorer.MainActivity.longi;

public class PontoCidade extends AppCompatActivity {
    private listViewAdapter listViewAdapter;
    private String mCurrentPhotoPath;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder newbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(newbuilder.build());
        setContentView(R.layout.activity_ponto_cidade);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if(Servico.getNavegacao()==null){
            Servico.setNavegacao("d");
        }
        Intent intent = getIntent();
        final Cidade city = (Cidade)intent.getSerializableExtra("cidade") ;
         ListView listview;
         listview = (ListView) findViewById(R.id.listview);
         Collections.sort(Servico.getCidadesServico());
         Collections.sort(city.getHotspots());
         listViewAdapter = new listViewAdapter(PontoCidade.this, city.getHotspots());
         listview.setAdapter(listViewAdapter);
         listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 if(city.getHotspots().get(i).getDistancia()<MainActivity.PROXIMIDADE){
                     if (ActivityCompat.checkSelfPermission(PontoCidade.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                         ActivityCompat.requestPermissions(PontoCidade.this, new String[]{Manifest.permission.CAMERA}, 1);
                     }
                     else{
                         try{
                             atualizarPonto();
                             dispatchTakePictureIntent();
                         }catch (Exception e){
                             //Toast.makeText(PontoCidade.this, "1 - "+e.getMessage(), Toast.LENGTH_LONG).show();
                         }

                     }
                 }
                 else{
                     String dir = "google.navigation:q="+city.getHotspots().get(i).getLatidute()+","+city.getHotspots().get(i).getLongitude()+
                             "&mode="+Servico.getNavegacao();
                     Uri gmmIntentUri = Uri.parse(dir);
                     Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                     mapIntent.setPackage("com.google.android.apps.maps");
                     startActivity(mapIntent);
                 }
             }
         });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 1 && resultCode == RESULT_OK){
                File f = new File(mCurrentPhotoPath);
                Uri contentUri = Uri.fromFile(f);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("image/jpeg");
                sendIntent.putExtra(Intent.EXTRA_STREAM,  contentUri);
                startActivity(Intent.createChooser( sendIntent, "Share image using"));
            }
        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(PontoCidade.this, "2 -"+e.getMessage(), Toast.LENGTH_LONG).show();
            dispatchTakePictureIntent();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void atualizarPonto(){
        try {
            Map<String, Long> mapa = new HashMap<>();
            Servico.setPonto(Servico.getPonto()+5);
            mapa.put("ponto", Servico.getPonto());
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setTimestampsInSnapshotsEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            db.collection(user.getUid()).document("score").set(mapa);
            atualizarRanking();
        }catch (Exception e){
            Toast.makeText(PontoCidade.this, "3 - "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.pegarPonto();
        GPSTracker g = new GPSTracker(this);
        lat = g.getLatitude();
        longi = g.getLongitude();
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void dispatchTakePictureIntent() {
        try{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(PontoCidade.this, "4 - " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.projeto.henrique.urbanexplorer.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        }catch (Exception e){
            Toast.makeText(PontoCidade.this, "5 - "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void atualizarRanking(){
        Map<String, String> mapa = new HashMap<>();
        mapa.put(user.getUid(), user.getDisplayName()+ " - "+Servico.getPonto()+ "λ"+user.getPhotoUrl());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        db.collection("ranking").document(user.getUid()).set(mapa);
    }



}
