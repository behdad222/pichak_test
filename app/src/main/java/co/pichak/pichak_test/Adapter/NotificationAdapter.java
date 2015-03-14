package co.pichak.pichak_test.Adapter;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.app.TaskStackBuilder;
import android.support.v4.app.NotificationCompat;

import co.pichak.pichak_test.MainActivity;
import co.pichak.pichak_test.R;

public class NotificationAdapter extends Activity {
    int id;
    NotificationManager notificationManager;
    NotificationCompat.Builder nBuilder;
    Context context;

    public NotificationAdapter(int id, Context context) {
        this.id = id;
        this.context = context;

        nBuilder = new NotificationCompat.Builder(context);
        nBuilder.setContentTitle(context.getString(R.string.app_name))
                .setContentText("start")
                .setSmallIcon(R.drawable.ic_launcher)
                .setOngoing(true);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        nBuilder.setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void startNotification(int id) {
        nBuilder.setContentText("start").setOngoing(true);
        notificationManager.notify(id, nBuilder.build());
    }

    public void stopNotification(int id) {
            nBuilder.setContentText("stop").setOngoing(false);
            notificationManager.notify(id, nBuilder.build());
    }
}
