package Data.UsersTable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Entity=table=جدول
//عندما نريد ان نتعامل مع هذه الفئه كجدول معطيات

/**
 * فئه تمثل المستعمل
 */
@Entity
public class myusers {
    @PrimaryKey(autoGenerate = true)//تحديد الصفه كمفتاح رئيسي والذي ينتج بشكل تلقائي
    public long keyid;
    @ColumnInfo(name = "fullName")//اعطاء اسم جديد للعامود - الصفه في الجدول

    public String fullName;
    public String email;// في حاله لم يتم اعطاء اسم للعامود يكون اسم الصفه هو اسم العامود
    public String phone;
    public String passw;

    public long getKeyid() {
        return keyid;
    }

    public void setKeyid(long keyid) {
        this.keyid = keyid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "keyid=" + keyid +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }

}