package RY.Yassen.finalapplaction.Data.PlayerTable;

import androidx.room.Dao;
import androidx.room.Query;

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



}
