package com.aplicativo.henrique.urbanexplorer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class listViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Lugar> beanClassArrayList;
    public listViewAdapter(Context context, ArrayList<Lugar> beanClassArrayList) {
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

        ViewHoder viewHoder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview, parent, false);
            viewHoder = new ViewHoder();
            viewHoder.image = (ImageView) convertView.findViewById(R.id.market);
            viewHoder.title = (TextView) convertView.findViewById(R.id.sport);
            convertView.setTag(viewHoder);

        } else {

            viewHoder = (ViewHoder) convertView.getTag();
        }
        Lugar beanClass = (Lugar) getItem(position);
        if(beanClass instanceof Cidade){
            viewHoder.title.setText(beanClass.getNome());
        }
        else{
            if(beanClass.getDistancia()<MainActivity.PROXIMIDADE){
                viewHoder.title.setText(beanClass.getNome()+ " "+context.getString(R.string.proximidade));
            }
            else{
                viewHoder.title.setText(beanClass.getNome()+ " - "+beanClass.getDistancia()+ " km");
            }
        }

        Picasso.with(context).load(Uri.parse(beanClass.getImg())).into(viewHoder.image);
        //viewHoder.image.setImageResource(beanClass.getImagem());
        return convertView;

    }
    private class ViewHoder{

        ImageView image;
        TextView title;
        TextView description;

    }
}

