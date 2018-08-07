package cpt111.toyl.Timer.ViewTimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cpt111.toyl.MainActivity;
import cpt111.toyl.R;
import cpt111.toyl.Timer.Home.TimerListFragment;
import cpt111.toyl.Timer.Home.TimerListRecyclerViewAdapter;
import cpt111.toyl.Timer.Model.AbstractTimer;
import cpt111.toyl.Timer.Model.CompoundTimer;
import cpt111.toyl.Timer.Model.Set;
import cpt111.toyl.Timer.Model.SimpleTimer;

public class TimerViewFragment extends Fragment {

    private static final long START_TIME_IN_MILLIS = 600_000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private RecyclerView mRecyclerView;
    private TimerViewRecyclerViewAdapter adapter;
    private CompoundTimer selectedTimer;
    private TextView currentTimerTextView;
    private TextView nextTimerTextView;

    private SimpleTimer activeSubTimer;
    private int activeAbstractTimerPosition;
    private AbstractTimer activeAbstractTimer;
    // for subtimers in sets
    private int activeSubTimerPosition;
  //  private OnListFragmentInteractionListener mListener;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private int position;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    public TimerViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get selected timer position
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("Position");
            selectedTimer = ((MainActivity) getActivity()).listOfTimers.getTimers().get(position);
        } else {
            System.out.println("Error in bundle");
        }

        // TODO: Could probably have its own function

        // get first timer to set initial text
        mTimeLeftInMillis = selectedTimer.getListOfTimers().get(0).getRemainingTime();

        // set active abstract timer to 0
        activeAbstractTimer = selectedTimer.getListOfTimers().get(0);
        activeAbstractTimerPosition = 0;

        // set up activeSubTimer to first subTimer
        if(activeAbstractTimer instanceof SimpleTimer) {
            activeSubTimer = (SimpleTimer) selectedTimer.getListOfTimers().get(0);
        } else {
            ((Set)activeAbstractTimer).setStartedExecution(true);
            activeSubTimerPosition = 0;
            activeSubTimer = ((Set)activeAbstractTimer).getListOfTimers().get(0);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate view
        View view = inflater.inflate(R.layout.fragment_timer_view, container, false);

        // set up UI components
        setUpUIComponents(view);

        // set up recyclerView adapter
        adapter = new TimerViewRecyclerViewAdapter(selectedTimer.getListOfTimers());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // update text to initial countdown timer
        updateCountDownText();

        // OnClickListener for Pause button
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startCompoundTimer();
                }
            }
        });

        // OnClickListener for Reset Button
        mButtonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
        return view;
    }

    // set up compound timer
    public void startCompoundTimer() {
        // Probably to be moved to its own separate function (label setting)
        currentTimerTextView.setText("Current: " + activeSubTimer.getName());
        currentTimerTextView.setVisibility(View.VISIBLE);

        if(selectedTimer.getListOfTimers().size() - 1 == activeAbstractTimerPosition) {
            nextTimerTextView.setVisibility(View.INVISIBLE);
        } else {
            // maybe will be changed to name of subtimer and not abstract timer
            nextTimerTextView.setText("Next: " + selectedTimer.getListOfTimers().get(activeAbstractTimerPosition + 1).getName());
            nextTimerTextView.setVisibility(View.VISIBLE);
        }

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                //timer.getListOfTimers().get(0).setRemainingTime(millisUntilFinished);
                //adapter.notifyParentChanged(0);
            }

            @Override
            public void onFinish() {
                currentTimerTextView.setVisibility(View.INVISIBLE);
                nextTimerTextView.setVisibility(View.INVISIBLE);

                // set remaining time on simpleTimer
                activeSubTimer.setRemainingTime(mTimeLeftInMillis);
                mTimerRunning = false;

                // if really over, get next timer
                if(activeSubTimer.getRemainingTime() < 1000) {
                    startNextTimer();
                }
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }


    private void setUpUIComponents(View view) {
        mRecyclerView = view.findViewById(R.id.subtimers_list);
        mTextViewCountDown = view.findViewById(R.id.countdown_text);
        mButtonStartPause = view.findViewById(R.id.timer_start_button);
        mButtonReset = view.findViewById(R.id.timer_reset_button);
        currentTimerTextView = view.findViewById(R.id.current_timer);
        nextTimerTextView = view.findViewById(R.id.next_timer);

        currentTimerTextView.setVisibility(View.INVISIBLE);
        nextTimerTextView.setVisibility(View.INVISIBLE);
    }

    private void startNextTimer() {
        // check if currently executing a simpleTimer
        if(activeAbstractTimer instanceof SimpleTimer) {
            // check if it's the last timer in the compound timer
            // otherwise assign the next abstractTimer
            if (selectedTimer.getListOfTimers().size() -1 == activeAbstractTimerPosition) {
                System.out.println("Compound timer finished executing");
                // end everything
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
                return;
            } else {
                activeAbstractTimerPosition += 1;
                activeAbstractTimer = selectedTimer.getListOfTimers().get(activeAbstractTimerPosition);
            }
            // NONE OF THIS IS WORKING YET
//        // it's a set, check if we already executed the first timer
//        } else {
//            // if set had started execution (??)
//            if (((Set) activeAbstractTimer).getStartedExecution()) {
//                // check if current position of subtimer is last subtimer in set
//                if (((Set) activeAbstractTimer).getListOfTimers().size() - 1 == activeSubTimerPosition) {
//                    ((Set) activeAbstractTimer).setCompletedRepeats(((Set) activeAbstractTimer).getCompletedRepeats() + 1);
//                    // check if the set has finished all the repetitions
//                    if (((Set) activeAbstractTimer).getCompletedRepeats() == ((Set) activeAbstractTimer).getRepeats()) {
//                        // check if it was the last abstract timer
//                        if (selectedTimer.getListOfTimers().size() - 1 == activeAbstractTimerPosition) {
//                            System.out.println("Compound timer finished executing");
//                            return;
//                            // else set the next abstract timer as the active timer
//                        } else {
//                            activeAbstractTimerPosition += 1;
//                            activeAbstractTimer = selectedTimer.getListOfTimers().get(activeAbstractTimerPosition);
//                        }
//                        // else set the first timer in the set as the active timer (active abstract timer and abstract timer position remain the same)
//                    } else {
//                        activeSubTimerPosition = 0;
//                    }
//                    // else set the next subtimer in the set
//                } else {
//                    activeSubTimerPosition += 1;
//                }
//            }
        }

        // set next active subtimer
        if(activeAbstractTimer instanceof SimpleTimer) {
            // reset set timer counter
            activeSubTimerPosition = 0;
            activeSubTimer = (SimpleTimer) selectedTimer.getListOfTimers().get(activeAbstractTimerPosition);
        } else {
            activeSubTimer = ((Set) selectedTimer.getListOfTimers().get(activeAbstractTimerPosition)).getListOfTimers().get(activeSubTimerPosition);
        }
        mTimeLeftInMillis = activeSubTimer.getRemainingTime();
        updateCountDownText();

        startCompoundTimer();
    }

    private void updateCountDownText() {

        long timeLeft = mTimeLeftInMillis;

        long hours = TimeUnit.MILLISECONDS.toHours(timeLeft);
        timeLeft -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeft);
        timeLeft -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeft);

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        activeSubTimer.setRemainingTime(mTimeLeftInMillis);
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);


    }

    private void resetTimer() {

        currentTimerTextView.setVisibility(View.INVISIBLE);
        nextTimerTextView.setVisibility(View.INVISIBLE);


        activeAbstractTimer = selectedTimer.getListOfTimers().get(0);
        mTimeLeftInMillis = selectedTimer.getListOfTimers().get(0).getLength();

        // set active abstract timer to 0
        activeAbstractTimer = selectedTimer.getListOfTimers().get(0);
        activeAbstractTimerPosition = 0;

        // set up activeSubTimer to first subTimer
        if(activeAbstractTimer instanceof SimpleTimer) {
            activeSubTimer = (SimpleTimer) selectedTimer.getListOfTimers().get(0);
        } else {
            ((Set)activeAbstractTimer).setStartedExecution(true);
            activeSubTimerPosition = 0;
            activeSubTimer = ((Set)activeAbstractTimer).getListOfTimers().get(0);
        }
        updateCountDownText();

    }



    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}