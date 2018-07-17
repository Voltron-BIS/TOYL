package cpt111.toyl;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

	private TextView mTextMessage;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
	{

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item)
		{
			// Initialise the fragment manager for displaying the different sections when a navigation option is chosen
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

			switch (item.getItemId()) {
				case R.id.navigation_scheduler:
					mTextMessage.setText(R.string.title_scheduler);
					return true;
				case R.id.navigation_statistics:
					mTextMessage.setText(R.string.title_statistics);
					return true;
				case R.id.navigation_timer:
					mTextMessage.setText(R.string.title_timer);
					return true;
				case R.id.navigation_tasks:
					mTextMessage.setText(R.string.title_tasks);
					return true;
				case R.id.navigation_overlays:
					mTextMessage.setText("");

					// Hide the currently displayed fragment and inflate the overlays fragment
					setTitle(R.string.title_overlays);
					Fragment overlaysFragment = new OverlaysWhiteboardFragment();
					fragmentTransaction.replace(R.id.container, overlaysFragment);
                    fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
					return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextMessage = (TextView) findViewById(R.id.message);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
	}

}
