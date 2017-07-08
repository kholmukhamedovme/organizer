package me.kholmukhamedov.organizer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kholmukhamedov.organizer.presentation.view.MainView;
import me.kholmukhamedov.organizer.presentation.presenter.MainPresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.kholmukhamedov.organizer.R;
import me.kholmukhamedov.organizer.ui.fragment.CalendarFragment;
import me.kholmukhamedov.organizer.ui.fragment.TimeTrackerFragment;
import me.kholmukhamedov.organizer.ui.fragment.TodoFragment;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    private FragmentManager fragmentManager;

    @InjectPresenter
    MainPresenter mMainPresenter;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_bottom_sheet_navigation)
    BottomNavigationView navigation;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_bottom_sheet_navigation_calendar:
                    mMainPresenter.changeTab(MainPresenter.Tab.CALENDAR);
                    return true;
                case R.id.main_bottom_sheet_navigation_todo:
                    mMainPresenter.changeTab(MainPresenter.Tab.TODO);
                    return true;
                case R.id.main_bottom_sheet_navigation_time_tracker:
                    mMainPresenter.changeTab(MainPresenter.Tab.TIME_TRACKER);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public void navigateTo(MainPresenter.Tab tab) {
        switch (tab) {
            case CALENDAR:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, CalendarFragment.newInstance(), CalendarFragment.TAG)
                        .commit();
                break;
            case TODO:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, TodoFragment.newInstance(), TodoFragment.TAG)
                        .commit();
                break;
            case TIME_TRACKER:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, TimeTrackerFragment.newInstance(), TimeTrackerFragment.TAG)
                        .commit();
                break;
        }
    }
}
