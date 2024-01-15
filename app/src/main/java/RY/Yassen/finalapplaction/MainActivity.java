package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showPopUpMenu(View v) {
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
                    ;
                    Intent i = new Intent(MainActivity.this, UploadVideo.class);
                    startActivity(i);
                }
//                if (menuitem.getItemId()==R.id.itmedit){
//                    Toast.makeText(MainActivity.this,"Delete",Toast.LENGTH_SHORT).show();
//                    MainActivity db= MainActivity.getDB(MainActivity2.this);
//                    MyTasksQuery myTasksQuery=db.getMyTaskQuery();
//                    myTasksQuery.deletTask(menuitem.getItemId());
//                    initiAlllistView();
//                    initSubjectSpnr();
//                }
                if (menuitem.getItemId() == R.id.itmedit) {
                    Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        popup.show();
    }
}