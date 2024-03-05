package RY.Yassen.finalapplaction.Data.PlayerTable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyPlayer {
    @PrimaryKey(autoGenerate = true)
    long id;
    public String username;// اسم المستخدم
    public String FirstName;// اسم الاول للاعب
    public String LastName; // اسم الاخير للاعب
    public String YourCity;// اسم المدينة
    public boolean AreyouinClub; // هل انت في فريق حالي
    public long Profile;//الملف الشخصي


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getYourCity() {
        return YourCity;
    }

    public void setYourCity(String yourCity) {
        YourCity = yourCity;
    }

    public boolean isAreyouinClub() {
        return AreyouinClub;
    }

    public void setAreyouinClub(boolean areyouinClub) {
        AreyouinClub = areyouinClub;
    }

    public long getProfile() {
        return Profile;
    }

    public void setProfile(long profile) {
        Profile = profile;
    }

    @Override
    public String toString() {
        return "MyPlayer{" +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", YourCity='" + YourCity + '\'' +
                ", AreyouinClub=" + AreyouinClub +
                ", Profile=" + Profile +
                '}';
    }
}
