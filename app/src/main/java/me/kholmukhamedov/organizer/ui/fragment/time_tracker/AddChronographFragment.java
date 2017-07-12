package me.kholmukhamedov.organizer.ui.fragment.time_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.kholmukhamedov.organizer.presentation.view.time_tracker.AddChronographView;
import me.kholmukhamedov.organizer.presentation.presenter.time_tracker.AddChronographPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class AddChronographFragment extends MvpAppCompatFragment implements AddChronographView {
    public static final String TAG = "AddChronographFragment";

    @InjectPresenter
    AddChronographPresenter mAddChronographPresenter;

    public static AddChronographFragment newInstance() {
        AddChronographFragment fragment = new AddChronographFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_chronograph, container, false);

        return view;
    }
}
