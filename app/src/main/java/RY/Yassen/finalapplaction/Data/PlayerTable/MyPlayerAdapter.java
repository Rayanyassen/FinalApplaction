package RY.Yassen.finalapplaction.Data.PlayerTable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import RY.Yassen.finalapplaction.R;

public class MyPlayerAdapter extends ArrayAdapter<MyPlayer>{
    private final int itemLayout;

    /**
     * פעולה בונה מתאם
     * @param context קישור להקשר (מסך- אקטיביטי)
     * @param resource עיצוב של פריט שיציג הנתונים של העצם
     * تصميم قائمه لعرض المعطيات الكائن
     */

    public MyPlayerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.itemLayout =resource;


    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vitem= convertView;
        if(vitem==null)
            vitem= LayoutInflater.from(getContext()).inflate(itemLayout,parent,false);
        //קבלת הפניות לרכיבים בקובץ העיצוב

        ImageView imageView=vitem.findViewById(R.id.imgVitm);
        TextView tvTitle=vitem.findViewById(R.id.tvItmTitle);
        TextView tvText=vitem.findViewById(R.id.tvItmText);
        TextView tvImportance=vitem.findViewById(R.id.tvItmImportance);
        ImageButton btnSendSMS=vitem.findViewById(R.id.imgBtnSendSmsitm);
        ImageButton btnEdit=vitem.findViewById(R.id.imgBtnEdititm);
        ImageButton btnCall=vitem.findViewById(R.id.imgBtnCallitm);
        ImageButton btnDel=vitem.findViewById(R.id.imgBtnDeleteitm);
        //קבלת הנתון (עצם) הנוכחי
        MyPlayer current=getItem(position);
        tvTitle.setText(current.getShortTitlePlayer());
        tvText.setText(current.getTextPlayer());
        tvImportance.setText("Importance:"+current.getImportancePlayer());

        return vitem;


    }
}




