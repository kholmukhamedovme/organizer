package me.kholmukhamedov.organizer.ui.fragment.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kholmukhamedov.organizer.presentation.view.calendar.AddAppointmentView;
import me.kholmukhamedov.organizer.presentation.presenter.calendar.AddAppointmentPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class AddAppointmentFragment extends MvpAppCompatFragment implements AddAppointmentView {
    public static final String TAG = "AddAppointmentFragment";

    @InjectPresenter
    AddAppointmentPresenter mAddAppointmentPresenter;

    @BindView(R.id.appointment_title)
    EditText mAppointmentTitle;
    @BindView(R.id.appointment_time_start)
    EditText mAppointmentTimeStart;
    @BindView(R.id.appointment_date_start)
    EditText mAppointmentDateStart;
    @BindView(R.id.appointment_time_end)
    EditText mAppointmentTimeEnd;
    @BindView(R.id.appointment_date_end)
    EditText mAppointmentDateEnd;

    public static AddAppointmentFragment newInstance() {
        AddAppointmentFragment fragment = new AddAppointmentFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
