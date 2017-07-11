package me.kholmukhamedov.organizer.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void navigateToCalendar();
    void navigateToTodo();
    void navigateToTimeTracker();
    void expandAddScreen();
    void collapseAddScreen();
}
