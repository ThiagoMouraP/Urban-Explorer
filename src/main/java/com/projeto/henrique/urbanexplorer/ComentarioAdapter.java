package com.aplicativo.henrique.urbanexplorer;

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




public class ComentarioAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<Comentario> comentario;

    public ComentarioAdapter(Activity context, ArrayList<Comentario> itemname) {
        super(context, R.layout.dialogo, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.comentario=itemname;

    }
    public View getView(int position, View view, ViewGroup parent) throws  IndexOutOfBoundsException {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.dialogo, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(comentario.get(position).getUser()+": "+comentario.get(position).getMensagem());
        try{
            if (comentario.get(position).getFoto()!=null) {
                Picasso.with(context).load(Uri.parse(comentario.get(position).getFoto())).into(imageView);
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
