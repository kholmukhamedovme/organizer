package me.kholmukhamedov.organizer.presentation.presenter;

import me.kholmukhamedov.organizer.presentation.view.MainView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private Tab tab = Tab.CALENDAR;

    public MainPresenter() {
        getViewState().navigateTo(tab);
    }

    public void navigateToCalendar() {
        tab = Tab.CALENDAR;
        getViewState().navigateTo(tab);
    }

    public void navigateToTodo() {
        tab = Tab.TODO;
        getViewState().navigateTo(tab);
    }

    public void navigateToTimeTracker() {
        tab = Tab.TIME_TRACKER;
        getViewState().navigateTo(tab);
    }

    public enum Tab {
        CALENDAR, TODO, TIME_TRACKER
    }
}
