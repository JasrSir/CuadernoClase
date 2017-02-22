package jasrsir.com.cuadernoclasejasr;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jasrsir.com.cuadernoclasejasr.classes.AdapterEstudiantes;
import jasrsir.com.cuadernoclasejasr.classes.Estudiante;
import jasrsir.com.cuadernoclasejasr.classes.Result;
import jasrsir.com.cuadernoclasejasr.utils.RestClient;

public class ActivityPrincipal extends AppCompatActivity  {
    public static final String URL_API="estudiante";
    public static final String MAIL="mail";
    public static final int ADD_CODE=100;
    public static final int UPDATE_CODE=200;
    public static final int OK=1;

    private Toolbar toolbar;
    private ListView mListEstudiantes;
    private FloatingActionButton mFabAdd;
    private AdapterEstudiantes mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        downloadEstudiantes();
        mFabAdd = (FloatingActionButton) findViewById(R.id.fabAddAlum);
        mListEstudiantes = (ListView) findViewById(R.id.expListAlumnos);;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de Estudiantes");
        mListAdapter = new AdapterEstudiantes(this);

        mListEstudiantes.setAdapter(mListAdapter);

        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPrincipal.this, CreatModifEstud_Activity.class));
            }
        });


        mListEstudiantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopup(view,position);
                return false;
            }
        });
        mListEstudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ActivityPrincipal.this, ControlEstudianteActivity.class);
                i.putExtra("estudiante", mListAdapter.getAt(position));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.goto_calendar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent i = new Intent(ActivityPrincipal.this, FaltasHoy.class);
        i.putExtra("estudiantes", mListAdapter.getAll());
        startActivity(i);
        return true;
    }

    private void downloadEstudiantes() {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(URL_API, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Connecting . . .");
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
                        mListAdapter.start(result.getEstudiantes());
                    } else
                        message = result.getMsgError();
                else
                    message = "Null data";
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progreso.dismiss();
                Snackbar.make(mFabAdd,"Error: "+ statusCode + responseString,Snackbar.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Snackbar.make(mFabAdd,"Error: "+ statusCode,Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void showPopup(View v, final int position) {
        PopupMenu popup = new PopupMenu(this, v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(R.menu.estudiantesecond, popup.getMenu());
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.updateEstudiante:
                        modify(mListAdapter.getAt(position));
                        //positionClicked = position;
                        return true;
                    case R.id.deleteEstudiante:
                        delete(mListAdapter.getAt(position));
                        return true;
                    case R.id.emailEstudiante:
                        //confirm(mListAdapter.getAt(position).getId(), mListAdapter.getAt(position).getNombre(), position);
                        return true;
                    default:
                        return false;
                }
            }
        });
        // Show the menu
        popup.show();
    }

    private void modify(Estudiante s) {
        Intent i = new Intent(this, CreatModifEstud_Activity.class);
        i.putExtra("estudiante", s);
        startActivityForResult(i, UPDATE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == OK)
            downloadEstudiantes();

    }

    @Override
    protected void onStart() {
        super.onStart();
        downloadEstudiantes();
    }
     private void delete(final Estudiante e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(e.getNombre()+" "+e.getApellidos() + "\n¿Quieres borrar al estudiante?")
                .setTitle("Delete")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        connection(e);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void connection(Estudiante e) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.delete(URL_API + "/" + e.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Borrando Estudiante . . .");
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
                        message = "Alumno Borrado";
                        downloadEstudiantes();
                    } else
                        message = "Error borrando al alumno\nEstado: " + result.getCode() + "\n" +
                                result.getMsg();
                else
                    message = "Null data";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
            // onFailure
        });
    }
}
