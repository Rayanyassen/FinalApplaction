package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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

    public void onclickbtn_SignUp(View v)
    {
        Intent i= new Intent(SignInActivty.this, SignUpActivty.class);
        startActivity(i);
        //to close current activity
        finish();
    }



    private void checkEmailPassw() {
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
        if(isAllok){

            //بناء قاعدة وارجاع مؤشر عليها1
            AppDataBase db=AppDataBase.getDB(getApplicationContext());
            //مؤشر لكائن عمليات الجدول2
            MyUsersQuery usersQuery=db.getmyUserQuery();
            //استدعاء العملية التي تنفذ الاستعلام الذي يفحص البريد و كلمة المرور ويعيد كائنا ان كان موجودا او ان لم يكن موجود null
            myusers myUser=usersQuery.checkEmailPassw(email,password);
            if(myUser==null)//هل لا يوجد كائن حسب الايميل والباسورد
            {
                Toast.makeText(this,"Wrong Email or Password ", Toast.LENGTH_LONG).show();

            }
            else
            {//ان كان هنالك حساب الايميل و الباسورد ننتقل الى الشاشه الرئيسية
                Toast.makeText(this,"All Ok" , Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SignInActivty.this,Profile.class);
                startActivity(i);
                finish();
            }
        }
    }
    public void onclickbtn_signIn(View v)
    {
        checkEmailPassw();


    }
}
