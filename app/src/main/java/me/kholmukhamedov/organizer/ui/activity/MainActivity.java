package me.kholmukhamedov.organizer.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
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

import java.util.Map;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    private BottomSheetBehavior mBottomSheetBehavior;
    private FragmentManager mFragmentManager;
    private Map<String, Fragment> mFragmentsMap = new ArrayMap<>();

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
        Fragment mainFragment = mFragmentsMap.get(CalendarFragment.TAG);
        Fragment addScreenFragment = mFragmentsMap.get(AddAppointmentFragment.TAG);

        if (mainFragment == null && addScreenFragment == null) {
            mainFragment = CalendarFragment.newInstance();
            addScreenFragment = AddAppointmentFragment.newInstance();

            mFragmentsMap.put(CalendarFragment.TAG, mainFragment);
            mFragmentsMap.put(AddAppointmentFragment.TAG, addScreenFragment);
        }

        mFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, mainFragment, CalendarFragment.TAG)
                .replace(R.id.main_bottom_sheet_fragment, addScreenFragment, AddAppointmentFragment.TAG)
                .commit();
    }

    @Override
    public void navigateToTodo() {
        Fragment mainFragment = mFragmentsMap.get(TodoFragment.TAG);
        Fragment addScreenFragment = mFragmentsMap.get(AddTodoFragment.TAG);

        if (mainFragment == null && addScreenFragment == null) {
            mainFragment = TodoFragment.newInstance();
            addScreenFragment = AddTodoFragment.newInstance();

            mFragmentsMap.put(TodoFragment.TAG, mainFragment);
            mFragmentsMap.put(AddTodoFragment.TAG, addScreenFragment);
        }

        mFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, mainFragment, TodoFragment.TAG)
                .replace(R.id.main_bottom_sheet_fragment, addScreenFragment, AddTodoFragment.TAG)
                .commit();
    }

    @Override
    public void navigateToTimeTracker() {
        Fragment mainFragment = mFragmentsMap.get(TimeTrackerFragment.TAG);
        Fragment addScreenFragment = mFragmentsMap.get(AddChronographFragment.TAG);

        if (mainFragment == null && addScreenFragment == null) {
            mainFragment = TimeTrackerFragment.newInstance();
            addScreenFragment = AddChronographFragment.newInstance();

            mFragmentsMap.put(TimeTrackerFragment.TAG, mainFragment);
            mFragmentsMap.put(AddChronographFragment.TAG, addScreenFragment);
        }

        mFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, mainFragment, TimeTrackerFragment.TAG)
                .replace(R.id.main_bottom_sheet_fragment, addScreenFragment, AddChronographFragment.TAG)
                .commit();
    }

    @Override
    public void expandAddScreen() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mBottomSheetTextView.setVisibility(View.INVISIBLE);

        switch (mBottomSheetNavigationView.getSelectedItemId()) {
            case R.id.main_bottom_sheet_navigation_calendar:
                CalendarFragment calendarFragment = (CalendarFragment) mFragmentsMap.get(CalendarFragment.TAG);
                if (calendarFragment != null) calendarFragment.hideCalendarView();
                break;
        }
    }

    @Override
    public void collapseAddScreen() {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetTextView.setVisibility(View.VISIBLE);

        switch (mBottomSheetNavigationView.getSelectedItemId()) {
            case R.id.main_bottom_sheet_navigation_calendar:
                CalendarFragment calendarFragment = (CalendarFragment) mFragmentsMap.get(CalendarFragment.TAG);
                if (calendarFragment != null) calendarFragment.showCalendarView();
                break;
        }
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

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback =
            new BottomSheetBehavior.BottomSheetCallback() {
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

            switch (mBottomSheetNavigationView.getSelectedItemId()) {
                case R.id.main_bottom_sheet_navigation_calendar:
                    CalendarFragment calendarFragment = (CalendarFragment) mFragmentsMap.get(CalendarFragment.TAG);
                    if (calendarFragment != null) calendarFragment.showCalendarView();
                    break;
            }
        }
    };
}
