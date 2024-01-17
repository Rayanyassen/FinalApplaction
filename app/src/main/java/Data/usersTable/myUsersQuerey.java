package Data.usersTable;

import java.util.List;
/**
 * فئه تحوي عمليات \دوال \ استعلامات  على قاعدة البيانات
 */
@Dao//لتحديد ان الواجهه تحوي على استعلامات  على قاعدة البيانات
public interface myUsersQuerey
        @Query("SELECT * FROM myusers")
        List<myusers> getAll();

        @Query("SELECT * FROM  myusers WHERE keyid IN (:userIds)")
        List<myusers> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM myusers WHERE email = :myEmail AND " +
                "passw = :myPassw LIMIT 1")
        myusers checkEmailPassw(String myEmail, String myPassw);

        @Query("SELECT * FROM myusers WHERE email = :myEmail")
        myusers checkEmail(String myEmail);

        @Insert
        void insertAll(myusers... users);

        @Delete
        void delete(myusers user);

        @Query("Delete From myusers WHERE keyid=:id ")
        void delete(int id);

        @Insert
        void insert(myusers myUser);
        @Update
        void update(myusers...values);


}
