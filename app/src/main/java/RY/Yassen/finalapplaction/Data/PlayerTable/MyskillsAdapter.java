package RY.Yassen.finalapplaction.Data.PlayerTable;
import static android.Manifest.permission.CALL_PHONE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.PermissionChecker.checkSelfPermission;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;


import android.media.MediaPlayer;
import android.net.Uri;

import com.bumptech.glide.Glide;
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

import RY.Yassen.finalapplaction.MainActivity;
import RY.Yassen.finalapplaction.R;
import RY.Yassen.finalapplaction.skillsactivty;

public class MyskillsAdapter extends ArrayAdapter<Skills> {
    private final int itemLayout;
    private Dialog dialog;

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
        ImageView videoskill = vitem.findViewById(R.id.imgVdialog);
        ImageView imgVPlay = vitem.findViewById(R.id.ImgVplay);
        Skills item = getItem(position);
        downloadvideothumbnails(item.getVideo(),videoskill);
        imgVPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showvediodialog(item.getVideo());
            }
        });






        return vitem;
    }


    public void showvediodialog(String vedioUrl){

        if(dialog==null)
            dialog = new Dialog(getContext());



        dialog.setContentView(R.layout.dialog_layout);
        VideoView videoVdial=dialog.findViewById(R.id.videoVdialog);
        Button btncancelV=dialog.findViewById(R.id.btnCancelV);
        btncancelV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        videoVdial.setVideoURI(Uri.parse(vedioUrl));
        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(getContext());
        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoVdial);
        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoVdial);
        // sets the media controller to the videoView
        videoVdial.setMediaController(mediaController);
        dialog.show();

    }
    private void downloadvideothumbnails(String video, ImageView vifbtnmend){
        if(video==null||video.length()==0)
        {
            return;
        }
        Glide.with(getContext()).asBitmap()
                .load(Uri.parse(video))
                .into(vifbtnmend);
    }
}







