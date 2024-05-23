package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class skills extends AppCompatActivity {
    private FloatingActionButton fabaddskills;
    private SearchView srchvskills;
    private Spinner spinerskills;
    private ListView listskills;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        fabaddskills=findViewById(R.id.fabadd);
        fabaddskills.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent i=new Intent(skills.this,addskills.class);
                startActivity(i);
                finish();
            }
        });
        srchvskills=findViewById(R.id.srchS);
        spinerskills=findViewById(R.id.spnrskills);
        listskills=findViewById(R.id.lstvskills);

    }

    /**
     * دالة مساعدة لفتح قائمه تتلقى
     * برمترا للكائن الذي سبب فتح القائمه
     * @param v
     */
    public void showPopUpMenu (View v){
        //popup menu بناء قائمه
        PopupMenu popup = new PopupMenu(this, v);//الكائن الذي سبب فتح القائمه v
        //ملف القائمه
        popup.inflate(R.menu.popup_menu);
        //اضافه معالج حدث لاختيار عنصر من القائمه
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                if (menuitem.getItemId() == R.id.itmProfile) {
                    Toast.makeText(skills.this, "Upload", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(skills.this, addskills.class);
                    startActivity(i);
                }
                return true;
            }
        });
        popup.show();
    }

}