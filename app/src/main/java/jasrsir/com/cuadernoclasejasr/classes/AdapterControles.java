package jasrsir.com.cuadernoclasejasr.classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jasrsir.com.cuadernoclasejasr.R;

/**
 * Copyright (c) 2017 Juan Antonio Suárez Rosa (JasrSir)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * jasrsir@gmail.com
 * <p>
 * Created by jasrsir on 20/02/17.
 */
public class AdapterControles extends ArrayAdapter {

    public static final String ACT_POS = "positiva";
    public static final String ACT_NEG = "negativa";
    public static final String TRAB_BUENA = "bueno";
    public static final String TRAB_REG = "regular";
    public static final String TRAB_MALO = "malo";
    public static final String FALT_INJUS = "injustificada";
    public static final String FALT_JUS = "justificada";
    public static final String FALT_RETRA = "retraso";
    private Context context;
    private CardViewHolder holder;

    public AdapterControles(Context context, ArrayList<Control> controles) {
        super(context, R.layout.estudiante_item, controles );
        this.context = context;
    }
    class CardViewHolder{
        TextView fecha;
        ImageView faltaimg;
        ImageView trabajoimg;;
        ImageView actitudimg;
        EditText observaciones;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootview = convertView;
        holder = new CardViewHolder();

        if (rootview == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootview = inflater.inflate(R.layout.control_item, parent, false);
            holder.fecha = (TextView) rootview.findViewById(R.id.txvFecha);
            holder.faltaimg = (ImageView) rootview.findViewById(R.id.imgFalta);
            holder.trabajoimg = (ImageView) rootview.findViewById(R.id.imgTrabajo);
            holder.actitudimg = (ImageView) rootview.findViewById(R.id.imgActitud);
            holder.observaciones = (EditText) rootview.findViewById(R.id.edtObservacion);

            rootview.setTag(holder);
        } else{
            holder = (CardViewHolder) rootview.getTag();
        }

        switch (((Control)getItem(position)).getFalta()){
            case FALT_INJUS:
                holder.faltaimg.setImageResource(R.mipmap.ic_faltanojustif);
                break;
            case FALT_JUS:
                holder.faltaimg.setImageResource(R.mipmap.ic_faltajustific);
                break;
            case FALT_RETRA:
                holder.faltaimg.setImageResource(R.mipmap.ic_faltaretraso);
                break;
        }
        switch (((Control)getItem(position)).getActitud()){
            case ACT_NEG:
                holder.actitudimg.setImageResource(R.mipmap.ic_actneg);
                break;
            case ACT_POS:
                holder.actitudimg.setImageResource(R.mipmap.ic_actpos);
                break;
        }
        switch (((Control)getItem(position)).getTrabajo()){
            case TRAB_BUENA:
                holder.trabajoimg.setImageResource(R.mipmap.ic_good);
                break;
            case TRAB_MALO:
                holder.trabajoimg.setImageResource(R.mipmap.ic_bad);
                break;
            case TRAB_REG:
                holder.trabajoimg.setImageResource(R.mipmap.ic_notbad);
                break;

        }
        holder.fecha.setText(new SimpleDateFormat().format(((Control)getItem(position)).getFecha()));
        holder.observaciones.setText(((Control)getItem(position)).getObservacion());
        holder.observaciones.setEnabled(false);
        return rootview;
    }

}
