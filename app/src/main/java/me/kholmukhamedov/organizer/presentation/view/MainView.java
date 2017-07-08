package me.kholmukhamedov.organizer.presentation.view;

import com.arellomobile.mvp.MvpView;

import me.kholmukhamedov.organizer.presentation.presenter.MainPresenter;

public interface MainView extends MvpView {
    void navigateTo(MainPresenter.Tab tab);
}
