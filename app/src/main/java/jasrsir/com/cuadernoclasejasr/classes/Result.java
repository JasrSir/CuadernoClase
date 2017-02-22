package jasrsir.com.cuadernoclasejasr.classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by usuario on 13/02/17.
 */

public class Result implements Serializable {
    int code;
    boolean estado;
    String msg;
    String msgError;
    ArrayList<Estudiante> estudiantes;
    Estudiante estudiante;
    Control control;
    ArrayList<Control> controls;
    int lasId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public ArrayList<Control> getControls() {
        return controls;
    }

    public void setControls(ArrayList<Control> controls) {
        this.controls = controls;
    }

    public int getLasId() {
        return lasId;
    }

    public void setLasId(int lasId) {
        this.lasId = lasId;
    }
}
