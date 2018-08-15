package cpt111.toyl.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
					DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							ToylDatabase.class,"toyl_database")
							.fallbackToDestructiveMigration()
							.addCallback(sRoomDatabaseCallback)
							.build();
				}
			}
		}
		return DB_INSTANCE;
	}

	/**
	 * Override the onOpen method to populate the database.
	 * For this sample, we clear the database every time it is created or opened.
	 */
	private static ToylDatabase.Callback sRoomDatabaseCallback = new ToylDatabase.Callback(){

		@Override
		public void onOpen (@NonNull SupportSQLiteDatabase db){
			super.onOpen(db);
			// If you want to keep the data through app restarts,
			// comment out the following line.
			//new PopulateDbAsync(DB_INSTANCE).execute();
		}
	};

	/**
	 * Populate the database in the background.
	 * If you want to start with more words, just add them.
	 */
	private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

		private final SimpleTimerDOA mDao;

		PopulateDbAsync(ToylDatabase db) {
			mDao = db.simpleTimerDOA();
		}

		@Override
		protected Void doInBackground(final Void... params) {
			// Start the app with a clean database every time.
			// Not needed if you only populate on creation.
			mDao.deleteAll();
			SimplerTimerEntity timer = new SimplerTimerEntity("Test timer 1",1000);
			mDao.insert(timer);
			timer = new SimplerTimerEntity("Test timer 2", 5000);
			mDao.insert(timer);
			return null;
		}
	}



}
