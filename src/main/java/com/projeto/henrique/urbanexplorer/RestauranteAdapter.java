package com.projeto.henrique.urbanexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RestauranteAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Restaurante> beanClassArrayList;
    public RestauranteAdapter(Context context, ArrayList<Restaurante> beanClassArrayList) {
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

        RestauranteAdapter.ViewHoder viewHoder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview, parent, false);
            viewHoder = new RestauranteAdapter.ViewHoder();
            viewHoder.image = (ImageView) convertView.findViewById(R.id.market);
            viewHoder.title = (TextView) convertView.findViewById(R.id.sport);
            convertView.setTag(viewHoder);

        } else {

            viewHoder = (RestauranteAdapter.ViewHoder) convertView.getTag();
        }
        Restaurante beanClass = (Restaurante) getItem(position);
        if(beanClass.getDistancia()<MainActivity.PROXIMIDADE){
            viewHoder.title.setText(beanClass.getNome()+ " "+context.getString(R.string.proximidade));
        }
        else{
            viewHoder.title.setText(beanClass.getNome()+ " - "+beanClass.getDistancia()+ " km");
        }
        viewHoder.image.setImageResource(beanClass.getImagem());
        return convertView;

    }
    private class ViewHoder{

        ImageView image;
        TextView title;
        TextView description;

    }
}
