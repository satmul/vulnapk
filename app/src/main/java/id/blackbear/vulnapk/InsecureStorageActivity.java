package id.blackbear.vulnapk;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class InsecureStorageActivity extends AppCompatActivity {

    Button submit;
    EditText name,secret;
    TextView result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecurestorage);

        submit = (Button)findViewById(R.id.insecure_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                name = (EditText)findViewById(R.id.insecure_name);
                secret = (EditText)findViewById(R.id.insecure_secret);
                result = (TextView)findViewById(R.id.insecure_result);
                String final_result = ("This is your secret!\n" + secret.getText().toString() + "\nBy: " + name.getText().toString());
                result.setText(final_result);
                Log.i("user_secret_info",final_result);

                try {
                    FileOutputStream fos = openFileOutput("mysecret", Context.MODE_PRIVATE);
                    fos.write(final_result.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Your secret is saved successfully in our very secure database!.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed to save your secret :((", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
