package me.kholmukhamedov.organizer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import me.kholmukhamedov.organizer.presentation.view.MainView;
import me.kholmukhamedov.organizer.presentation.presenter.MainPresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";
    @InjectPresenter
    MainPresenter mMainPresenter;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
