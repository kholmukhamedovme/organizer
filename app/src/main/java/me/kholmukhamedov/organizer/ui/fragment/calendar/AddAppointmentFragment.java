package me.kholmukhamedov.organizer.ui.fragment.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kholmukhamedov.organizer.presentation.view.calendar.AddAppointmentView;
import me.kholmukhamedov.organizer.presentation.presenter.calendar.AddAppointmentPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddAppointmentFragment extends MvpAppCompatFragment implements AddAppointmentView {
    public static final String TAG = "AddAppointmentFragment";

    private Calendar mCalendar;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_appointment, container, false);
        ButterKnife.bind(this, view);

        mCalendar = Calendar.getInstance();
        setDefaultDate(mCalendar.getTimeInMillis());

        return view;
    }

    public void setDefaultDate (long selectedDate) {
        mCalendar.setTimeInMillis(selectedDate);
        String selectedYear = String.valueOf(mCalendar.get(Calendar.YEAR));
        String selectedMonth = getValidDateTime(mCalendar.get(Calendar.MONTH));
        String selectedDay = getValidDateTime(mCalendar.get(Calendar.DAY_OF_MONTH));

        mAppointmentDateStart.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
        mAppointmentDateEnd.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
    }

    @OnClick({ R.id.appointment_time_start, R.id.appointment_time_end })
    void openTimePickerDialog(final EditText view) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        String inputTime = view.getText().toString();
        Date defaultTime = null;
        try {
            defaultTime = timeFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mCalendar.setTime(defaultTime);
        int defaultHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int defaultMinute = mCalendar.get(Calendar.MINUTE);

        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String selectedHour = getValidDateTime(hourOfDay);
                String selectedMinute = getValidDateTime(minute);

                if (view.getId() == R.id.appointment_time_start) {
                    mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    mCalendar.set(Calendar.MINUTE, minute);
                    mCalendar.setTimeInMillis(mCalendar.getTimeInMillis() + 3600000);

                    int oneHourOffset = mCalendar.get(Calendar.HOUR_OF_DAY);
                    String oneHourLater = getValidDateTime(oneHourOffset);

                    mAppointmentTimeEnd.setText(oneHourLater + ":" + selectedMinute);
                }

                view.setText(selectedHour + ":" + selectedMinute);
            }
        }, defaultHour, defaultMinute, true).show();
    }

    @OnClick({ R.id.appointment_date_start, R.id.appointment_date_end })
    void openDatePickerDialog(final EditText view) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String inputDate = view.getText().toString();
        Date defaultDate = null;
        try {
            defaultDate = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mCalendar.setTime(defaultDate);
        int defaultYear = mCalendar.get(Calendar.YEAR);
        int defaultMonth = mCalendar.get(Calendar.MONTH);
        int defaultDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String selectedYear = String.valueOf(year);
                String selectedMonth = getValidDateTime(month + 1);
                String selectedDay = getValidDateTime(dayOfMonth);

                if (view.getId() == R.id.appointment_date_start) {
                    mAppointmentDateEnd.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                }

                view.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
            }
        }, defaultYear, defaultMonth, defaultDay).show();
    }

    private String getValidDateTime (int dateTime) {
        return (dateTime < 10) ? ("0" + dateTime) : ("" + dateTime);
    }
}
