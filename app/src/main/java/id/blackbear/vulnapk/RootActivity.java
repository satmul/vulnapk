package id.blackbear.vulnapk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.scottyab.rootbeer.RootBeer;

public class RootActivity extends AppCompatActivity {
    TextView status, hook;

    public String checkStatus(String status){
        if(status.equals("Admin")){
            return "yay";
        }
        return "nay";
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rootdetection);

        status = (TextView)findViewById(R.id.root_status);
        hook = (TextView)findViewById(R.id.hook_status);
        String stats = "NormalUser";

        RootBeer rootBeer = new RootBeer(this);
        if (rootBeer.isRooted()) {
            status.setText("STATUS: ROOT DETECTED!!!!!");
        } else {
            status.setText("STATUS: ROOT IS NOT DETECTED.");
        }

        if (checkStatus(stats).equals("yay")) {
            hook.setText("STATUS: YOU ARE ADMIN!");
        } else  {
            hook.setText("STATUS: WHO ARE YOU ?!");

        }


    }
}
