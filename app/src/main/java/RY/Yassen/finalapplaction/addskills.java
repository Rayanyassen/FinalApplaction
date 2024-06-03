package RY.Yassen.finalapplaction;

import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_MEDIA_VIDEO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import RY.Yassen.finalapplaction.Data.PlayerTable.Skills;
import RY.Yassen.finalapplaction.Data.UsersTable.myusers;


public class addskills extends AppCompatActivity {
    private TextInputEditText ET_Text;
    private Button btnsaveskills;
    private Button btncancelskills;
    private final int IMAGE_PICK_CODE = 100;// קוד מזהה לבקשת בחירת תמונה
    private final int PERMISSION_CODE = 101;//קוד מזהה לבחירת הרשאת גישה לקבצים
    private Uri toUploadVideoUri;// כתוב הקובץ(תמונה) שרוצים להעלות
    private Uri downladuri;//כתובת הקוץ בענן אחרי ההעלא
    private myusers myusers;// עצם/נתון שרוצים לשמור
    private ImageView uplvedio;//כפתור/ לחצן לבחירת תמונה והצגתה
    private VideoView videoview;
    private Skills skillss = new Skills();//עצם/נתון שרוצים לשמור


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addskills);
        uplvedio = findViewById(R.id.uplvedio);
        ET_Text = findViewById(R.id.ET_Text);
        btnsaveskills = findViewById(R.id.btnsave);
        btncancelskills = findViewById(R.id.btncancel);
        videoview = findViewById(R.id.videoView);
        uplvedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPickvideoPermission();


            }
        });
        btnsaveskills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checknewskills();
            }
        });
    }
    private void SaveSkills_FB(){
//مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //استخراج الرقم المميز للمستعمل الذي سجل الدخول لاستعماله كاسم لل دوكيومينت
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //بناء الكائن الذي سيتم حفظه
        skillss.setUid(uid);
        //اضافة كائن "لمجموعة" المستعملين ومعالج حدث لفحص   نجاح المطلوب
        // معالج حدث لفحص هل تم المطلوب من قاعدة البيانات
        db.collection("Skills").document(uid).set(skillss).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(addskills.this, "Succeeded to Add skill", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(addskills.this, MainActivity.class);
                    startActivity(i);


                } else {
                    Toast.makeText(addskills.this, "Failed to Add skill", Toast.LENGTH_SHORT).show();


                }
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
            skillss.setText(Text);
            uploadVideo(toUploadVideoUri);
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
            videoview.seekTo(2);
        }
    }
    //داله تفحص اذا يوجد اذن للوصول للملفات بالتلفون
    private void checkPickvideoPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//בדיקת גרסאות
            //בדיקה אם ההשאה לא אושרה בעבר
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED &&
                    checkSelfPermission(READ_MEDIA_VIDEO) == PackageManager.PERMISSION_DENIED&&
                    checkSelfPermission(READ_MEDIA_IMAGES) == PackageManager.PERMISSION_DENIED) {
                //רשימת ההרשאות שרוצים לבקש אישור
                String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE,READ_MEDIA_VIDEO,READ_MEDIA_IMAGES};

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

    private void uploadVideo(Uri filePath) {
        if (filePath != null) {
            //יצירת דיאלוג התקדמות
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();//הצגת הדיאלוג
            //קבלת כתובת האחסון בענן
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            //יצירת תיקיה ושם גלובלי לקובץ
            final StorageReference ref = storageReference.child("Video/" + UUID.randomUUID().toString());
            // יצירת ״תהליך מקביל״ להעלאת תמונה
            ref.putFile(filePath)
                    //הוספת מאזין למצב ההעלאה
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();// הסתרת הדיאלוג
                                //קבלת כתובת הקובץ שהועלה
                                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        downladuri = task.getResult();
                                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                                        skillss.setVideo(downladuri.toString());//עדכון כתובת התמונה שהועלתה
                                        //استعمال داله لحفظ الskills
                                        SaveSkills_FB();
                                    }
                                });
                            } else {
                                progressDialog.dismiss();//הסתרת הדיאלוג
                                Toast.makeText(getApplicationContext(), "Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    //הוספת מאזין שמציג מהו אחוז ההעלאה
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //חישוב מה הגודל שהועלה
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        } else {

            SaveSkills_FB();
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
