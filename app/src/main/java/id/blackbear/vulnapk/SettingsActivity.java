package id.blackbear.vulnapk;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {
    EditText ip;
    Button submit,delete;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        submit = (Button)findViewById(R.id.settings_save);
        delete = (Button)findViewById(R.id.settings_delete);
        ip = (EditText)findViewById(R.id.settings_ip);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput("ip_settings", Context.MODE_PRIVATE);
                    fos.write(ip.getText().toString().getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Saved successfully.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error occurred.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File file = new File("data/data/id.blackbear.vulnapk/files/ip_settings");

                if (file.exists()) {
                    boolean isDeleted = file.delete();

                    if (isDeleted) {
                        Toast.makeText(getApplicationContext(), "File deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error occurred.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "File doesn't exist.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

