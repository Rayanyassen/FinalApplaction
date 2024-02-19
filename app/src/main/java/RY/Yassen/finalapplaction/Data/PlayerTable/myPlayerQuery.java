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
@Query("Select * FROM MyPlayer Where username=:name ")
    List <MyPlayer> getUsername(String name);
    /**
     *  ادخال معلومات الشخصيه
     * @param p  مجموعه معلومات
     */
    @Insert
    void insertinfo(MyPlayer...p); // ثلاثة نقاط تعني ادخال مجموعه

    /**
     * تعديل الملف الشخصي
     * @param privateinfo مجموعه لتعديل المعلومات الشخصي (التعديل حسب المفتاح الرئيسي)
     */
    @Update
    void Updateinfo(MyPlayer... privateinfo);

    /**
     * حذف  المعلومه التي في الملف الشخصي
     * @param privateinfo حذف المعلومات (حسب المفتاح الرئيسي)
     */

    @Delete
    void deleteinfo(MyPlayer... privateinfo);

    /**
     * يفحص اذا كان هناك ملف شخصي اخر مشابه له
     * @param autoetPro
     * @return
     */
//    @Query("SELECT * FROM MyPlayer WHERE Profile=:autoetPro")
//    MyPlayer checkProfile(AutoCompleteTextView autoetPro);





}
