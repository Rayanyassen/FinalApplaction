package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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



        if (item.getItemId() == R.id.itmLogout) {
            ShowNoYesDialog();
        }
        if (item.getItemId() == R.id.itmDirectMessage) {
            Intent i = new Intent(MainActivity.this, Message.class);
            startActivity(i);
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

    public void ShowNoYesDialog()
    {
        //تجهيز بناء شباك حوار "ديالوغ" يتلقى بارمتر مؤشر للنشاط (اكتفيتي) الحالي
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Log out");// تحديد العنوان
        builder.setMessage("Are you sure?");//تحديد فحوى الشباك الحوار
        //النص على الزر ومعالج الحدث
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //معالجه الحدث للموافقه
                Toast.makeText(MainActivity.this,"Signing out",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, SignInActivty.class);
                startActivity(i);
                FirebaseAuth.getInstance().signOut();


            }
        });
        AlertDialog dialog= builder.create();// بناء شباك حوار - ديالوغ
        dialog.show();//عرض الشباك
    }

    }
