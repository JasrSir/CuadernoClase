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

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import jasrsir.com.cuadernoclasejasr.classes.Email;
import jasrsir.com.cuadernoclasejasr.classes.Estudiante;
import jasrsir.com.cuadernoclasejasr.classes.Result;
import jasrsir.com.cuadernoclasejasr.utils.RestClient;

public class EmailActivity extends AppCompatActivity implements View.OnClickListener{
        public static final String URL_API = "mail";
        public static final String MAIL = "mail";
        public static final int OK = 1;
    Estudiante estudiante;
        @BindView(R.id.from)
        EditText from;
        @BindView(R.id.password)
        EditText password;
        @BindView(R.id.to)
        EditText to;
        @BindView(R.id.subject)
        EditText subject;
        @BindView(R.id.message)
        EditText message;
        @BindView(R.id.accept)
        Button accept;
        @BindView(R.id.cancel)
        Button cancel;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_email);
            ButterKnife.bind(this);
            estudiante = getIntent().getParcelableExtra("estudiante");

            accept.setOnClickListener(this);
            cancel.setOnClickListener(this);
            to.setText(estudiante.getEmail());
        }

        @Override
        public void onClick(View v) {
            if (v == accept) {
                String f = from.getText().toString();
                String p = password.getText().toString();
                String t = to.getText().toString();
                String s = subject.getText().toString();
                String m = message.getText().toString();
                if (f.isEmpty() || p.isEmpty() || t.isEmpty() || s.isEmpty() || m.isEmpty()) {
                    Toast.makeText(this, "Rellena todos los campos",
                            Toast.LENGTH_LONG).show();
                } else {
                    Email email = new Email(f, p, t, s, m);
                    connection(email);
                }
            }
            if (v == cancel) {
                finish();
            }
        }

        private void connection(Email e) {
            final ProgressDialog progreso = new ProgressDialog(this);
            Gson gson = new Gson();
            RequestParams params = new RequestParams();
            //params.put(MAIL, gson.toJson(e));
            params.put("from", e.getFrom());
            params.put("pwd", e.getPassword());
            params.put("to", e.getTo());
            params.put("subject", e.getSubject());
            params.put("msg", e.getMessage());
            RestClient.post(URL_API, params, new JsonHttpResponseHandler() {
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
                            message = "Email sent ok";

                        } else
                            message = "Error sending the email:\n" + result.getMsg();
                    else
                        message = "Null data";
                    Toast.makeText(EmailActivity.this, message, Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    progreso.dismiss();
                    Snackbar.make(accept,"Error: "+ statusCode + responseString,Snackbar.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    progreso.dismiss();
                    Snackbar.make(accept,"Error: "+ statusCode,Snackbar.LENGTH_LONG).show();
                }
            });
        }


}
