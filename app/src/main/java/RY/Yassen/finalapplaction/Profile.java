package RY.Yassen.finalapplaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import RY.Yassen.finalapplaction.Data.PlayerTable.MyPlayer;
import RY.Yassen.finalapplaction.Data.PlayerTable.myPlayerQuery;

public class Profile extends AppCompatActivity {
    private Button btnsavesignup;
    private Button btnCancelSignup;
    private TextInputEditText ET_FirstName;
    private TextInputEditText ET_LastName;
    private TextInputEditText ET_city;
    private TextInputEditText Et_phone;
    private TextInputEditText ET_Username;
    private TextInputEditText ET_RUINCLUB;
    private AutoCompleteTextView autoetProfile;



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
        if (username.length() < 2 || username.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل  الملف الشخصي
            ET_Username.setError("Wrong username");

        }
        //فحص الاسم ان كان طوله اقل من 6 او  يحوي فراغ فهو خطأ
        if (Firstname.length() > 6 || Firstname.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل  الملف الشخصي
            ET_FirstName.setError("Wrong FirstName");

        }
        //فحص الاسم ان كان طوله اقل من 7 او  يحوي فراغ فهو خطأ
        if (Lastname.length() > 7 || Lastname.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_LastName.setError("Wrong LastName");
        }
        if (Cityname.length() > 4 || Cityname.contains(" ") == true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_city.setError("Wrong CityName");
        }
        if (phone.length() != 10 || phone.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            Et_phone.setError("Wrong phone number");

        }
        if (AreyouinClub==true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = true;

        }

        if(isAllok){
            //استعمال داله Saveprofile
            SaveProfile_FB(username,Firstname,Lastname,Cityname,AreyouinClub, phone);

        }

    }


    private void SaveProfile_FB(String username, String firstName, String lastName, String yourCity, boolean areyouinClub,String phone ) {
        //مؤشر لقاعدة البيانات
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //استخراج الرقم المميز للمستعمل الذي سجل الدخول لاستعماله كاسم لل دوكيومينت
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //بناء الكائن الذي سيتم حفظه
        MyPlayer profiles = new MyPlayer();
        profiles.setUsername(username);
        profiles.setFirstName(firstName);
        profiles.setLastName(lastName);
        profiles.setYourCity(yourCity);
        profiles.setAreyouinClub(areyouinClub);
        profiles.setPhone(phone);
        //اضافة كائن "لمجموعة" المستعملين ومعالج حدث لفحص   نجاح المطلوب
        // معالج حدث لفحص هل تم المطلوب من قاعدة البيانات
        db.collection("Profile").document(uid).set(profiles).addOnCompleteListener(new OnCompleteListener<Void>() {
            //داله معالجه الحدث
            @Override
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

    public void onclickBTNcheckProfile(View V) {
        checkProfile();
    }


    public void onclickBTNcancel(View V) {
        Intent i = new Intent(Profile.this, MainActivity.class);
        startActivity(i);
        //to close current activity
        finish();
    }
}