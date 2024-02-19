package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
private TextView ET_Wlcm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.d(" ", "");
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        //بناء قاعدة بيانات وارجاع مؤشر عليها 1

        Handler h=new Handler();
        Runnable r=new Runnable() {

            public void run()
            {
                //to open new activity from current to next
                Intent i= new Intent(SplashScreen.this, SignInActivty.class);
                startActivity(i);
                //to close current activity
                finish();
            }
        };
        h.postDelayed(r,3000);
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}