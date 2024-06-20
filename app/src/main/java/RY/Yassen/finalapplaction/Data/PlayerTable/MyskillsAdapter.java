package RY.Yassen.finalapplaction.Data.PlayerTable;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
        VideoView videoskill=vitem.findViewById(R.id.videoskills);
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






//to do media play video
}







