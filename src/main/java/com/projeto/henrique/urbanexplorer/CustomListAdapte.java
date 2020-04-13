package com.aplicativo.henrique.urbanexplorer;

/**
 * Created by henrique on 01/06/2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;




public class CustomListAdapte extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<Emblema> emblemas;

    public CustomListAdapte(Activity context, ArrayList itemname) {
        super(context, R.layout.dialogo, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.emblemas=itemname;
    }

    public View getView(int position,View view,ViewGroup parent) throws  IndexOutOfBoundsException{
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.dialogo, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(setDescricao(position));
        imageView.setImageResource(setImagem(position));
        return rowView;

    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public int setImagem(int position){
        try{
            if (  Servico.getPonto() >= emblemas.get(position).getPontosNecessarios()) {
                return emblemas.get(position).getImagem();
            }
            else{
                return  R.drawable.vazio2;
            }
        }catch (Exception e){
            return R.drawable.vazio2;
        }
    }
    public String setDescricao(int position) {
        try {
            if (Servico.getPonto() >= emblemas.get(position).getPontosNecessarios()) {
                return emblemas.get(position).getDescricao() + " - "+ context.getString(R.string.conquistado);
            } else {
                return emblemas.get(position).getDescricao() + " - " + emblemas.get(position).getPontosNecessarios() + " "+context.getString(R.string.pontos);
            }

        } catch (Exception e) {
            return emblemas.get(position).getDescricao() + " - " + emblemas.get(position).getPontosNecessarios() + " "+context.getString(R.string.pontos);
        }
    }
}
