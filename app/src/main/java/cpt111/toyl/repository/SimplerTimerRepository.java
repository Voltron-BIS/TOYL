package cpt111.toyl.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import cpt111.toyl.database.ToylDatabase;
import cpt111.toyl.database.database_access_objects.SimpleTimerDOA;
import cpt111.toyl.database.entities.SimplerTimerEntity;

public class SimplerTimerRepository
{
	private SimpleTimerDOA simpleTimerDOA;
	private LiveData<List<SimplerTimerEntity>> allSimpleTimerEntities;
	
	SimplerTimerRepository(Application application)
	{
		ToylDatabase toylDatabase = ToylDatabase.getDbInstance(application);
		simpleTimerDOA = toylDatabase.simpleTimerDOA();
		allSimpleTimerEntities = simpleTimerDOA.getAllTimers();
	}
	
	LiveData<List<SimplerTimerEntity>> getAllSimpleTimerEntities()
	{
		return allSimpleTimerEntities;
	}
	
	public void insert(SimplerTimerEntity simplerTimerEntity)
	{
		new insertAsyncTask(simpleTimerDOA).execute(simplerTimerEntity);
	}
	
	private static class insertAsyncTask extends AsyncTask<SimplerTimerEntity, Void, Void>
	{
		private SimpleTimerDOA simpleTimerDOAAsyncTask;
		
		insertAsyncTask(SimpleTimerDOA simpleTimerDOA)
		{
			simpleTimerDOAAsyncTask = simpleTimerDOA;
		}
		
		@Override
		protected Void doInBackground(final SimplerTimerEntity... params)
		{
			simpleTimerDOAAsyncTask.insert(params[0]);
			return null;
		}
	}
}
