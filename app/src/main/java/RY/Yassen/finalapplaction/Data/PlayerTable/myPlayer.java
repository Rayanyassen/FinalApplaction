package RY.Yassen.finalapplaction.Data.PlayerTable;

import androidx.room.Entity;

@Entity
public class myPlayer {
    public String username;// اسم المستخدم
    public String FirstName;// اسم الاول للاعب
    public String LastName; // اسم الاخير للاعب
    public String YourCity;// اسم المدينة
    public boolean AreyouinClub; // هل انت في فريق حالي
    public long StatisticPlayer; // احصائيات اللاعب
    public long Profile;//الملف الشخصي


    public long getProfile() {
        return Profile;
    }

    public void setProfile(long profile) {
        Profile = profile;
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

    public long getStatisticPlayer() {
        return StatisticPlayer;
    }

    public void setStatisticPlayer(long statisticPlayer) {
        StatisticPlayer = statisticPlayer;
    }

    @Override
    public String toString() {
        return "myPlayer{" +
                "username='" + username + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", YourCity='" + YourCity + '\'' +
                ", AreyouinClub=" + AreyouinClub +
                ", StatisticPlayer=" + StatisticPlayer +
                ", Profile=" + Profile +
                '}';
    }
}
