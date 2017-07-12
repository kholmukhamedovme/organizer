package me.kholmukhamedov.organizer.ui.fragment.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kholmukhamedov.organizer.presentation.view.calendar.CalendarView;
import me.kholmukhamedov.organizer.presentation.presenter.calendar.CalendarPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class CalendarFragment extends MvpAppCompatFragment implements CalendarView {
    public static final String TAG = "CalendarFragment";

    @InjectPresenter
    CalendarPresenter mCalendarPresenter;

    @BindView(R.id.calendar)
    android.widget.CalendarView mCalendarView;

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.setGroupVisible(R.id.main_toolbar_calendar_options, true);
    }
}
