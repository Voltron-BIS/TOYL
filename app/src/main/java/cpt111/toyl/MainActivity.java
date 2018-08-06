package cpt111.toyl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cpt111.toyl.Timer.Dummy_TimerTempData;
import cpt111.toyl.Timer.Home.TimerListFragment;

public class MainActivity extends AppCompatActivity
{

	// TODO: temp data to simulate DB (timers), public to avoid cluttering this class with
    // methods as it will be moved from here
	public Dummy_TimerTempData listOfTimers;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
	{
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item)
		{
			// Initialise the fragment manager for displaying the different sections when a navigation option is chosen
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

			// TODO: To be changed to Tasks fragment
			Fragment selectedFragment = new EmptyTestFragment();

			switch (item.getItemId()) {

                case R.id.navigation_timer:
                    setTitle(R.string.title_timer);
                    selectedFragment = new TimerListFragment();
                    break;

                case R.id.navigation_overlays:
                    setTitle(R.string.title_overlays);
                    selectedFragment = new OverlaysWhiteboardFragment();
                    break;

                case R.id.navigation_tasks:
                    setTitle(R.string.title_tasks);
                    selectedFragment = new EmptyTestFragment();
                    break;

				case R.id.navigation_scheduler:
                    setTitle(R.string.title_scheduler);
                    selectedFragment = new TimeSchedulerFragment();
                    break;

				case R.id.navigation_statistics:
                    setTitle(R.string.title_statistics);
                    selectedFragment = new StatisticWhiteboardFragment();
                    break;
			}

            //fragmentTransaction.addToBackStack(null);
			fragmentTransaction.replace(R.id.container, selectedFragment).commit();

			return true;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listOfTimers = new Dummy_TimerTempData();

		setContentView(R.layout.activity_main);

		getWindow().getDecorView().setBackgroundColor(Color.WHITE);


		BottomNavigationView bottomNavigationView;
		bottomNavigationView = findViewById(R.id.navigation);
		// set default selected section to tasks
		bottomNavigationView.setSelectedItemId(R.id.navigation_tasks);
		bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

	}

}
