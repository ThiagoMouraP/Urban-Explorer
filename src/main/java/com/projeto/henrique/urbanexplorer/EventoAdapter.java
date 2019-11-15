package com.projeto.henrique.urbanexplorer;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter<Evento> {
    private final Activity context;
    private final  ArrayList<Evento> evento;

    public EventoAdapter(Activity context, ArrayList<Evento> itemname) {
        super(context, R.layout.dialogo, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.evento=itemname;
    }
    public View getView(int position, View view, ViewGroup parent) throws  IndexOutOfBoundsException {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.dialogo, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(evento.get(position).toString());
        try{
            imageView.setImageResource(evento.get(position).getImagem());
        }catch (Exception e){
            imageView.setImageResource(R.drawable.unknown);
        }
        return rowView;
    }
}
