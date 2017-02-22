package jasrsir.com.cuadernoclasejasr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jasrsir.com.cuadernoclasejasr.classes.Estudiante;
import jasrsir.com.cuadernoclasejasr.classes.Result;
import jasrsir.com.cuadernoclasejasr.utils.RestClient;

public class CreatModifEstud_Activity extends AppCompatActivity {
    public static final String URL_API="estudiante";
    public static final int OK = 1;

    private Estudiante estudiante;
    private int id;
    private EditText nombre;
    private EditText apellidos;
    private EditText direccion;
    private EditText provincia;
    private EditText codPos;
    private EditText telefono;
    private EditText email;
    private Button aceptar;
    private Button cancel;
    private boolean hayEstudiante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_modif_estud);
        nombre = (EditText) findViewById(R.id.edtnombreEst);
        hayEstudiante = false;
        apellidos = (EditText) findViewById(R.id.edtapellidEst);
        direccion = (EditText) findViewById(R.id.edtdireccionEst);
        provincia = (EditText) findViewById(R.id.edtprovinciaEst);
        codPos = (EditText) findViewById(R.id.edtCodPosEst);
        telefono = (EditText) findViewById(R.id.edtTelEst);
        email = (EditText) findViewById(R.id.edtEmailEst);
        aceptar = (Button) findViewById(R.id.accept);
        cancel = (Button) findViewById(R.id.cancel);
        if ((estudiante = (Estudiante) getIntent().getSerializableExtra("estudiante")) != null) {
            setEstudiante(estudiante);
            hayEstudiante = true;
        }
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void comprobar() {
        if (nombre.getText().toString().isEmpty()||apellidos.getText().toString().isEmpty()||
                direccion.getText().toString().isEmpty()||provincia.getText().toString().isEmpty()||
                codPos.getText().toString().isEmpty()||telefono.getText().toString().isEmpty()||email.getText().toString().isEmpty()){
            Toast.makeText(this,"No puede haber campos vacíos",Toast.LENGTH_LONG).show();
        } else if (hayEstudiante){
            Estudiante e = new Estudiante(id, nombre.getText().toString(),apellidos.getText().toString(),direccion.getText().toString(),
                    provincia.getText().toString(),codPos.getText().toString(),telefono.getText().toString(), email.getText().toString() );
            modifyEstudiante(e);
        } else {
            Estudiante e = new Estudiante( nombre.getText().toString(),apellidos.getText().toString(),direccion.getText().toString(),
                    provincia.getText().toString(),codPos.getText().toString(),telefono.getText().toString(), email.getText().toString() );
            modifyEstudiante(e);
        }
    }

    private void modifyEstudiante(final Estudiante e) {
        final ProgressDialog progreso = new ProgressDialog(this);
        Gson gson = new Gson();
        RequestParams params = new RequestParams();

        params.put("apellidos", e.getApellidos());
        params.put("nombre", e.getNombre());
        params.put("direccion", e.getDireccion());
        params.put("ciudad",e.getCiudad());
        params.put("codigoPos", e.getCodigoPos());
        params.put("telefono", e.getTelefono());
        params.put("email", e.getEmail());
        if (hayEstudiante) {
            params.put("id", id);
            RestClient.put(URL_API +"/" +id, params, new JsonHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progreso.setMessage("Modificando Estudiante . . .");
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
                    //Site site;
                    result = gson.fromJson(String.valueOf(response), Result.class);
                    if (result != null)
                        if (result.getEstado()) {
                            message = "";
                            //site = gson.fromJson(String.valueOf(result.getSites()), Site.class);
                            Intent i = new Intent();
                            i.putExtra("estudiante", e);
                            setResult(OK, i);
                            finish();
                        } else
                            message = "Error modifying the estudiante:\n" + result.getMsgError();
                    else
                        message = "Null data";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    progreso.dismiss();
                    Snackbar.make(nombre, "Error: " + statusCode + responseString, Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    progreso.dismiss();
                    Snackbar.make(nombre, "Error: " + statusCode, Snackbar.LENGTH_LONG).show();
                }
            });
        } else {
            RestClient.post(URL_API, params, new JsonHttpResponseHandler() {
                @Override
                public void onStart() {
                    super.onStart();
                    progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progreso.setMessage("Guardando Estudiante . . .");
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
                    //Site site;
                    result = gson.fromJson(String.valueOf(response), Result.class);
                    if (result != null)
                        if (result.getEstado()) {
                            message = "Modified estudiante ok";
                            //site = gson.fromJson(String.valueOf(result.getSites()), Site.class);
                            Intent i = new Intent();
                            i.putExtra("estudiante", e);
                            setResult(OK, i);
                            finishActivity(OK);
                        } else
                            message = "Error modifying the site:\n" + result.getMsgError();
                    else
                        message = "Null data";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    progreso.dismiss();
                    Snackbar.make(nombre, "Error: " + statusCode + responseString, Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    progreso.dismiss();
                    Snackbar.make(nombre, "Error: " + statusCode, Snackbar.LENGTH_LONG).show();
                }
            });
        }

    }


    private void setEstudiante(Estudiante estudiante) {
        nombre.setText(estudiante.getNombre());
        apellidos.setText(estudiante.getApellidos());
        direccion.setText(estudiante.getDireccion());
        provincia.setText(estudiante.getCiudad());
        codPos.setText(estudiante.getCodigoPos());
        telefono.setText(estudiante.getTelefono());
        email.setText(estudiante.getEmail());
        id = estudiante.getId();
    }

}
