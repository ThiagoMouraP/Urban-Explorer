package com.projeto.henrique.urbanexplorer;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class ComentarioAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] comentario;
    private final ArrayList<String> foto;

    public ComentarioAdapter(Activity context, String[] itemname, ArrayList itemFoto) {
        super(context, R.layout.dialogo, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.comentario=itemname;
        this.foto = itemFoto;
    }
    public View getView(int position, View view, ViewGroup parent) throws  IndexOutOfBoundsException {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.dialogo, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(comentario[position]);
        try{
            if (!foto.get(position).equals("")) {
                Picasso.with(context).load(Uri.parse(foto.get(position))).into(imageView);
            }
            else{
                imageView.setImageResource(R.drawable.unknown);
            }
        }catch (Exception e){
            imageView.setImageResource(R.drawable.unknown);
        }


        return rowView;
    }
}
