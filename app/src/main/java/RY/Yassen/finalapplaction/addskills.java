package RY.Yassen.finalapplaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;


public class addskills extends AppCompatActivity {
    private ImageView uplvedio;
    private TextInputEditText ET_Text;
    private Button btnsaveskills;
    private Button btncancelskills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addskills);
        uplvedio=findViewById(R.id.uplvedio);
        ET_Text=findViewById(R.id.ET_Text);
        btnsaveskills=findViewById(R.id.btnsave);
        btncancelskills=findViewById(R.id.btncancel);

    }
}