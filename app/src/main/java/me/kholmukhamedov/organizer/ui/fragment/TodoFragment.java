package me.kholmukhamedov.organizer.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import me.kholmukhamedov.organizer.presentation.view.TodoView;
import me.kholmukhamedov.organizer.presentation.presenter.TodoPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class TodoFragment extends MvpAppCompatFragment implements TodoView {
    public static final String TAG = "TodoFragment";

    @InjectPresenter
    TodoPresenter mTodoPresenter;

    public static TodoFragment newInstance() {
        TodoFragment fragment = new TodoFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.setGroupVisible(R.id.main_toolbar_todo_options, true);
    }
}
