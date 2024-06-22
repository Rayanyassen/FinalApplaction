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



        //קבלת הנתון (עצם) הנוכחי
        Skills currents = getItem(position);
        // sets the resource from the
        // videoUrl to the videoView
        videoskill.setVideoURI(Uri.parse(currents.getVideo()));
        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(getContext());

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoskill);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoskill);

        // sets the media controller to the videoView
        videoskill.setMediaController(mediaController);


        return vitem;
    }


}









