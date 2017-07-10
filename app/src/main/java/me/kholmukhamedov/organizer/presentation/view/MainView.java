package me.kholmukhamedov.organizer.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import me.kholmukhamedov.organizer.presentation.presenter.MainPresenter;

public interface MainView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void navigateTo(MainPresenter.Tab tab);
}
