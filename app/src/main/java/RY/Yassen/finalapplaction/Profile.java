package RY.Yassen.finalapplaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
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
import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayer;
import RY.Yassen.finalapplaction.Data.UsersTable.myusers;

public class Profile extends AppCompatActivity {
    public static final String PROFILE = "Profile";
    private Button btnsavesignup;
    private Button btnCancelSignup;
    private TextInputEditText ET_FirstName;
    private TextInputEditText ET_LastName;
    private TextInputEditText ET_city;
    private TextInputEditText Et_phone;
    private TextInputEditText ET_Username;
    private TextInputEditText ET_RUINCLUB;
    private AutoCompleteTextView autoetProfile;
    private final int IMAGE_PICK_CODE=100;// קוד מזהה לבקשת בחירת תמונה
    private final int PERMISSION_CODE=101;//קוד מזהה לבחירת הרשאת גישה לקבצים
    private ImageButton imgBtnl;//כפתור/ לחצן לבחירת תמונה והצגתה
    private Button btnUpload;// לחצן לביצוע העלאת התמונה
    private Uri toUploadimageUri;// כתוב הקובץ(תמונה) שרוצים להעלות
    private Uri downladuri;//כתובת הקוץ בענן אחרי ההעלא
    private myusers myusers;// עצם/נתון שרוצים לשמור
    private MyPlayer profiles=new MyPlayer();//עצם/נתון שרוצים לשמור


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        autoetProfile = findViewById(R.id.autoEtProfile);
        btnsavesignup = findViewById(R.id.btnsavesignup);
        btnCancelSignup= findViewById(R.id.btnCancel);
        ET_FirstName = findViewById(R.id.ET_FirstName);
        ET_LastName = findViewById(R.id.ET_LastName);
        ET_city = findViewById(R.id.ET_city);
        Et_phone = findViewById(R.id.Et_phone);
        ET_Username=findViewById(R.id.ET_Username);
        ET_RUINCLUB=findViewById(R.id.ET_RUINCLUB);
        imgBtnl=findViewById(R.id.imgBtnskills);
        imgBtnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();


            }
        });
    }
    /**
     * عملية  لاختيار صوره بمساعدة אינטנט מרומז: implicit intent
     */
    private void pickImageFromGallery(){
        //implicit intent (מרומז) to pick image
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);//הפעלתה האינטנט עם קוד הבקשה
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
            toUploadimageUri = data.getData();//קבלת כתובת התמונה הנתונים שניבחרו
            imgBtnl.setImageURI(toUploadimageUri);// הצגת התמונה שנבחרה על רכיב התמונה
        }
    }
    //داله تفحص اذا يوجد اذن للوصول للملفات بالتلفون
    private void checkPermission()
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
                pickImageFromGallery();
            }
        }
        else {//אם גרסה ישנה ולא צריך קבלת אישור
            pickImageFromGallery();
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
                pickImageFromGallery();
            } else {
                //permission was denied אם אין אישור
                Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onclickBTNcheckProfile(View V) {
        checkProfile();
    }


    public void onclickBTNcancel(View V) {
        Intent i = new Intent(Profile.this, MainActivity.class);
        startActivity(i);
        //to close current activity
        finish();
    }

    private void checkProfile() {
        boolean isAllok = true; //يفحص الحقول ان كانت سليمة
        // استخراج النص اسمك الاول
        String Firstname = ET_FirstName.getText().toString();
        // استخراج نص  اسمك الاخير
        String Lastname = ET_LastName.getText().toString();
        //استخراج نص اسم مدينتك
        String Cityname = ET_city.getText().toString();
        // استخراج نص من رقم الهاتف
        String phone = Et_phone.getText().toString();
        // استخراج اسم المستخدم
        String username= ET_Username.getText().toString();
        //استخراج هل انت موجود في فريق حالي ام لا
        boolean AreyouinClub=true;

        //فحص الاسم ان كان طوله اقل من حرفين او  يحوي فراغ فهو خطأ
        if (username.length() < 2 && username.contains(" ") == true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل  الملف الشخصي
            ET_Username.setError("Wrong username");

        }
        //فحص الاسم ان كان طوله اقل من 6 او  يحوي فراغ فهو خطأ
        if (Firstname.length() > 6 && Firstname.contains(" ") == true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل  الملف الشخصي
            ET_FirstName.setError("Wrong FirstName");

        }
        //فحص الاسم ان كان طوله اقل من 7 او  يحوي فراغ فهو خطأ
        if (Lastname.length() > 7 && Lastname.contains(" ") == true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_LastName.setError("Wrong LastName");
        }
        if (Cityname.length() > 4 && Cityname.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_city.setError("Wrong CityName");
        }
        if (phone.length() < 10 && phone.contains(" ") == true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            Et_phone.setError("Wrong phone number");

        }
        if (AreyouinClub==false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();

        }
        if(toUploadimageUri==null)
        {
            isAllok=true;
            Toast.makeText(this, "add Image", Toast.LENGTH_SHORT).show();
        }

        if(isAllok){
            //يتم تعبئه الكائن profiles بصفات التالية.
            profiles.setUsername(username);
            profiles.setFirstName(Firstname);
            profiles.setLastName(Lastname);
            profiles.setYourCity(Cityname);
            profiles.setAreyouinClub(AreyouinClub);
            profiles.setPhone(phone);
          uploadImage(toUploadimageUri);

        }

    }
// داله تقوم بحفظ الProfile

    private void SaveProfile_FB() {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //استخراج الرقم المميز للمستعمل الذي سجل الدخول لاستعماله كاسم لل دوكيومينت
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //بناء الكائن الذي سيتم حفظه

        profiles.setUid(uid);
        //اضافة كائن "لمجموعة" المستعملين ومعالج حدث لفحص   نجاح المطلوب
        // معالج حدث لفحص هل تم المطلوب من قاعدة البيانات
        db.collection(PROFILE).document(uid).set(profiles).addOnCompleteListener(new OnCompleteListener<Void>() {
            //داله معالجه الحدث
            @Override
            /**
             * عملية تقوم باستدعاء الfirestore لحفظ المعلومات بشكل مباشر
             */
            public void onComplete(@NonNull Task<Void> task) {
                // هل تم تنفيذ المطلوب بنجاح
                if (task.isSuccessful()) {
                    Toast.makeText(Profile.this, "Succeeded to Add profile", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Profile.this, MainActivity.class);
                    startActivity(i);




                } else {
                    Toast.makeText(Profile.this, "Failed to Add Profile", Toast.LENGTH_SHORT).show();

                }
            }
        });


        }

    private void uploadImage(Uri filePath) {
        if (filePath != null) {
            //יצירת דיאלוג התקדמות
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();//הצגת הדיאלוג
            //קבלת כתובת האחסון בענן
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            //יצירת תיקיה ושם גלובלי לקובץ
            final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
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
                                        profiles.setImage(downladuri.toString());//עדכון כתובת התמונה שהועלתה
                                        SaveProfile_FB();
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
            SaveProfile_FB();
        }


    }



}