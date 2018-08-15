package cpt111.toyl.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import cpt111.toyl.database.database_access_objects.SimpleTimerDOA;
import cpt111.toyl.database.entities.SimplerTimerEntity;
import cpt111.toyl.repository.SimplerTimerRepository;

public class TestTimerViewModel extends AndroidViewModel {

    private SimplerTimerRepository mRepository;
    private LiveData<List<SimplerTimerEntity>> mAllTimers;

    public TestTimerViewModel(Application application) {
        super(application);
        mRepository = new SimplerTimerRepository(getApplication());
        mAllTimers = mRepository.getAllSimpleTimerEntities();
    }

    public LiveData<List<SimplerTimerEntity>> getAllTimers() {

        return mAllTimers; }

    public void insert(SimplerTimerEntity timer) {
        mRepository.insert(timer);
    }

}
