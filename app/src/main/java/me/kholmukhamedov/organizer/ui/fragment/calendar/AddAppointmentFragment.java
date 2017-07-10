package me.kholmukhamedov.organizer.ui.fragment.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.kholmukhamedov.organizer.presentation.view.calendar.AddAppointmentView;
import me.kholmukhamedov.organizer.presentation.presenter.calendar.AddAppointmentPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class AddAppointmentFragment extends MvpAppCompatFragment implements AddAppointmentView {
    public static final String TAG = "AddAppointmentFragment";

    @InjectPresenter
    AddAppointmentPresenter mAddAppointmentPresenter;

    public static AddAppointmentFragment newInstance() {
        AddAppointmentFragment fragment = new AddAppointmentFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_appointment, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
