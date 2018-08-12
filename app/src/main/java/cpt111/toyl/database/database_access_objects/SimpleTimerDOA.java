package cpt111.toyl.database.database_access_objects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import cpt111.toyl.database.entities.SimplerTimerEntity;

/*
This interface specifies the SQL queries and associates them to methods.
 */
@Dao
public interface SimpleTimerDOA
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(SimplerTimerEntity simplerTimerEntity);
	
	@Query("DELETE FROM simple_timer")
	void deleteAll();
	
	@Query("SELECT * FROM simple_timer ORDER BY timer_id ASC")
	LiveData<List<SimplerTimerEntity>> getAllTimers();
}
