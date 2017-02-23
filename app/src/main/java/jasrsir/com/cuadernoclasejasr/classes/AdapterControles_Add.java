package jasrsir.com.cuadernoclasejasr.classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import jasrsir.com.cuadernoclasejasr.FaltasHoy;
import jasrsir.com.cuadernoclasejasr.R;
import jasrsir.com.cuadernoclasejasr.utils.RestClient;

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
    //private CardViewHolder holder;

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
        Button fabControlAdd;
        EditText observaciones;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View rootview = convertView;
        CardViewHolder holder = new CardViewHolder();

       // if (rootview == null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootview = inflater.inflate(R.layout.faltahoy_item, parent, false);
            holder.id = (TextView) rootview.findViewById(R.id.htxvIDALUMNO);
            holder.fabControlAdd = (Button) rootview.findViewById(R.id.fabControlAdd);
            holder.nombreEst = (TextView) rootview.findViewById(R.id.hTxvNombreAlumno);
            holder.falta = (TextView) rootview.findViewById(R.id.htxvVALORFALTA);
            holder.trabajo = (TextView) rootview.findViewById(R.id.htxvVALORTRABAJO);
            holder.actitud = (TextView) rootview.findViewById(R.id.htxvVALORACTITUD);
            holder.faltaimg = (ImageView) rootview.findViewById(R.id.himgFalta);
            holder.trabajoimg = (ImageView) rootview.findViewById(R.id.himgTrabajo);
            holder.actitudimg = (ImageView) rootview.findViewById(R.id.himgActitud);
            holder.observaciones = (EditText) rootview.findViewById(R.id.hedtObservacion);
            holder.observaciones.setEnabled(true);

        //    rootview.setTag(holder);
       // } else{
         //   holder = (CardViewHolder) rootview.getTag();
       // }
        holder.id.setText(String.valueOf(((Estudiante)getItem(position)).getId()));
        holder.nombreEst.setText(((Estudiante)getItem(position)).getNombre() +" " + ((Estudiante)getItem(position)).getApellidos() );
        final CardViewHolder holderf = holder;

        final Estudiante estudiante = (Estudiante)getItem(position);
        switch (estudiante.getFaltasB()) {
            case 0:
                holderf.falta.setText(FALTAS[0]);
                holderf.faltaimg.setImageResource(IMG_FALTAS[0]);
                break;
            case 1:
                holderf.falta.setText(FALTAS[1]);
                holderf.faltaimg.setImageResource(IMG_FALTAS[1]);
                break;
            case 2:
                holderf.falta.setText(FALTAS[2]);
                holderf.faltaimg.setImageResource(IMG_FALTAS[2]);
                break;
            default:
                break;
        }
        switch (estudiante.getTrabajoB()) {
            case 0:
                holderf.trabajo.setText(TRABAJO[0]);
                holderf.trabajoimg.setImageResource(IMG_TRABAJO[0]);
                break;
            case 1:
                holderf.trabajo.setText(TRABAJO[1]);
                holderf.trabajoimg.setImageResource(IMG_TRABAJO[1]);
                break;
            case 2:
                holderf.trabajo.setText(TRABAJO[2]);
                holderf.trabajoimg.setImageResource(IMG_TRABAJO[2]);
                break;
            default:
                break;

        }
        switch (estudiante.getActitudB()) {
            case 0:
                holderf.actitud.setText(ACTITUD[0]);
                holderf.actitudimg.setImageResource(IMG_ACTITUD[0]);
                break;
            case 1:
                holderf.actitud.setText(ACTITUD[1]);
                holderf.actitudimg.setImageResource(IMG_ACTITUD[1]);
                break;
            default:
                break;

        }

        holder.observaciones.requestFocus();
        if (!estudiante.getControl().getObservacion().isEmpty()) {
            holderf.observaciones.setText(estudiante.getControl().getObservacion());
        }

        holder.observaciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            estudiante.getControl().setObservacion(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.fabControlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(getItem(position));
                //remove(getView(position,convertView,parent));
                remove(getItem(position));
                notifyDataSetChanged();

            }

            private void guardar(final Object item) {
                Control control = null;
                SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
                    control = new Control();
                    control.setIdEstudiante(Integer.parseInt(holderf.id.getText().toString()));
                    control.setActitud(holderf.actitud.getText().toString());
                    control.setFalta(holderf.falta.getText().toString());
                    control.setTrabajo(holderf.trabajo.getText().toString());
                    control.setObservacion(holderf.observaciones.getText().toString());
                    control.setFecha(smf.format(new Date()));

                final ProgressDialog progreso = new ProgressDialog(context);
                RequestParams params = new RequestParams();
                params.put("idEstudiante", control.getIdEstudiante());
                params.put("falta", control.getFalta());
                params.put("actitud", control.getActitud());
                params.put("trabajo", control.getTrabajo());
                params.put("observacion", control.getObservacion());
                params.put("fecha", control.getFecha());
                RestClient.post("control", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progreso.setMessage("Enviando Control . . .");
                        progreso.setCancelable(false);
                        progreso.show();
                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // Handle resulting parsed JSON response here
                        progreso.dismiss();
                        Result result;
                        Gson gson = new Gson();
                        String message;
                        result = gson.fromJson(String.valueOf(response), Result.class);
                        if (result != null)
                            if (result.getEstado()) {
                                message = "Control ADJUDICADO";

                            } else
                                message = "Error: YA HAY UN CONTROL DEL ALUMNO";
                        else
                            message = "Null data";
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        progreso.dismiss();
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        progreso.dismiss();
                    }
                });
            }

            });


        holder.faltaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupu();
            }

            private void popupu() {
                holderf.dialog = new AlertDialog.Builder(context)
                        .setSingleChoiceItems(FALTAS,7 ,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((Estudiante) getItem(position)).setFaltasB(i);
                                holderf.falta.setText(FALTAS[i]);
                                holderf.faltaimg.setImageResource(IMG_FALTAS[i]);
                                notifyDataSetChanged();

                            }
                        }).setTitle("Elige el tipo de falta")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holderf.dialog.closeOptionsMenu();

                            }
                        }).create();

                holderf.dialog.show();
            }
        });
        holder.trabajoimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupu();
            }

            private void popupu() {
                holderf.dialog = new AlertDialog.Builder(context)
                        .setSingleChoiceItems(TRABAJO, 7, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((Estudiante) getItem(position)).setTrabajoB(i);
                                holderf.trabajo.setText(TRABAJO[i]);
                                holderf.trabajoimg.setImageResource(IMG_TRABAJO[i]);
                                notifyDataSetChanged();

                            }
                        }).setTitle("¿Cómo ha trabajado el alumno?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holderf.dialog.closeOptionsMenu();

                            }
                        }).create();
                holderf.dialog.show();
            }
        });
        holder.actitudimg .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupu();
            }

            private void popupu() {
                holderf.dialog = new AlertDialog.Builder(context)
                        .setSingleChoiceItems(ACTITUD, 7, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((Estudiante) getItem(position)).setActitudB(i);
                                holderf.actitud.setText(ACTITUD[i]);
                                holderf.actitudimg.setImageResource(IMG_ACTITUD[i]);
                                notifyDataSetChanged();

                            }
                        }).setTitle("¿Cómo es su actitud?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holderf.dialog.closeOptionsMenu();

                            }
                        }).create();
                holderf.dialog.show();
            }
        });

        return rootview;
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

        return null;
    }





}
