package me.mortuza.dualserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import me.mortuza.truenetworkdetectorandroid.NetworkResult;
import me.mortuza.truenetworkdetectorandroid.TrueNetwork;

public class HomeActivity extends AppCompatActivity {
    String Host = "8.8.8.8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new TrueNetwork(this).detect(Host, new NetworkResult() {
            @Override
            public void result(boolean status) {
                Toast.makeText(HomeActivity.this, "" + status, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
