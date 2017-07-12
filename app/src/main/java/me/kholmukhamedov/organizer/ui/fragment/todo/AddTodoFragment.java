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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_todo, container, false);

        return view;
    }
}
