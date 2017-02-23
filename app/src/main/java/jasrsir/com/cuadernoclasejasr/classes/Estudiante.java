package jasrsir.com.cuadernoclasejasr.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by usuario on 9/02/17.
 */

public class Estudiante implements Parcelable {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String ciudad;
    private String codigoPos;
    private String telefono;
    private String email;
    private int id;
    private int faltasB;
    private int actitudB;
    private int trabajoB;
    public Estudiante(){
        this.trabajoB = 5;
        this.actitudB = 5;
        this.faltasB = 5;
    }

    public Estudiante(int id, String nombre, String apellidos, String direccion, String ciudad, String codigoPos, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPos = codigoPos;
        this.telefono = telefono;
        this.email = email;
        this.id = id;
        this.trabajoB = 5;
        this.actitudB = 5;
        this.faltasB = 5;
    }
    public Estudiante( String nombre, String apellidos, String direccion, String ciudad, String codigoPos, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPos = codigoPos;
        this.telefono = telefono;
        this.email = email;
        this.trabajoB = 5;
        this.actitudB = 5;
        this.faltasB = 5;
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPos() {
        return codigoPos;
    }

    public void setCodigoPos(String codigoPos) {
        this.codigoPos = codigoPos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getFaltasB() {
        return faltasB;
    }

    public void setFaltasB(int faltasB) {
        this.faltasB = faltasB;
    }

    public int getActitudB() {
        return actitudB;
    }

    public void setActitudB(int actitudB) {
        this.actitudB = actitudB;
    }

    public int getTrabajoB() {
        return trabajoB;
    }

    public void setTrabajoB(int trabajoB) {
        this.trabajoB = trabajoB;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellidos);
        dest.writeString(this.direccion);
        dest.writeString(this.ciudad);
        dest.writeString(this.codigoPos);
        dest.writeString(this.telefono);
        dest.writeString(this.email);
        dest.writeInt(this.id);
        dest.writeInt(this.faltasB);
        dest.writeInt(this.actitudB);
        dest.writeInt(this.trabajoB);
    }

    protected Estudiante(Parcel in) {
        this.nombre = in.readString();
        this.apellidos = in.readString();
        this.direccion = in.readString();
        this.ciudad = in.readString();
        this.codigoPos = in.readString();
        this.telefono = in.readString();
        this.email = in.readString();
        this.id = in.readInt();
        this.faltasB = in.readInt();
        this.actitudB = in.readInt();
        this.trabajoB = in.readInt();
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        @Override
        public Estudiante createFromParcel(Parcel source) {
            return new Estudiante(source);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };
}
