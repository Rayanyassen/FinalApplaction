package RY.Yassen.finalapplaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import RY.Yassen.finalapplaction.Data.UsersTable.MyUsersQuery;
import RY.Yassen.finalapplaction.Data.UsersTable.myusers;


public class SignUpActivty extends AppCompatActivity {
    private TextInputEditText Et_emailsignup;
    private TextInputEditText ETpassword;
    private TextInputEditText ETrepassword;

    private Button btnsavesignup;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activty);
        Et_emailsignup = findViewById(R.id.ET_FirstName);
        ETpassword = findViewById(R.id.ET_LastName);
        ETrepassword = findViewById(R.id.ET_city);

        btnsavesignup = findViewById(R.id.btnsavesignup);
        btnCancel = findViewById(R.id.btnCancelSignup);
    }
// داله تفحص اذاالحقول سليمه
    public void checkAndSignUP_FB() {


        boolean isAllok = true; //يفحص الحقول ان كانت سليمة
        //استخراج النص من حقل الايميل
        String email = Et_emailsignup.getText().toString();
        //استخراج نص كلمه المرور
        String password = ETpassword.getText().toString();
        //استخراج نص اعادة كلمه المرور
        String repassword = ETrepassword.getText().toString();


        //فحص الايمل ان كان طوله اقل من 6 او لا يحوي @ فهو خطأ
        if (email.length() < 6 || email.contains("@") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل البريد
            Et_emailsignup.setError("Wrong Email");

        }
        if (password.length() <= 8 || password.length() >= 20 || password.contains(" ") == true) {
            isAllok = false;
            ETpassword.setError("Password between 8 - 20 letters");
        }
        if (!repassword.equals(password)) {
            isAllok = false;
            ETrepassword.setError("should be the same password");
        }


        //تفحص اذا تسجيل الدخول كان ناجحا ام لا
        if(isAllok){

            // עצם לביצוע רישום كائن لعملية التسجيل
            FirebaseAuth auth=FirebaseAuth.getInstance();
            // יצירת חשבון בעזרת  מיל וסיסמא
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {//הפרמטר מכיל מהרשמת על תוצאת הבקשה לרישום
                    if (task.isSuccessful()) {//אם הפעולה הצליחה
                        Toast.makeText(SignUpActivty.this, "Signing up Succeeded", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivty.this, SignInActivty.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(SignUpActivty.this, "Signing up failed", Toast.LENGTH_SHORT).show();
                        Et_emailsignup.setError(task.getException().getMessage());//הצגת הודעת השגיאה שהקבלה מהענן
                    }
                }
            });
        }
    }


    public void onclickBTNSAVE(View V) {
        checkAndSignUP_FB();

    }

    public void onclickBTNCancel(View v) {
        Intent i = new Intent(SignUpActivty.this, SignInActivty.class);
        startActivity(i);
        //to close current activity
        finish();
    }

}