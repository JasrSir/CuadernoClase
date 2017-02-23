package jasrsir.com.cuadernoclasejasr;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jasrsir.com.cuadernoclasejasr.classes.AdapterControles_Add;
import jasrsir.com.cuadernoclasejasr.classes.AdapterEstudiantes;
import jasrsir.com.cuadernoclasejasr.classes.Estudiante;

public class FaltasHoy extends AppCompatActivity {
    ArrayList<Estudiante> estudiantes;
    private AdapterControles_Add mAdapterontroles;
    //private FloatingActionButton aniadir;
    private ListView mListFaltas;
    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltas_hoy);
        estudiantes =  getIntent().getParcelableArrayListExtra("estudiantes");
        mAdapterontroles = new AdapterControles_Add(this);
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        //aniadir = (FloatingActionButton) findViewById(R.id.fabaddcontrol);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Faltas del "+ smf.format(new Date()));
        mListFaltas = (ListView) findViewById(R.id.listCOntrols);

        mListFaltas.setAdapter(mAdapterontroles);
/*
        //aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterontroles.createControl();
            }
        });*/
    }
}
