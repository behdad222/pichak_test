package co.pichak.pichak_test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.pixplicity.easyprefs.library.Prefs;

import co.pichak.pichak_test.View.CustomView.CircleTextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button start;
    private Button stop;
    private CircleTextView counter;
    private int count;
    private boolean startStatus;

    private final String COUNT = "count";
    private final String STARTSTATUS = "startStatus";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Prefs.initPrefs(this);

        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        counter = (CircleTextView) findViewById(R.id.counter);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        counter.setOnClickListener(this);

        count = Prefs.getInt(COUNT, 0);
        startStatus = Prefs.getBoolean(STARTSTATUS, false);

        if (startStatus) {
            start(count);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                start(0);
                break;

            case R.id.stop:
                stop();
                break;

            case R.id.counter:
                if (startStatus) {
                    count++;
                    start(count);
                }
                break;
        }
    }

    private void start(int count) {
        stop.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
        startStatus = true;
        Prefs.putInt(COUNT, count);
        Prefs.putBoolean(STARTSTATUS, startStatus);
        counter.setText(String.valueOf(count));
    }

    private void stop() {
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);
        startStatus = false;
        count = 0;
        Prefs.putInt(COUNT, count);
        Prefs.putBoolean(STARTSTATUS, startStatus);
        counter.setText(String.valueOf(count));
    }
}
