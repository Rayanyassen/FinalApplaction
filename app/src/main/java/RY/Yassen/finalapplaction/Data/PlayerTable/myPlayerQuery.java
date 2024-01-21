package RY.Yassen.finalapplaction.Data.PlayerTable;

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
public interface myPlayerQuery {

    /**
     *اعاده جميع معطيات جدول اللاعبين
     * @return *اعاده قائمه من المستخدمين
     */
@Query("Select * FROM myPlayer Where username=:name ")
    List <myPlayer> getUsername(String name);
    /**
     *  ادخال مهمات
     * @param t  مجموعه مهمات
     */
    @Insert
    void insertinfo(myPlayer...t); // ثلاثة نقاط تعني ادخال مجموعه

    /**
     * تعديل المعلومات
     * @param tasks مجموعه لتعديل المهمات (التعديل حسب المفتاح الرئيسي)
     */
    @Update
    void Updateinfo(myPlayer... tasks);

    /**
     * حذف  المعلومه
     * @param tasks حذف المعلومات (حسب المفتاح الرئيسي)
     */

    @Delete
    void deletTask(myPlayer... tasks);




}
