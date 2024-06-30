package RY.Yassen.finalapplaction.Data.PlayerTable;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import android.media.MediaPlayer;
import android.net.Uri;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import RY.Yassen.finalapplaction.R;
import RY.Yassen.finalapplaction.skillsactivty;

public class MyskillsAdapter extends ArrayAdapter<Skills> {
    private final int itemLayout;

    /**
     * פעולה בונה מתאם
     *
     * @param context  קישור להקשר (מסך- אקטיביטי)
     * @param resource עיצוב של פריט שיציג הנתונים של העצם
     *                 تصميم قائمه لعرض المعطيات الكائن
     */

    public MyskillsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.itemLayout = resource;


    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vitem = convertView;
        if (vitem == null)
            vitem = LayoutInflater.from(getContext()).inflate(itemLayout, parent, false);

        //קבלת הפניות לרכיבים בקובץ העיצוב
        VideoView videoskill = vitem.findViewById(R.id.videoskills);
        ImageView imagplay = vitem.findViewById(R.id.imgplay);
        ImageView imagpuase = vitem.findViewById(R.id.imgpuase);
        ImageView imagstop = vitem.findViewById(R.id.imgstop);
        ImageView imaginfo = vitem.findViewById(R.id.imginfo);



        //קבלת הנתון (עצם) הנוכחי
        Skills currents = getItem(position);
        videoskill.setVideoURI(Uri.parse(currents.getVideo()));
        //to do addthreads
        // videoskill.seekTo(2);
        imagplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoskill.start();
            }
        });
        imagpuase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoskill.pause();
            }
        });
        imagstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoskill.stopPlayback();
            }
        });
        imaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v);
            }
        });

        return vitem;
    }

    public void showPopUpMenu(View p) {
        //popup menu بناء قائمه
        PopupMenu popup = new PopupMenu(getContext(), p);//الكائن الذي سبب فتح القائمه p
        //ملف القائمه
        popup.inflate(R.menu.popup_menu);
        //اضافه معالج حدث لاختيار عنصر من القائمه
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                if (menuitem.getItemId() == R.id.itmdelskill) {
                    Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getContext(), skillsactivty.class);

                    getContext().startActivity(i);
                }

                return true;
            }
        });
        popup.show();

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
     * הורדת הקובץ/התמונה לאחסון מיקומי של הטלפון והגתה על רכיב וִידֵאוֹ
     *
     * @param fileURL כתובת הקובץ באחסון הענן
     * @param toView  רכיב וִידֵאוֹ המיועד להצגת וִידֵאוֹ
     */
    private void downloadImageToLocalFile(String fileURL, final ImageView toView) {
        if (fileURL == null) return;// אם אין תמונה= כתובת ריקה אז לא עושים כלום מפסיקים את הפעולה
        // הפניה למיקום הקובץ באיחסון
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final File localFile;
        try {// יצירת קובץ מיקומי לפי שם וסוג קובץ
            localFile = File.createTempFile("Videos", "mp4");
            //הורדת הקובץ והוספת מאיזין שבודק אם ההורדה הצליחה או לא
            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Toast.makeText(getContext(), "downloaded video To Local File", Toast.LENGTH_SHORT).show();
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
     * הורדת קובץ/וִידֵאוֹ לזיכרון של הטלפון (לא לאחסון)
     * @param fileURL כתובת הקובץ באחסון הענן
     * @param toView רכיב וִידֵאוֹ המיועד להצגת וִידֵאוֹ
     */
    private void downloadImageToMemory(String fileURL, final ImageView toView)
    {
        if(fileURL==null)return;
        // הפניה למיקום הקובץ באיחסון
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final long ONE_MEGABYTE = 1024 * 1024;
        //הורדת הקובץ והוספת מאזין שבודק אם ההורדה הצליחה או לא
        httpsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


                toView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 90, 90, false));
                Toast.makeText(getContext(), "downloaded Image To Memory", Toast.LENGTH_SHORT).show();


            }
            //מאזין אם ההורדה נכשלה
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(getContext(), "onFailure downloaded Image To Local File "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                exception.printStackTrace();
            }
        });


    }



//    /**
//     * מחיקת פריט כולל התמונה מבסיס הנתונים
//     * @param myplayer הפריט שמוחקים
//     */
//    private void delMyPlayerFromDB_FB(MyPlayer myplayer )
//    {
//        //הפנייה/כתובת  הפריט שרוצים למחוק
//        FirebaseFirestore db= FirebaseFirestore.getInstance();
//
//
//        db.collection("Profile").document(myplayer.getUid()).
//                delete().//מאזין אם המחיקה בוצעה
//                addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful())
//                {
//                    remove(myplayer);// מוחקים מהמתאם
//                    deleteFile(myplayer.getImage());// מחיקת הקובץ
//                    Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
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

//
//        private MediaPlayer mediaPlayer;
//        private Uri videoUri;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//            // Get the VideoView from layout
//            VideoView videoView = findViewById(R.id.videoView);
//
//            // Set the URI for the video
//            videoUri = Uri.parse("your_video_uri_here");
//
//            // Set up MediaPlayer
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//            try {
//                mediaPlayer.setDataSource(getApplicationContext(), videoUri);
//                mediaPlayer.prepareAsync(); // Prepare asynchronously
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            // Set up event listeners
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    // Start playback when MediaPlayer is prepared
//                    mediaPlayer.start();
//                }
//            });
//
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    // Release MediaPlayer resources after playback completes
//                    mediaPlayer.release();
//                }
//            });
//        }
//
//        @Override
//        protected void onDestroy() {
//            super.onDestroy();
//            // Release MediaPlayer resources when activity is destroyed
//            if (mediaPlayer != null) {
//                mediaPlayer.release();
//                mediaPlayer = null;
//            }
//        }
//    }


}







