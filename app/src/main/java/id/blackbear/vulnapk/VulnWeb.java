package id.blackbear.vulnapk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class VulnWeb extends AppCompatActivity {

    private WebView webView;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.web_webview);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        StringBuilder content = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream("/data/data/id.blackbear.vulnapk/files/ip_settings");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();
            fis.close();
        } catch (IOException e) {
            Log.e("ip-addr", "Error reading file: " + e.getMessage());
        }

        webView.loadUrl(String.valueOf(content));
    }

    // Handle back button press
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
