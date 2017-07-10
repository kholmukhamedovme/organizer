package me.kholmukhamedov.organizer.ui.fragment.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.kholmukhamedov.organizer.presentation.view.todo.AddTodoView;
import me.kholmukhamedov.organizer.presentation.presenter.todo.AddTodoPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class AddTodoFragment extends MvpAppCompatFragment implements AddTodoView {
    public static final String TAG = "AddTodoFragment";

    @InjectPresenter
    AddTodoPresenter mAddTodoPresenter;

    public static AddTodoFragment newInstance() {
        AddTodoFragment fragment = new AddTodoFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_todo, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
