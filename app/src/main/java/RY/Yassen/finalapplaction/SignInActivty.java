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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import RY.Yassen.finalapplaction.Data.UsersTable.MyUsersQuery;
import RY.Yassen.finalapplaction.Data.UsersTable.myusers;

public class SignInActivty extends AppCompatActivity {
    private TextInputEditText Et_E_mail;
    private TextInputEditText Et_Password;
    private Button btn_SignUp;
    private Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activty);
        Et_E_mail=findViewById(R.id.Et_E_mail);
        Et_Password=findViewById(R.id.Et_Password);
        btn_SignUp=findViewById(R.id.btn_SignUp);
        btn_signIn=findViewById(R.id.btn_signIn);
    }
    // داله تفحص اذا الحقول صحيحه وسليمه
    private void checkEmailPassw_FB() {
        boolean isAllok = true; //يفحص الحقول ان كانت سليمة
        //استخراج النص من حقل الايميل
        String email = Et_E_mail.getText().toString();
        //استخراج نص كلمه المرور
        String password = Et_Password.getText().toString();


        //فحص الايمل ان كان طوله اقل من 6 او لا يحوي @ فهو خطأ
        if (email.length() < 6 || email.contains("@") == false) {
            //تعديل المتغير ليدل على ان الفحص يهطي نتيجه خاطئه
            isAllok = false;
            // عرض ملاحظه خطا على الشاشه داخل حقل البريد
            Et_E_mail.setError("Wrong Email");

        }
        if(password.length()< 8||password.contains(" ")==true)
        {
            isAllok=false;
            Et_Password.setError("Wrong Password");

        }
        //يفحص اذا الايميل و الباسورد موجود مسبقا
        if(isAllok){
            //עצם לביצוע רישום كائن لعملية التسجيل
            FirebaseAuth auth=FirebaseAuth.getInstance();
            //כניסה לחשבון בעזרת מיל ן סיסמא
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {//אם הפעולה הצליחה
                        Toast.makeText(SignInActivty.this, "Signing up Succeeded", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(SignInActivty.this, "Signing up failed", Toast.LENGTH_SHORT).show();
                        Et_E_mail.setError(task.getException().getMessage());//הצגת הודעת השגיאה שהקבלה מהענן
                    }
                }
            });
        }


    }


    // داله onclick ل sign in
    public void onclickbtn_signIn(View v)
    {
        checkEmailPassw_FB();
        Intent i = new Intent(SignInActivty.this, MainActivity.class);
        startActivity(i);
        //to close current activity
        finish();
    }
    // داله onclick ل sign up

    public void onclickbtn_SignUp(View v)
    {
        Intent i= new Intent(SignInActivty.this, SignUpActivty.class);
        startActivity(i);
        //to close current activity
        finish();
    }
}