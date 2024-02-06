package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import RY.Yassen.finalapplaction.Data.PlayerTable.myPlayerQuery;
import RY.Yassen.finalapplaction.Data.UsersTable.MyUsersQuery;

public class Profile extends AppCompatActivity {
    private Button btnsavesignup;
    private Button btnCancelSignup;
    private Button btn_statistic;
    private TextInputEditText ET_FirstName;
    private TextInputEditText ET_LastName;
    private TextInputEditText ET_city;
    private TextInputEditText Et_phone;
    private AutoCompleteTextView autoetProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        autoetProfile=findViewById(R.id.autoEtProfile);
        btnsavesignup = findViewById(R.id.btnsavesignup);
        btnCancelSignup = findViewById(R.id.btnCancelSignup);
        btn_statistic = findViewById(R.id.btn_statistic);
        ET_FirstName = findViewById(R.id.ET_FirstName);
        ET_LastName = findViewById(R.id.ET_LastName);
        ET_city = findViewById(R.id.ET_city);
        Et_phone = findViewById(R.id.Et_phone);
    }

    private void checkEmailPassw() {
        boolean isAllok = true; //يفحص الحقول ان كانت سليمة
        // استخراج النص اسمك الاول
        String Firstname = ET_FirstName.getText().toString();
        // استخراج نص  اسمك الاخير
        String Lastname = ET_LastName.getText().toString();
        //استخراج نص اسم مدينتك
        String Cityname = ET_city.getText().toString();
        // استخراج نص من رقم الهاتف
        String phone = Et_phone.getText().toString();
        //فحص الايمل ان كان طوله اقل من 6 او لا يحوي @ فهو خطأ
        if (Firstname.length() > 6 || Firstname.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل  الملف الشخصي
            ET_FirstName.setError("Wrong FirstName");

        }
        if (Lastname.length() > 10 || Lastname.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_LastName.setError("Wrong LastName");
        }
        if (Cityname.length() > 10 || Cityname.contains(" ") == true) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_city.setError("Wrong LastName");
        }


        if (phone.length() > 10 || phone.contains(" ") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل الملف الشخصي
            ET_city.setError("Wrong LastName");


        }
        if (isAllok) {
            Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
            AppDataBase db = AppDataBase.getDB(getApplicationContext());
            myPlayerQuery playerQuery = db.getmyPlayerQuery();
            if (playerQuery.checkProfile(autoetProfile) == null) {


            }

        }
    }
}