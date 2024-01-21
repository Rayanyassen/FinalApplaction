package RY.Yassen.finalapplaction;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import RY.Yassen.finalapplaction.Data.UsersTable.MyUsersQuery;
import RY.Yassen.finalapplaction.Data.UsersTable.myusers;


/*
تعريف الجداول ورقم الاصدار
version
عند تغيير اي شي يخص جدول او جداول علينا تغيير رقم الاصدار
ليتم بناء قاعدة البيانات من جديد
*/
@Database(entities = {myusers.class},version =1)
/**
 * الفئة المسؤولة عن بناء قاعدة البايانات بكل جداولها
 * وتوفر لنا كائن للتعامل مع قاعدة البيانات
 */

public abstract class AppDataBase extends RoomDatabase
{

    /**
     * كائن للتعامل مع قاعدة البيانات
     */
    private static AppDataBase db;

    /**
     * يعيد كائن لعمليات جدول المستعملين
     * @return
     */
    public abstract MyUsersQuery getmyUserQuery();

    /**
     * بناء قاعدة البيانات واعادة كائن يؤشر عليها
     * @param context
     * @return
     */
    public static AppDataBase getDB(Context context)
    {
        if(db==null)
        {
            db = Room.databaseBuilder(context,
                            AppDataBase.class,
                            "footballDataBase")//اسم قاعدة اليانات
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }

}

