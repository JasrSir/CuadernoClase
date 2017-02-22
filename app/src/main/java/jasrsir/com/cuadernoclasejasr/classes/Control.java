package jasrsir.com.cuadernoclasejasr.classes;

import java.util.Date;

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
public class Control {



    private int idEstudiante;
    private String falta;
    private String actitud;
    private String trabajo;
    private String observacion;
    private String fecha;

    public Control(int idEstudiante, String falta, String actitud,String trabajo, String observacion, String fecha) {
        this.idEstudiante = idEstudiante;
        this.falta = falta;
        this.actitud = actitud;
        this.trabajo = trabajo;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public Control() {
        this.idEstudiante = 0;
        this.falta = null;
        this.actitud = null;
        this.trabajo = null;
        this.observacion = null;
        this.fecha = null;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getFalta() {
        return falta;
    }

    public void setFalta(String falta) {
        this.falta = falta;
    }

    public String getActitud() {
        return actitud;
    }

    public void setActitud(String actitud) {
        this.actitud = actitud;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
