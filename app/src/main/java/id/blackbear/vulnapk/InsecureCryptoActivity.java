package id.blackbear.vulnapk;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InsecureCryptoActivity extends AppCompatActivity {

    Button submit;
    EditText secret;
    TextView result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insecurecrypto);

        submit = (Button)findViewById(R.id.crypto_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                secret = (EditText) findViewById(R.id.crypto_secret_input);
                result = (TextView) findViewById(R.id.crypto_result);
                String encrypted = null;
                String hashed = null;


                try {
                    encrypted = CryptoUtil.encrypt(secret.getText().toString());
                    MessageDigest messagedigest = MessageDigest.getInstance("MD5");
                    String input = secret.getText().toString();
                    byte[] hashBytes = messagedigest.digest(input.getBytes());
                    StringBuilder stringbuilder = new StringBuilder();
                    for (byte b : hashBytes) {
                        stringbuilder.append(String.format("%02x", b));
                    }
                    hashed = stringbuilder.toString();
                    Toast.makeText(getApplicationContext(), "Encrypted Successfully!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "An Error Has Occurred", Toast.LENGTH_SHORT).show();
                }

                String final_result = ("This is your encrypted secret!\n" + encrypted + "And your hashed secret is: " + hashed);
                result.setText(final_result);
            }
        });

    }
}