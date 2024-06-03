package RY.Yassen.finalapplaction.Data.PlayerTable;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.PermissionChecker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import RY.Yassen.finalapplaction.R;

public class MyPlayerAdapter extends ArrayAdapter<MyPlayer> {
    private final int itemLayout;
    MyPlayer player=new MyPlayer();

    /**
     * פעולה בונה מתאם
     *
     * @param context  קישור להקשר (מסך- אקטיביטי)
     * @param resource עיצוב של פריט שיציג הנתונים של העצם
     *                 تصميم قائمه لعرض المعطيات الكائن
     */

    public MyPlayerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.itemLayout = resource;


    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vitem = convertView;
        if (vitem == null)
            vitem = LayoutInflater.from(getContext()).inflate(itemLayout, parent, false);
        //קבלת הפניות לרכיבים בקובץ העיצוב

        ImageView imageView = vitem.findViewById(R.id.imgVitm);
        TextView tvTitle = vitem.findViewById(R.id.tvItmName);
        TextView tvText = vitem.findViewById(R.id.tvItmText);
        TextView tvImportance = vitem.findViewById(R.id.tvItmImportance);
        ImageButton btnSendSMS = vitem.findViewById(R.id.imgBtnSendSmsitm);
        ImageButton btnEdit = vitem.findViewById(R.id.imgBtnEdititm);
        ImageButton btnCall = vitem.findViewById(R.id.imgBtnCallitm);
        ImageButton btnDel = vitem.findViewById(R.id.imgBtnDeleteitm);
        //קבלת הנתון (עצם) הנוכחי
        MyPlayer current = getItem(position);
        tvTitle.setText(current.getShortTitlePlayer());
        tvText.setText(current.getTextPlayer());
        tvImportance.setText("Importance:" + current.getImportancePlayer());

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendSmsApp(current.getTextPlayer(),current.phone);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPhoneNumber(current.phone);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delMyPlayerFromDB_FB( player);

            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSendWhatsAppV2(current.getTextPlayer(),current.getPhone());
            }
        });
        downloadImageUsingPicasso(current.getImage(),imageView);



        return vitem;
    }

    /**
     * פתיחת אפליקצית שליחת whatsapp
     *
     * @param msg   .. ההודעה שרוצים לשלוח
     * @param phone
     */
    public void openSendWhatsAppV2(String msg, String phone) {
        //אינטנט מרומז לפתיחת אפליקצית ההודות סמס
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        ;
        String url = null;
        try {
            url = "https://api.whatsapp.com/send?phone=" + phone + "&text=" + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            Toast.makeText(getContext(), "there is no whatsapp!!", Toast.LENGTH_SHORT).show();
        }
        sendIntent.setData(Uri.parse(url));
        sendIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        sendIntent.addCategory(Intent.CATEGORY_DEFAULT);
        //פתיחת אפליקציית ה סמס
        getContext().startActivity(sendIntent);
    }


    /**
     * הצגת תמונה ישירות מהענן בעזרת המחלקה ״פיקאסו״
     *
     * @param imageUrL כתובת התמונה בענן/שרת
     * @param toView   רכיב תמונה המיועד להצגת התמונה אחרי ההורדה
     */
    private void downloadImageUsingPicasso(String imageUrL, ImageView toView) {
        // אם אין תמונה= כתובת ריקה אז לא עושים כלום מפסיקים את הפעולה
        if (imageUrL == null) return;

        Picasso.with(getContext())
                .load(imageUrL)//הורדת התמונה לפי כתובת
                .centerCrop()
                .error(R.drawable.ic_launcher_background)//התמונה שמוצגת אם יש בעיה בהורדת התמונה
                .resize(90, 90)//שינוי גודל התמונה
                .into(toView);// להציג בריכיב התמונה המיועד לתמונה זו
    }

    /**
     * הורדת הקובץ/התמונה לאחסון מיקומי של הטלפון והגתה על רכיב תמונה
     *
     * @param fileURL כתובת הקובץ באחסון הענן
     * @param toView  רכיב התמונה המיועד להצגת התמונה
     */
    private void downloadImageToLocalFile(String fileURL, final ImageView toView) {
        if (fileURL == null) return;// אם אין תמונה= כתובת ריקה אז לא עושים כלום מפסיקים את הפעולה
        // הפניה למיקום הקובץ באיחסון
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final File localFile;
        try {// יצירת קובץ מיקומי לפי שם וסוג קובץ
            localFile = File.createTempFile("images", "jpg");
            //הורדת הקובץ והוספת מאיזין שבודק אם ההורדה הצליחה או לא
            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Toast.makeText(getContext(), "downloaded Image To Local File", Toast.LENGTH_SHORT).show();
                    toView.setImageURI(Uri.fromFile(localFile));
                }
                //מאזין אם ההורדה נכשלה
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Toast.makeText(getContext(), "onFailure downloaded Image To Local File " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    exception.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * פתיחת אפליקצית שליחת sms
     *
     * @param msg   .. ההודעה שרוצים לשלוח
     * @param phone
     */
    public void openSendSmsApp(String msg, String phone) {
        //אינטנט מרומז לפתיחת אפליקצית ההודות סמס
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        //מעבירים מספר הטלפון
        smsIntent.setData(Uri.parse("smsto:" + phone));
        //ההודעה שנרצה שתופיע באפליקצית ה סמס
        smsIntent.putExtra("sms_body", msg);
        smsIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        //פתיחת אפליקציית ה סמס
        getContext().startActivity(smsIntent);
    }

    /**
     * @param phone מספר טלפון שרוצים להתקשר אליו
     */
    private void callAPhoneNumber(String phone) {
        //בדיקה אם יש הרשאה לביצוע שיחה
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//בדיקת גרסאות
            //בדיקה אם ההרשאה לא אושרה בעבר
            if (checkSelfPermission(getContext(), CALL_PHONE) == PermissionChecker.PERMISSION_DENIED) {
                //רשימת ההרשאות שרוצים לבקש אישור
                String[] permissions = {CALL_PHONE};
                //בקשת אישור הרשאות (שולחים קוד הבקשה)
                //התשובה תתקבל בפעולה onRequestPermissionsResult
                requestPermissions((Activity) getContext(), permissions, 100);
            } else {
                //אינטנט מרומז לפתיחת אפליקצית ההודות סמס
                Intent phone_intent = new Intent(Intent.ACTION_CALL);
                phone_intent.setData(Uri.parse("tel:" + phone));
                getContext().startActivity(phone_intent);


            }
        }
    }
    /**
     * מחיקת פריט כולל התמונה מבסיס הנתונים
     * @param myplayer הפריט שמוחקים
     */
    private void delMyPlayerFromDB_FB(MyPlayer myplayer )
    {
        //הפנייה/כתובת  הפריט שרוצים למחוק
        FirebaseFirestore db= FirebaseFirestore.getInstance();


        db.collection("Profile").document(myplayer.getUid()).
                delete().//מאזין אם המחיקה בוצעה
                addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    remove(myplayer);// מוחקים מהמתאם
                    deleteFile(myplayer.getImage());// מחיקת הקובץ
                    Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * מחיקת קובץ האיחסון הענן
     * @param fileURL כתובת הקובץ המיועד למחיקה
     */
    private void deleteFile(String fileURL)
    {
        // אם אין תמונה= כתובת ריקה אז לא עושים כלום מפסיקים את הפעולה
        if(fileURL==null){
            Toast.makeText(getContext(), "Theres no file to delete!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        // הפניה למיקום הקובץ באיחסון
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        //מחיקת הקובץ והוספת מאזין שבודק אם ההורדה הצליחה או לא
        storageReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "file deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "onFailure: did not delete file " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

}







