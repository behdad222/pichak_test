package co.pichak.pichak_test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import co.pichak.pichak_test.View.CustomView.CircleTextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button start;
    private Button stop;
    private CircleTextView counter;
    private int count;
    private boolean startStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        counter = (CircleTextView) findViewById(R.id.counter);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        counter.setOnClickListener(this);

        count = 0;
        startStatus = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                stop.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                startStatus = true;
                break;

            case R.id.stop:
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                startStatus = false;
                count = 0;
                counter.setText(String.valueOf(count));
                break;

            case R.id.counter:
                if (startStatus) {
                    count++;
                    counter.setText(String.valueOf(count));
                }
                break;
        }
    }
}
