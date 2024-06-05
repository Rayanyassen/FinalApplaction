package RY.Yassen.finalapplaction;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.PopupMenu;
import android.widget.SearchView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayer;

public class skillsactivty extends AppCompatActivity {
    private FloatingActionButton fabaddskills;
    private SearchView srchvskills;
    private Spinner spinerskills;
    private ListView listskills;
MyPlayer player=new MyPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        fabaddskills=findViewById(R.id.fabadd);
        fabaddskills.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent i=new Intent(skillsactivty.this,addskills.class);
                startActivity(i);
                finish();
            }
        });
        srchvskills=findViewById(R.id.srchS);
        spinerskills=findViewById(R.id.spnrskills);
        listskills=findViewById(R.id.lstvskills);

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
                if (menuitem.getItemId() == R.id.itmdelskill) {
                    Toast.makeText(skillsactivty.this, "Add", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(skillsactivty.this, addskills.class);
                    startActivity(i);
                }

                return true;
            }
        });
        popup.show();

    }



    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent()!=null&&getIntent().getExtras()!=null&&getIntent().getExtras().get("Player")!=null){
            player= (MyPlayer) getIntent().getExtras().get("Player");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.itmdelskill){
            onclickSkills();
        }
        return true;

    }


    public void onclickSkills() {
        Intent i = new Intent(skillsactivty.this, addskills.class);
        startActivity(i);
        finish();
    }

}