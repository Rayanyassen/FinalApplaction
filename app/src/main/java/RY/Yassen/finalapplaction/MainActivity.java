package RY.Yassen.finalapplaction;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayer;
import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayerAdapter;
import io.reactivex.annotations.NonNull;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabaddProfiles;
    private ListView lstProfile ;
    EditText SrchVprofile ;
    Spinner spinnerProfiles;
    ListView lstviewProfiles;
    MyPlayerAdapter myPlayerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstProfile=findViewById(R.id.lstvprofiles);
        SrchVprofile=findViewById(R.id.srchV);
        spinnerProfiles=findViewById(R.id.spnrProfiles);
        lstviewProfiles=findViewById(R.id.lstvprofiles);



        myPlayerAdapter = new MyPlayerAdapter(this,R.layout.myprofiles_item_layout);
        lstProfile.setAdapter(myPlayerAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        readTaskFrom_FB();

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

        return true;
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

    /**
     *  קריאת נתונים ממסד הנתונים firestore
     * @return .... רשימת הנתונים שנקראה ממסד הנתונים
     */
    public void readTaskFrom_FB()
    {
        //בניית רשימה ריקה
        ArrayList<MyPlayer> arrayList =new ArrayList<>();
        //קבלת הפנייה למסד הנתונים
        FirebaseFirestore ffRef = FirebaseFirestore.getInstance();
        //קישור לקבוצה לקבוצה שרוצים לקרוא
        ffRef.collection("Profile")
                //הוספת מאזין לקריאת הנתונים
                       .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    /**
                     * תגובה לאירוע השלמת קריאת הנתונים
                     * @param task הנתונים שהתקבלו מענן מסד הנתונים
                     */
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {// אם בקשת הנתונים התקבלה בהצלחה
                            //מעבר על כל ה״מסמכים״= עצמים והוספתם למבנה הנתונים
                            arrayList.clear();
                            myPlayerAdapter.clear();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                //המרת העצם לטיפוס שלו// הוספת העצם למבנה הנתונים
                                arrayList.add(document.toObject(MyPlayer.class));
                            }
                            myPlayerAdapter.addAll(arrayList);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error Reading data"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



















    }











