package co.pichak.pichak_test.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.pichak.pichak_test.Object.Record;
import co.pichak.pichak_test.R;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private ArrayList<Record> records;

    public RecycleViewAdapter(ArrayList<Record> records) {
        this.records = records;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView count;

        public ViewHolder(View v) {
            super(v);
            time = (TextView) itemView.findViewById(R.id.time);
            count = (TextView) itemView.findViewById(R.id.count);
        }
    }

    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Record record = records.get(position);
        holder.time.setText(record.getTime());
        holder.count.setText(String.valueOf(record.getCount()));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }
}
