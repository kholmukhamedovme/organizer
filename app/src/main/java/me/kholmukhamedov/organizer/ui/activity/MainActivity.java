package me.kholmukhamedov.organizer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
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

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    private BottomSheetBehavior bottomSheetBehavior;
    private FragmentManager fragmentManager;

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_bottom_sheet)
    LinearLayout bottomSheet;
    @BindView(R.id.main_bottom_sheet_navigation)
    BottomNavigationView bottomSheetNavigation;
    @BindView(R.id.main_bottom_sheet_hint)
    TextView bottomSheetHint;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
        bottomSheetNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
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
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetHint.setVisibility(View.INVISIBLE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateTo(MainPresenter.Tab tab) {
        switch (tab) {
            case CALENDAR:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, CalendarFragment.newInstance(), CalendarFragment.TAG)
                        .replace(R.id.main_bottom_sheet_fragment, AddAppointmentFragment.newInstance(), AddAppointmentFragment.TAG)
                        .commit();
                break;
            case TODO:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, TodoFragment.newInstance(), TodoFragment.TAG)
                        .replace(R.id.main_bottom_sheet_fragment, AddTodoFragment.newInstance(), AddTodoFragment.TAG)
                        .commit();
                break;
            case TIME_TRACKER:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, TimeTrackerFragment.newInstance(), TimeTrackerFragment.TAG)
                        .replace(R.id.main_bottom_sheet_fragment, AddChronographFragment.newInstance(), AddChronographFragment.TAG)
                        .commit();
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_bottom_sheet_navigation_calendar:
                    presenter.navigateToCalendar();
                    return true;
                case R.id.main_bottom_sheet_navigation_todo:
                    presenter.navigateToTodo();
                    return true;
                case R.id.main_bottom_sheet_navigation_time_tracker:
                    presenter.navigateToTimeTracker();
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
                case BottomSheetBehavior.STATE_DRAGGING:
                    bottomSheetHint.setVisibility(View.INVISIBLE);
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                    bottomSheetHint.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
    };
}
