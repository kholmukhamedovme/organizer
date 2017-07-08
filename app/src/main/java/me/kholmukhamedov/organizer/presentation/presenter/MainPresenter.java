package me.kholmukhamedov.organizer.presentation.presenter;

import me.kholmukhamedov.organizer.presentation.view.MainView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private Tab tab;

    public MainPresenter() {
        getViewState().navigateTo(Tab.CALENDAR);
    }

    public void changeTab (Tab tab) {
        this.tab = tab;
        getViewState().navigateTo(tab);
    }

    public enum Tab {
        CALENDAR, TODO, TIME_TRACKER
    }
}
