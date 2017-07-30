package me.kholmukhamedov.organizer.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kholmukhamedov.organizer.presentation.view.MainView;
import me.kholmukhamedov.organizer.presentation.presenter.MainPresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.kholmukhamedov.organizer.R;
import me.kholmukhamedov.organizer.ui.fragment.calendar.CalendarFragment;
import me.kholmukhamedov.organizer.ui.fragment.time_tracker.TimeTrackerFragment;
import me.kholmukhamedov.organizer.ui.fragment.todo.TodoFragment;
import me.kholmukhamedov.organizer.ui.fragment.calendar.AddAppointmentFragment;
import me.kholmukhamedov.organizer.ui.fragment.time_tracker.AddChronographFragment;
import me.kholmukhamedov.organizer.ui.fragment.todo.AddTodoFragment;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    private BottomSheetBehavior mBottomSheetBehavior;
    private FragmentManager mFragmentManager;

    @InjectPresenter
    MainPresenter mMainPresenter;

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_bottom_sheet)
    LinearLayout mBottomSheetLayout;
    @BindView(R.id.main_bottom_sheet_navigation)
    BottomNavigationView mBottomSheetNavigationView;
    @BindView(R.id.main_bottom_sheet_hint)
    TextView mBottomSheetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
        mBottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
        mBottomSheetNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_toolbar_add:
                mMainPresenter.expandAddScreen();

                if (mBottomSheetNavigationView.getSelectedItemId() == R.id.main_bottom_sheet_navigation_calendar) {
                    long selectedDate = ((CalendarFragment) mFragmentManager.findFragmentByTag(CalendarFragment.TAG))
                            .getSelectedDate();
                    ((AddAppointmentFragment) mFragmentManager.findFragmentByTag(AddAppointmentFragment.TAG))
                            .setDefaultDate(selectedDate);
                }

                return true;
            case R.id.main_toolbar_change_view:
                return true;
            case R.id.main_toolbar_delete_all_todos:
                return true;
            case R.id.main_toolbar_reset_all_chronographs:
                return true;
            case R.id.main_toolbar_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
            mMainPresenter.collapseAddScreen();
        else
            super.onBackPressed();
    }

    @Override
    public void navigateToCalendar() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment mainFragment = mFragmentManager.findFragmentByTag(CalendarFragment.TAG);
        Fragment addScreenFragment = mFragmentManager.findFragmentByTag(AddAppointmentFragment.TAG);

        hideAllFragments();

        if (mainFragment != null && addScreenFragment != null) {
            fragmentTransaction
                    .show(mainFragment)
                    .show(addScreenFragment);
        } else {
            fragmentTransaction
                    .add(R.id.main_fragment, CalendarFragment.newInstance(), CalendarFragment.TAG)
                    .add(R.id.main_bottom_sheet_fragment, AddAppointmentFragment.newInstance(), AddAppointmentFragment.TAG);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void navigateToTodo() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment mainFragment = mFragmentManager.findFragmentByTag(TodoFragment.TAG);
        Fragment addScreenFragment = mFragmentManager.findFragmentByTag(AddTodoFragment.TAG);

        hideAllFragments();

        if (mainFragment != null && addScreenFragment != null) {
            fragmentTransaction
                    .show(mainFragment)
                    .show(addScreenFragment);
        } else {
            fragmentTransaction
                    .add(R.id.main_fragment, TodoFragment.newInstance(), TodoFragment.TAG)
                    .add(R.id.main_bottom_sheet_fragment, AddTodoFragment.newInstance(), AddTodoFragment.TAG);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void navigateToTimeTracker() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment mainFragment = mFragmentManager.findFragmentByTag(TimeTrackerFragment.TAG);
        Fragment addScreenFragment = mFragmentManager.findFragmentByTag(AddChronographFragment.TAG);

        hideAllFragments();

        if (mainFragment != null && addScreenFragment != null) {
            fragmentTransaction
                    .show(mainFragment)
                    .show(addScreenFragment);
        } else {
            fragmentTransaction
                    .add(R.id.main_fragment, TimeTrackerFragment.newInstance(), TimeTrackerFragment.TAG)
                    .add(R.id.main_bottom_sheet_fragment, AddChronographFragment.newInstance(), AddChronographFragment.TAG);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void expandAddScreen() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetTextView.setVisibility(View.INVISIBLE);

        CalendarFragment calendarFragment = (CalendarFragment) mFragmentManager.findFragmentByTag(CalendarFragment.TAG);
        if (calendarFragment != null) calendarFragment.hideCalendarView();
    }

    @Override
    public void collapseAddScreen() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetTextView.setVisibility(View.VISIBLE);

        CalendarFragment calendarFragment = (CalendarFragment) mFragmentManager.findFragmentByTag(CalendarFragment.TAG);
        if (calendarFragment != null) calendarFragment.showCalendarView();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_bottom_sheet_navigation_calendar:
                    mMainPresenter.navigateToCalendar();
                    return true;
                case R.id.main_bottom_sheet_navigation_todo:
                    mMainPresenter.navigateToTodo();
                    return true;
                case R.id.main_bottom_sheet_navigation_time_tracker:
                    mMainPresenter.navigateToTimeTracker();
                    return true;
            }

            return false;
        }
    };

    private CustomBottomSheetBehavior.BottomSheetCallback bottomSheetCallback =
            new CustomBottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_EXPANDED:
                    mMainPresenter.expandAddScreen();
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                    mMainPresenter.collapseAddScreen();
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            mBottomSheetTextView.setVisibility(View.INVISIBLE);

            CalendarFragment calendarFragment = (CalendarFragment) mFragmentManager.findFragmentByTag(CalendarFragment.TAG);
            if (calendarFragment != null) calendarFragment.showCalendarView();
        }
    };

    private void hideAllFragments() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(mFragmentManager.findFragmentByTag(CalendarFragment.TAG));
        fragments.add(mFragmentManager.findFragmentByTag(AddAppointmentFragment.TAG));
        fragments.add(mFragmentManager.findFragmentByTag(TodoFragment.TAG));
        fragments.add(mFragmentManager.findFragmentByTag(AddTodoFragment.TAG));
        fragments.add(mFragmentManager.findFragmentByTag(TimeTrackerFragment.TAG));
        fragments.add(mFragmentManager.findFragmentByTag(AddChronographFragment.TAG));

        for (Fragment fragment : fragments) {
            if (fragment != null) fragmentTransaction.hide(fragment);
        }

        fragmentTransaction.commit();
    }
}
