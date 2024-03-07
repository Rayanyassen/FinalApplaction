package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override//بناء القائمه
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itmsetting) {
            Intent i = new Intent(MainActivity.this, SettingActivty.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itmLogout) {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(MainActivity.this, SignInActivty.class);
            finish();
        }
        if (item.getItemId() == R.id.itmDirectMessage) {
            Intent i = new Intent(MainActivity.this, Message.class);
            finish();
        }
        return true;
    }
        public void showPopUpMenu (View v){
            //popup menu بناء قائمه
            PopupMenu popup = new PopupMenu(this, v);//الكائن الذي سبب فتح القائمه v
            //ملف القائمه
            popup.inflate(R.menu.popup_menu);
            //اضافه معالج حدث لاختيار عنصر من القائمه
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuitem) {
                    if (menuitem.getItemId() == R.id.itmupload) {
                        Toast.makeText(MainActivity.this, "Upload", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, UploadVideo.class);
                        startActivity(i);
                    }
                    return true;
                }
            });
            popup.show();
        }
    }
