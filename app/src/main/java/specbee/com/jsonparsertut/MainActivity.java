package specbee.com.jsonparsertut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    String mJson;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        try {
            InputStream inputStream = getAssets().open("contacts.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
            mJson = stringBuilder.toString();
            JSONObject jsonObject = new JSONObject(mJson);
            JSONArray contacts = jsonObject.getJSONArray("contacts");
            for (int i = 0; i < contacts.length(); i++) {
                textView.setText(textView.getText().toString()+"\nId : "+contacts.getJSONObject(i).getString("id"));
                textView.setText(textView.getText().toString()+"\nName : "+contacts.getJSONObject(i).getString("name"));
                textView.setText(textView.getText().toString()+"\nEmail : "+contacts.getJSONObject(i).getString("email"));
                textView.setText(textView.getText().toString()+"\nAddress : "+contacts.getJSONObject(i).getString("address"));
                textView.setText(textView.getText().toString()+"\nGender : "+contacts.getJSONObject(i).getString("gender"));
                textView.setText(textView.getText().toString()+"\n\nMobile : "+contacts.getJSONObject(i).getJSONObject("phone").getString("mobile"));
                textView.setText(textView.getText().toString()+"\nHome : "+contacts.getJSONObject(i).getJSONObject("phone").getString("home"));
                textView.setText(textView.getText().toString()+"\nOffice : "+contacts.getJSONObject(i).getJSONObject("phone").getString("office"));


            }
        } catch (Throwable e) {
            Log.e("Error Occured", e.getMessage() + "");
        }
    }
}
