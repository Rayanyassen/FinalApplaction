package RY.Yassen.finalapplaction.Data.UsersTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * فئه تحوي عمليات \دوال \ استعلامات  على قاعدة البيانات
 */
@Dao//لتحديد ان الواجهه تحوي على استعلامات  على قاعدة البيانات
public interface MyUsersQuery {
    /**
     * يقوم باعاده جميع المستخدمين من الجدول myusers
     * @return
     */
    @Query("SELECT * FROM myusers")
    List<myusers> getAll();

    /**
     * يقوم باعاده  وفحص جميع المستخدمين حسب الkeyid
     * @param userIds
     * @return
     */
    @Query("SELECT * FROM  myusers WHERE keyid IN (:userIds)")
    List<myusers> loadAllByIds(int[] userIds);
    /**
     *  يقوم باعاده وفحص جميع حقول الايميلات و الباسورد حسب الصفات myemail , mypass
     * @param myEmail
     * @param myPassw
     * @return
     */
    @Query("SELECT * FROM myusers WHERE email = :myEmail AND " +
            "passw = :myPassw LIMIT 1")
    myusers checkEmailPassw(String myEmail, String myPassw);

    /**
     * يقوم باعاده وفحص جميع الحقول الايميلات حسب الصفات myemail
     * @param myEmail
     * @return
     */

    @Query("SELECT * FROM myusers WHERE email = :myEmail")
    myusers checkEmail(String myEmail);

    /**
     * ادخال مستخدمين
     * @param users مجموعه مستخدمين
     */

    @Insert
    void insertAll(myusers... users);

    /**
     *  يقوم بحذف المستخدم من جدول المستخدمين
     * @param user
     */

    @Delete
    void delete(myusers user);

    /**
     * يقوم بحذف المستخدمين حسب الايدي
     * @param id
     */

    @Query("Delete From myusers WHERE keyid=:id ")
    void delete(int id);

    /**
     * يقوم بادخال معلومات للمستخدمين
     * @param myUser
     */
    @Insert
    void insert(myusers myUser);

    /**
     * يقوم بتعديل على المعلومات في الجدول  myusers
     * @param values
     */
    @Update
    void update(myusers...values);


}