package RY.Yassen.finalapplaction.Data.PlayerTable;

public class Skills {
public String uid;
public String id;
public String Video;
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

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
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
                ", Video='" + Video + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }
}
