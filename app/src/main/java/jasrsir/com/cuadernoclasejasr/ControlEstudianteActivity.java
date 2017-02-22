package jasrsir.com.cuadernoclasejasr;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jasrsir.com.cuadernoclasejasr.classes.AdapterControles;
import jasrsir.com.cuadernoclasejasr.classes.Estudiante;
import jasrsir.com.cuadernoclasejasr.classes.Result;
import jasrsir.com.cuadernoclasejasr.utils.RestClient;

public class ControlEstudianteActivity extends AppCompatActivity {

    private static final String URL_API ="control/" ;
    private ListView mListControl;
    private String mId;
    private AdapterControles mAdapterCOntrol;
    private Estudiante estudiante;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        estudiante = getIntent().getParcelableExtra("estudiante");
        setContentView(R.layout.activity_control_estudiante);
        mListControl = (ListView) findViewById(R.id.expListcontrol);
        toolbar = (Toolbar) findViewById(R.id.toolbarcontrol);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faltas de: " + estudiante.getNombre()+ " " + estudiante.getApellidos());
        downloadControles();

    }




    private void downloadControles() {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(URL_API+estudiante.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Descargando Faltas . . .");
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
                        mAdapterCOntrol = new AdapterControles(ControlEstudianteActivity.this,result.getControls());
                        mListControl.setAdapter(mAdapterCOntrol);
                        message = "Connection OK";
                    } else
                        message = result.getMsgError();
                else
                    message = "Null data";
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

}
