package RY.Yassen.finalapplaction.Data.PlayerTable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyPlayer {
    @PrimaryKey(autoGenerate = true)
    long id;
    public String username;// اسم المستخدم
    public String firstName;// اسم الاول للاعب
    public String lastName; // اسم الاخير للاعب
    public String yourCity;// اسم المدينة
    public String phone ;
    private boolean areyouinClub; // هل انت في فريق حالي
    public long profile;//الملف الشخصي
    public String ShortTitlePlayer;
    public String TextPlayer;
    public String ImportancePlayer;

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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYourCity() {
        return yourCity;
    }

    public void setYourCity(String yourCity) {
        this.yourCity = yourCity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAreyouinClub() {
        return areyouinClub;
    }

    public void setAreyouinClub(boolean areyouinClub) {
        this.areyouinClub = areyouinClub;
    }

    public long getProfile() {
        return profile;
    }

    public void setProfile(long profile) {
        this.profile = profile;
    }

    public String getShortTitlePlayer() {
        return ShortTitlePlayer;
    }

    public void setShortTitlePlayer(String shortTitlePlayer) {
        ShortTitlePlayer = shortTitlePlayer;
    }

    public String getTextPlayer() {
        return TextPlayer;
    }

    public void setTextPlayer(String textPlayer) {
        TextPlayer = textPlayer;
    }

    public String getImportancePlayer() {
        return ImportancePlayer;
    }

    public void setImportancePlayer(String importancePlayer) {
        ImportancePlayer = importancePlayer;
    }
}
