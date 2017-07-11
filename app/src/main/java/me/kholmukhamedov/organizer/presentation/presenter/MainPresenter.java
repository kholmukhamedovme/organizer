package me.kholmukhamedov.organizer.presentation.presenter;

import me.kholmukhamedov.organizer.presentation.view.MainView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    public MainPresenter() {
        getViewState().navigateToCalendar();
    }

    public void navigateToCalendar() {
        getViewState().navigateToCalendar();
    }

    public void navigateToTodo() {
        getViewState().navigateToTodo();
    }

    public void navigateToTimeTracker() {
        getViewState().navigateToTimeTracker();
    }

    public void expandAddScreen() {
        getViewState().expandAddScreen();
    }

    public void collapseAddScreen() {
        getViewState().collapseAddScreen();
    }
}
