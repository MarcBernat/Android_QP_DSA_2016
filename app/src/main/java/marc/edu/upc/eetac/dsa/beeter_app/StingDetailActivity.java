package marc.edu.upc.eetac.dsa.beeter_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import marc.edu.upc.eetac.dsa.beeter_app.Beeter.BeeterClient;
import marc.edu.upc.eetac.dsa.beeter_app.Beeter.BeeterClientException;
import marc.edu.upc.eetac.dsa.beeter_app.Beeter.entity.Sting;

/**
 * Created by Marc on 8/05/2016.
 */

public class StingDetailActivity extends AppCompatActivity {
GetStingTask mGetStingTask = null;
    String uri = null;
    String userid = null;
    String subject = null;
    Long creation = null;
    String screation = null;
    private final static String TAG = StingDetailActivity.class.toString();
    TextView textViewUserid = null;
    TextView textViewSubject = null;
    TextView textViewCreation = null;

    class GetStingTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetStingTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonSting = null;
            try {
                jsonSting = BeeterClient.getInstance().getSting(uri);
            } catch (BeeterClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonSting;
        }

        @Override
        protected void onPostExecute(String jsonSting) {
            Log.d(TAG, jsonSting);
            Sting sting = (new Gson()).fromJson(jsonSting, Sting.class);
            userid = sting.getUserid();
            subject = sting.getSubject();
            creation = sting.getCreationTimestamp();
            screation = String.valueOf(creation);
            textViewUserid.setText(userid);
            textViewSubject.setText(subject);
            textViewCreation.setText(screation);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sting_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewUserid = (TextView) findViewById(R.id.textViewUserid);
        textViewSubject = (TextView) findViewById(R.id.textViewSubject);
        textViewCreation = (TextView) findViewById(R.id.textViewCreation);

        // Execute AsyncTask
        mGetStingTask = new GetStingTask(uri);
        mGetStingTask.execute((Void) null);


    }
}
