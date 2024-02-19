package RY.Yassen.finalapplaction.Data.UsersTable;
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
    @PrimaryKey(autoGenerate = true)
    public long keYid;//رقم المهمه
    @ColumnInfo(name = "fullName")//اعطاء اسم جديد للعامود - الصفه في الجدول
    public String fullName;
    public String Id;
    public String email;// في حاله لم يتم اعطاء اسم للعامود يكون اسم الصفه هو اسم العامود
    public String phone;
    public String passw;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
        return "myusers{" +
                "Id='" + Id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}