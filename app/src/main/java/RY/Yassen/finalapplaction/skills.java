package RY.Yassen.finalapplaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.itmAddskills){
            onclickSkills();
        }
        return true;

    }


    public void onclickSkills() {
        Intent i = new Intent(skills.this, addskills.class);
        startActivity(i);
        finish();
    }

}