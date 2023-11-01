package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.d(" ", "OnCreate:");
        Toast.makeText(this, "OnCreate:", Toast.LENGTH_SHORT).show();
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
        Log.d("yassen", "onRestart: ");
        Toast.makeText(this, "onRestart:",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yassen", "onResume: ");
        Toast.makeText(this, "onResume:",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("yassen", "onPause: ");
        Toast.makeText(this, "onPause:",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("yassen", "onStop: ");
        Toast.makeText(this, "onStop:",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("yassen", "onDestroy: ");
        Toast.makeText(this, "onDestroy:",Toast.LENGTH_SHORT).show();
    }
}