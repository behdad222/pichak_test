package co.pichak.pichak_test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.pichak.pichak_test.Adapter.NotificationAdapter;
import co.pichak.pichak_test.Adapter.RecycleViewAdapter;
import co.pichak.pichak_test.Object.Record;
import co.pichak.pichak_test.View.CustomView.CircleTextView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button start;
    private Button stop;
    private CircleTextView counter;
    private int count;
    private boolean startStatus;

    private final String COUNT = "count";
    private final String STARTSTATUS = "startStatus";

    private NotificationAdapter notificationAdapter;

    private RecyclerView recordRecycleView;
    private RecyclerView.LayoutManager layoutManager;
    private RecycleViewAdapter adapter;

    private Realm realm;

    private ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Prefs.initPrefs(this);
        notificationAdapter = new NotificationAdapter (0, this);
        realm = Realm.getInstance(this);
        records = new ArrayList<>();

        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        counter = (CircleTextView) findViewById(R.id.counter);

        recordRecycleView = (RecyclerView) findViewById(R.id.record_recycle_view);
        recordRecycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recordRecycleView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(records);
        recordRecycleView.setAdapter(adapter);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        counter.setOnClickListener(this);

        count = Prefs.getInt(COUNT, 0);
        startStatus = Prefs.getBoolean(STARTSTATUS, false);

        if (startStatus) {
            start(count);
        }

        RealmResults<Record> result = realm
                .where(Record.class)
                .findAll();

        if (result.size() != 0) {

            for (int i = 0; i < result.size(); i++)
                records.add(result.get(i));

            adapter.notifyDataSetChanged();
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
        notificationAdapter.startNotification(0);
    }

    private void stop() {
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);
        startStatus = false;

        Record record = new Record();
        record.setCount(count);
        record.setTime(getDateTime());

        records.add(record);

        adapter.notifyDataSetChanged();

        realm.beginTransaction();
        realm.copyToRealm(record);
        realm.commitTransaction();

        count = 0;
        Prefs.putInt(COUNT, count);
        Prefs.putBoolean(STARTSTATUS, startStatus);
        counter.setText(String.valueOf(count));
        notificationAdapter.stopNotification(0);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(); return dateFormat.format(date);
    }
}
