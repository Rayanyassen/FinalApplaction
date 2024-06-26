package RY.Yassen.finalapplaction.Data.UsersTable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Entity=table=جدول
//عندما نريد ان نتعامل مع هذه الفئه كجدول معطيات

/**
 * فئه تمثل المستعمل
 */
@Entity
public class myusers {
    @PrimaryKey(autoGenerate = true)
    public long keYid;//رقم الايدي لكل فيديو
    public String fullName; //اسم الكامل للشخص
    public String id;// رمز الايدي للحساب
    public String email;
    public String passw;

    public long getKeYid() {
        return keYid;
    }

    public void setKeYid(long keYid) {
        this.keYid = keYid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @Override
    public String toString() {
        return "myusers{" +
                "keYid=" + keYid +
                ", fullName='" + fullName + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}