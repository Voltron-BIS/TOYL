package cpt111.toyl.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import cpt111.toyl.database.database_access_objects.SimpleTimerDOA;
import cpt111.toyl.database.entities.SimplerTimerEntity;

@Database(entities = {SimplerTimerEntity.class}, version = 1)
public abstract class ToylDatabase extends RoomDatabase
{
	public abstract SimpleTimerDOA simpleTimerDOA();
	private static ToylDatabase DB_INSTANCE;
	
	public static ToylDatabase getDbInstance(final Context context)
	{
		if (DB_INSTANCE == null) {
			synchronized (ToylDatabase.class) {
				if (DB_INSTANCE == null) {
					DB_INSTANCE = Room.databaseBuilder(
							context.getApplicationContext(),
							ToylDatabase.class,
							"toyl_database").build();
				}
			}
		}
		return DB_INSTANCE;
	}
}
