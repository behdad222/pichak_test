package co.pichak.pichak_test.Object;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Record extends RealmObject {
    private int count;
    private String time;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
