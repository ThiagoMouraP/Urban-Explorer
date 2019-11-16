package com.projeto.henrique.urbanexplorer;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Evento> beanClassArrayList;
    public EventoAdapter(Context context, ArrayList<Evento> beanClassArrayList) {
        this.context = context;
        this.beanClassArrayList = beanClassArrayList;
    }

    @Override
    public int getCount() {
        return beanClassArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanClassArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        EventoAdapter.ViewHoder viewHoder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview, parent, false);
            viewHoder = new  EventoAdapter.ViewHoder();
            viewHoder.image = (ImageView) convertView.findViewById(R.id.market);
            viewHoder.title = (TextView) convertView.findViewById(R.id.sport);
            convertView.setTag(viewHoder);

        } else {

            viewHoder = ( EventoAdapter.ViewHoder) convertView.getTag();
        }
        Evento beanClass = (Evento) getItem(position);
        viewHoder.title.setText(beanClass.getNome());
        viewHoder.image.setImageResource(beanClass.getImagem());
        return convertView;

    }
    private class ViewHoder{

        ImageView image;
        TextView title;
        TextView description;

    }
}
