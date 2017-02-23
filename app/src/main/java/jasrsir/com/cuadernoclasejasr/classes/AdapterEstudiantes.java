package jasrsir.com.cuadernoclasejasr.classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import jasrsir.com.cuadernoclasejasr.R;

/**
 * Copyright (c) 2017 Juan Antonio Suárez Rosa (JasrSir)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * jasrsir@gmail.com
 * <p>
 * Created by jasrsir on 19/02/17.
 */
public class AdapterEstudiantes extends ArrayAdapter {

    public static ArrayList<Estudiante> estudiantesList;
    private Context context;
    private CardViewHolder holder;

    public AdapterEstudiantes(Context context) {
        super(context, R.layout.estudiante_item );
        this.estudiantesList = new ArrayList<>();
        this.context = context;
        start(estudiantesList);
    }
    class CardViewHolder{
        TextView id;
        TextView nombreApp;
        TextView direccion;
        TextView ciudad;
        TextView codPos;
        TextView telefono;
        TextView email;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootview = convertView;
        holder = new CardViewHolder();

        if (rootview == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootview = inflater.inflate(R.layout.estudiante_item, parent, false);
            holder.id = (TextView) rootview.findViewById(R.id.txvIdAlum);
            holder.nombreApp = (TextView) rootview.findViewById(R.id.txvNombreApellAlum);
            holder.direccion = (TextView) rootview.findViewById(R.id.txvDireccionAlum);
            holder.ciudad = (TextView) rootview.findViewById(R.id.txvCiudadAlum);
            holder.codPos = (TextView) rootview.findViewById(R.id.txvCodPosAlum);
            holder.telefono = (TextView) rootview.findViewById(R.id.txvTelefonoAlum);
            holder.email = (TextView) rootview.findViewById(R.id.txvEmailAlum);

            rootview.setTag(holder);
        } else{
            holder = (CardViewHolder) rootview.getTag();
        }
        holder.id.setText(String.valueOf(((Estudiante)getItem(position)).getId()));
        holder.nombreApp.setText(((Estudiante)getItem(position)).getNombre() + " "+ ((Estudiante)getItem(position)).getApellidos());
        holder.direccion.setText(((Estudiante)getItem(position)).getDireccion());
        holder.ciudad.setText(((Estudiante)getItem(position)).getCiudad());
        holder.codPos.setText(((Estudiante)getItem(position)).getCodigoPos());
        holder.telefono.setText(((Estudiante)getItem(position)).getTelefono());
        holder.email.setText(((Estudiante)getItem(position)).getEmail());
        return rootview;
    }

    public void start(ArrayList<Estudiante> estudianteArrayList) {
        clear();
        addAll(estudianteArrayList);
        this.estudiantesList.clear();
        this.estudiantesList.addAll(estudianteArrayList);
        notifyDataSetChanged();
    }


    public Estudiante getAt(int position){
        return this.estudiantesList.get(position);
    }


    public ArrayList<Estudiante> getAll(){
        return this.estudiantesList;
    }


}
