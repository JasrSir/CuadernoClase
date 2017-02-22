package jasrsir.com.cuadernoclasejasr.classes;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jasrsir.com.cuadernoclasejasr.FaltasHoy;
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
public class AdapterControles_Add extends ArrayAdapter {

    public static final String ACT_POS = "positiva";
    public static final String ACT_NEG = "negativa";
    public static final String[] ACTITUD = {ACT_POS,ACT_NEG};
    public static final int[] IMG_ACTITUD = {R.mipmap.ic_actpos,R.mipmap.ic_actneg};
    public static final String TRAB_BUENA = "bueno";
    public static final String TRAB_REG = "regular";
    public static final String TRAB_MALO = "malo";
    public static final String[] TRABAJO = {TRAB_BUENA,TRAB_REG, TRAB_MALO};
    public static final int[] IMG_TRABAJO= {R.mipmap.ic_good,R.mipmap.ic_notbad, R.mipmap.ic_bad};
    public static final String FALT_INJUS = "injustificada";
    public static final String FALT_JUS = "justificada";
    public static final String FALT_RETRA = "retraso";
    public static final String[] FALTAS = {FALT_INJUS,FALT_JUS, FALT_RETRA};
    public static final int[] IMG_FALTAS= {R.mipmap.ic_faltanojustif,R.mipmap.ic_faltajustific, R.mipmap.ic_faltaretraso};

    private ArrayList<Control> controlList;
    private Context context;
    private CardViewHolder holder;

    public AdapterControles_Add(Context context, ArrayList<Estudiante> estud) {
        super(context, R.layout.faltahoy_item, estud );
        this.controlList = new ArrayList<>();
        this.context = context;
    }
    class CardViewHolder{
        AlertDialog dialog;
        TextView id;
        TextView nombreEst;
        TextView falta;
        TextView trabajo;
        TextView actitud;
        ImageView faltaimg;
        ImageView trabajoimg;;
        ImageView actitudimg;
        EditText observaciones;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rootview = convertView;
        holder = new CardViewHolder();

        if (rootview == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootview = inflater.inflate(R.layout.faltahoy_item, parent, false);
            holder.id = (TextView) rootview.findViewById(R.id.htxvIDALUMNO);
            holder.nombreEst = (TextView) rootview.findViewById(R.id.hTxvNombreAlumno);
            holder.falta = (TextView) rootview.findViewById(R.id.htxvVALORFALTA);
            holder.trabajo = (TextView) rootview.findViewById(R.id.htxvVALORTRABAJO);
            holder.actitud = (TextView) rootview.findViewById(R.id.htxvVALORACTITUD);
            holder.faltaimg = (ImageView) rootview.findViewById(R.id.himgFalta);
            holder.trabajoimg = (ImageView) rootview.findViewById(R.id.himgTrabajo);
            holder.actitudimg = (ImageView) rootview.findViewById(R.id.himgActitud);
            holder.observaciones = (EditText) rootview.findViewById(R.id.hedtObservacion);

            rootview.setTag(holder);
        } else{
            holder = (CardViewHolder) rootview.getTag();
        }
        holder.id.setText(String.valueOf(((Estudiante)getItem(position)).getId()));
        holder.nombreEst.setText(((Estudiante)getItem(position)).getNombre() +" " + ((Estudiante)getItem(position)).getApellidos() );

        holder.faltaimg.setO(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        })




            private void popupu(int i) {
                holder.dialog = new AlertDialog.Builder(context)
                        .setSingleChoiceItems(FALTAS, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                holder.falta.setText(FALTAS[i]);
                                holder.faltaimg.setImageResource(IMG_FALTAS[i]);
                                notifyDataSetChanged();
                                holder.dialog.closeOptionsMenu();

                            }
                        }).setTitle("Elige el tipo de falta")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holder.dialog.closeOptionsMenu();
                            }
                        }).create();

                holder.dialog.show();
            }

        holder.trabajoimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupu(2);
            }

            private void popupu(int i) {
                holder.dialog = new AlertDialog.Builder(context)
                        .setSingleChoiceItems(TRABAJO, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                holder.trabajo.setText(TRABAJO[i]);
                                holder.trabajoimg.setImageResource(IMG_TRABAJO[i]);
                                notifyDataSetChanged();

                            }
                        }).setTitle("¿Cómo ha trabajado el alumno?").create();
                holder.dialog.show();
            }
        });
        holder.actitudimg .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupu(3);
            }

            private void popupu(int i) {
                holder.dialog = new AlertDialog.Builder(context)
                        .setSingleChoiceItems(ACTITUD, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                holder.actitud.setText(ACTITUD[i]);
                                holder.actitudimg.setImageResource(IMG_ACTITUD[i]);
                                notifyDataSetChanged();
                            }
                        }).setTitle("¿Cómo es su actitud?").create();
                holder.dialog.show();
            }
        });

        return rootview;
    }

    public void popup(int caso) {

        switch (caso) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }


    }

    public void start(ArrayList<Control> controlArrayList) {
        clear();
        addAll(controlArrayList);
        notifyDataSetChanged();
    }


    public Control getAt(int position){
        return this.controlList.get(position);
    }
    public Control createControl() {
        Control control = new Control();
        return null;
    }





}
