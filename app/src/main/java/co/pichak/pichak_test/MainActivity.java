package co.pichak.pichak_test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button start;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                stop.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                break;

            case R.id.stop:
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
