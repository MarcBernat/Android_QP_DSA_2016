package marc.edu.upc.eetac.dsa.beeter_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.ws.rs.core.Form;

import marc.edu.upc.eetac.dsa.beeter_app.Beeter.BeeterClient;
import marc.edu.upc.eetac.dsa.beeter_app.Beeter.BeeterClientException;

/**
 * Created by Marc on 8/05/2016.
 */


public class StingCreateActivity extends AppCompatActivity {

    private CreateStingTask mCreateStingTask = null;
    private final static String TAG = LoginActivity.class.toString();

    EditText editTextSubject = null;
    EditText editTextContenido = null;
    Button bottonCreacion = null;


    class CreateStingTask extends AsyncTask<Void, Void, Boolean> {
        private Form form;

        public CreateStingTask(Form form) {
            this.form = form;

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean res = false;
            try
            {
                res = BeeterClient.getInstance().CreateSting(form);
            } catch (BeeterClientException e) {
                Log.d(TAG, e.getMessage());
            }
            return res;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if (result == true) {
                Intent i = getIntent();
                setResult(RESULT_OK, i);
                startActivity(new Intent(StingCreateActivity.this, StingsListActivity.class));
                finish();
            }

            if (result == false) {
                Intent i = getIntent();
                setResult(RESULT_CANCELED, i);
                finish();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sting_create);

        editTextSubject = (EditText)findViewById(R.id.editTextSubject);
        editTextContenido = (EditText)findViewById(R.id.editTextContenido);
        bottonCreacion = (Button)findViewById(R.id.BottonCreate);

        bottonCreacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                    String subject = editTextSubject.getText().toString();
                    String content = editTextContenido.getText().toString();
                    Form form = new Form();
                    form.param("subject", subject);
                    form.param("content", content);

                    // Execute AsyncTask
                    mCreateStingTask = new CreateStingTask(form);
                    mCreateStingTask.execute((Void) null);
            }
        });
    }

}
