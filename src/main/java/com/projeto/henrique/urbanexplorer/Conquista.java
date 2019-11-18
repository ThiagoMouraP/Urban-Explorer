package com.projeto.henrique.urbanexplorer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;



import static com.projeto.henrique.urbanexplorer.MainActivity.user;

public class Conquista extends AppCompatActivity {
    public  ArrayList<Emblema> emblemas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conquista);
        MainActivity.pegarPonto();
        TextView tv = (TextView) findViewById(R.id.score);
        if(  Servico.getPonto()!=null){
            tv.setText(getString(R.string.possuipontos)+" "+  Servico.getPonto()+" "+getString(R.string.pontos));
        }
        else{
            tv.setText(getString(R.string.possuipontos)+" 0 "+getString(R.string.pontos));
        }

        final ImageView foto = findViewById(R.id.foto);
        try {
            if(user.getPhotoUrl()!=null){
                Picasso.with(Conquista.this).load(user.getPhotoUrl()).into(foto);
            }
            else{
                foto.setImageResource(R.drawable.unknown);
            }
        } catch (Exception e) {
            Toast.makeText(Conquista.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        criaEmblemas(this);
        ListView listview;
        listview = (ListView) findViewById(R.id.listview2);
        //beanClassArrayList = new ArrayList<BeanClass>();
        CustomListAdapte customListAdapte = new CustomListAdapte(this, emblemas);
        listview.setAdapter(customListAdapte);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (ActivityCompat.checkSelfPermission(Conquista.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Conquista.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                else{
                    if(emblemas.get(i).getPontosNecessarios()<=Servico.getPonto()){
                        try {
                            Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(Conquista.this.getContentResolver(),
                                    BitmapFactory.decodeResource(getResources(), emblemas.get(i).getImagem()), null, null));
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.setType("image/jpeg");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.compartilharbadge)+" "+emblemas.get(i).getDescricao());
                            sendIntent.putExtra(Intent.EXTRA_STREAM,  imageUri);
                            startActivity(Intent.createChooser( sendIntent, "Share image using"));
                        }catch (Exception e){
                            Toast.makeText(Conquista.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void criaEmblemas(Context context){
        emblemas.add(new Emblema(context.getString(R.string.badge1), R.drawable.pes2, new Long(5)));
        emblemas.add(new Emblema(context.getString(R.string.vizinhanza), R.drawable.village2, new Long(15)));
        emblemas.add(new Emblema(context.getString(R.string.badge3), R.drawable.bairro2, new Long(30)));
        emblemas.add(new Emblema(context.getString(R.string.badge4), R.drawable.bigcity2, new Long(45)));
        emblemas.add(new Emblema(context.getString(R.string.badge5), R.drawable.nomade2, new Long(65)));
        emblemas.add(new Emblema(context.getString(R.string.badge6), R.drawable.camera2, new Long(95)));
        emblemas.add(new Emblema(context.getString(R.string.badge7), R.drawable.region2, new Long(125)));
        emblemas.add(new Emblema(context.getString(R.string.badge8), R.drawable.turista2, new Long(150)));
        emblemas.add(new Emblema(context.getString(R.string.badge9), R.drawable.compass2, new Long(200)));
        emblemas.add(new Emblema(context.getString(R.string.badgr10), R.drawable.fragata2, new Long(300)));
        emblemas.add(new Emblema(context.getString(R.string.badge11), R.drawable.continente2, new Long(450)));
        emblemas.add(new Emblema(context.getString(R.string.badge12), R.drawable.world2, new Long(600)));
        emblemas.add(new Emblema(context.getString(R.string.badge13), R.drawable.galaxy2, new Long(900)));
        //450
        ///600
    }
    public ArrayList<Emblema> retornarEmblemas(Context context){
        criaEmblemas(context);
        return emblemas;
    }
    public void irRanking(View view){
        Intent intent = new Intent(this, Ranking.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
