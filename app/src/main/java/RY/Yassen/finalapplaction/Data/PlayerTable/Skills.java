package RY.Yassen.finalapplaction.Data.PlayerTable;

public class Skills {
public String uid;
public String id;
public String video;
public String text;


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

    public String getVideo() {
        return video;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "uid='" + uid + '\'' +
                ", id='" + id + '\'' +
                ", Video='" + video + '\'' +
                ", Text='" + text + '\'' +
                '}';
    }
}
