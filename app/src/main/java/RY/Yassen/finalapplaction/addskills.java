package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputEditText;

import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayer;
import RY.Yassen.finalapplaction.Data.UsersTable.myusers;


public class addskills extends AppCompatActivity {
    private TextInputEditText ET_Text;
    private Button btnsaveskills;
    private Button btncancelskills;
    private final int IMAGE_PICK_CODE=100;// קוד מזהה לבקשת בחירת תמונה
    private final int PERMISSION_CODE=101;//קוד מזהה לבחירת הרשאת גישה לקבצים
    private Uri toUploadVideoUri;// כתוב הקובץ(תמונה) שרוצים להעלות
    private Uri downladuri;//כתובת הקוץ בענן אחרי ההעלא
    private MyPlayer player;//עצם/נתון שרוצים לשמור
    private myusers myusers;// עצם/נתון שרוצים לשמור
    private ImageView uplvedio;//כפתור/ לחצן לבחירת תמונה והצגתה
    private VideoView videoview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addskills);
        uplvedio = findViewById(R.id.uplvedio);
        ET_Text = findViewById(R.id.ET_Text);
        btnsaveskills = findViewById(R.id.btnsave);
        btncancelskills = findViewById(R.id.btncancel);
        videoview=findViewById(R.id.videoView);
        uplvedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPickvideoPermission();


            }
        });
    }


    private void checknewskills(){
        boolean isAllOK = true;
        String Text=ET_Text.getText().toString();
        if(  Text.length()<4  ){

            isAllOK = false;
            ET_Text.setError("Wrong text");

        }
        if (isAllOK){

            Toast.makeText(this, "The video has been downloaded", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(addskills.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }
    /**
     * عملية  لاختيار صوره بمساعدة אינטנט מרומז: implicit intent
     */
    private void pickVideoFromGallery(){
        // Initialize intent
        Intent intent=new Intent(Intent.ACTION_PICK);
        // set type
        intent.setType("video/*");
        // start activity result
        startActivityForResult(Intent.createChooser(intent,"Select Video"),IMAGE_PICK_CODE);//הפעלתה האינטנט עם קוד הבקשה

    }
    /**
     * بعد اختيار الصوره بمساعده StartActivtyForResult نرجع للدالة onActivityResult التي تعتبر من حياة الاكتفتي لهذه العملية نحصل على اختيارنا
     * @param requestCode מספר הקשה
     * @param resultCode תוצאה הבקשה (אם נבחר משהו או בוטלה)
     * @param data הנתונים שנבחרו
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //אם נבחר משהו ואם זה קוד בקשת התמונה
        if (resultCode==RESULT_OK && requestCode== IMAGE_PICK_CODE){
            //a עידכון תכונת כתובת התמונה
            toUploadVideoUri = data.getData();//קבלת כתובת התמונה הנתונים שניבחרו
            videoview.setVideoURI(toUploadVideoUri);// הצגת התמונה שנבחרה על רכיב התמונה
        }
    }
    //داله تفحص اذا يوجد اذن للوصول للملفات بالتلفون
    private void checkPickvideoPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//בדיקת גרסאות
            //בדיקה אם ההשאה לא אושרה בעבר
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //רשימת ההרשאות שרוצים לבקש אישור
                String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
                //בקשת אישור ההשאות (שולחים קוד הבקשה)
                //התשובה תתקבל בפעולה onRequestPermissionsResult
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                //permission already granted אם יש הרשאה מקודם אז מפעילים בחירת תמונה מהטלפון
                pickVideoFromGallery();
            }
        }
        else {//אם גרסה ישנה ולא צריך קבלת אישור
            pickVideoFromGallery();
        }
    }
    /**
     * @param requestCode The request code passed in מספר בקשת ההרשאה
     * @param permissions The requested permissions. Never null. רשימת ההרשאות לאישור
     * @param grantResults The grant results for the corresponding permissions תוצאה עבור כל הרשאה
     *   PERMISSION_GRANTED אושר or PERMISSION_DENIED נדחה . Never null.
     */
    @Override
    /**
     *    نحصل على اجابه  طلب الاذن بواسطة requestPermissions من الدالة onRequestPermissionsResult
     */
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_CODE) {//בדיקת קוד בקשת ההרשאה
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission was granted אם יש אישור
                pickVideoFromGallery();
            } else {
                //permission was denied אם אין אישור
                Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
            }
        }

    }




    public void onclickSAVE(View v) {
        checknewskills();

    }

    public void onclickCancel(View v) {
        Intent i = new Intent(addskills.this, skills.class);
        startActivity(i);
        finish();
    }
}
