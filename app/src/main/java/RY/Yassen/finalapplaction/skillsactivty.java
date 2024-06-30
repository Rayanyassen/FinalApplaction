package RY.Yassen.finalapplaction;


import static RY.Yassen.finalapplaction.Profile.PROFILE;
import static RY.Yassen.finalapplaction.addskills.SKILLS;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayer;
import RY.Yassen.finalapplaction.Data.PlayerTable.MyskillsAdapter;
import RY.Yassen.finalapplaction.Data.PlayerTable.Skills;
import io.reactivex.annotations.NonNull;

public class skillsactivty extends AppCompatActivity {
    private FloatingActionButton fabaddskills;
    private SearchView srchvskills;
    private Spinner spinerskills;
    private ListView listskills;
    MyskillsAdapter myskillsAdapter;
    MyPlayer player = new MyPlayer();
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        fabaddskills = findViewById(R.id.fabadd);
        fabaddskills.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent i = new Intent(skillsactivty.this, addskills.class);
                startActivity(i);
                finish();
            }
        });
        srchvskills = findViewById(R.id.srchS);
        spinerskills = findViewById(R.id.spnrskills);
        myskillsAdapter = new MyskillsAdapter(this, R.layout.skills_item_layout);
        listskills = findViewById(R.id.lstvskills);
        listskills.setAdapter(myskillsAdapter);

    }






    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().get("Player") != null) {
            player = (MyPlayer) getIntent().getExtras().get("Player");
            readSkillsFrom_FB();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        return true;

    }






    /**
     * קריאת נתונים ממסד הנתונים firestore
     *
     * @return .... רשימת הנתונים שנקראה ממסד הנתונים
     */
    public void readSkillsFrom_FB() {
        //בניית רשימה ריקה
        ArrayList<Skills> arrayList = new ArrayList<>();
        //קבלת הפנייה למסד הנתונים
        FirebaseFirestore ffRef = FirebaseFirestore.getInstance();
        //קישור לקבוצה לקבוצה שרוצים לקרוא
        ffRef.collection(PROFILE).document(player.getUid()).collection(SKILLS)
                //הוספת מאזין לקריאת הנתונים
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    /**
                     * תגובה לאירוע השלמת קריאת הנתונים
                     * @param task הנתונים שהתקבלו מענן מסד הנתונים
                     */
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {// אם בקשת הנתונים התקבלה בהצלחה
                            //מעבר על כל ה״מסמכים״= עצמים והוספתם למבנה הנתונים
                            arrayList.clear();
                            myskillsAdapter.clear();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                //המרת העצם לטיפוס שלו// הוספת העצם למבנה הנתונים
                                arrayList.add(document.toObject(Skills.class));
                            }
                            myskillsAdapter.addAll(arrayList);
                        } else {
                            Toast.makeText(skillsactivty.this, "Error Reading data" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}