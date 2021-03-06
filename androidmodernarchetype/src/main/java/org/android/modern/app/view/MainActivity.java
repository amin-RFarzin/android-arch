package org.android.modern.app.view;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.android.R;
import org.android.modern.app.controller.SampleController;
import org.android.modern.config.Application;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @Pref
    org.android.modern.app.preferences.SamplePreference_ samplePreference;

    @App
    Application application;


    @Bean
    SampleController sampleController;

    //injected view with android annotations lib
    @ViewById(R.id.info)
    TextView textView;

    // injected view with butterknife lib
    @ViewById(R.id.greeting)
    TextView greetingTextView;

    // injected view with butterknife lib
    @ViewById(R.id.greeting_action)
    Button greetingButton;

    @SystemService
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.greeting_action)
    public void changeGreetingText(View view) {
        greetingTextView.setText("greeting text changed, sample" +
                " preference nick name is" + samplePreference.nickName().get());

        textView.setText("it seems you liked it :-)");
        doSomethingInBackground("");
    }

    @Click(R.id.sample_nosql_action)
    public void insertSomething() {
        //sampleController.doSample();
        greetingTextView.setText("inserted Successfully");
    }

    @Background
    void doSomethingInBackground(String arg) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doSomethingInUiThread(arg);
    }

    @UiThread
    void doSomethingInUiThread(String arg) {
        greetingTextView.setText("did something in background thread and then switched to ui thread");
    }
}
