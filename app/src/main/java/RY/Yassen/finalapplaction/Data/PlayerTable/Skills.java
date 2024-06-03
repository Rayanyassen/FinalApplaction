package RY.Yassen.finalapplaction.Data.PlayerTable;

import android.net.Uri;

public class Skills {
public String uid;
public String id;
public String video;
public String Text;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setVideo(String video) {
        this.video = video;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "uid='" + uid + '\'' +
                ", id='" + id + '\'' +
                ", Video='" + video + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }
}
