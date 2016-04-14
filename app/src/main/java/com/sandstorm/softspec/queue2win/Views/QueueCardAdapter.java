package com.sandstorm.softspec.queue2win.Views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sandstorm.softspec.queue2win.CustomClickListener;
import com.sandstorm.softspec.queue2win.Models.Queue;
import com.sandstorm.softspec.queue2win.R;

import java.util.List;

/**
 * Created by FTTX on 4/12/2016 AD.
 */
public class QueueCardAdapter extends RecyclerView.Adapter<QueueCardAdapter.CardViewHolder> {

    private List<Queue> queues;
    private CustomClickListener listener;

    public QueueCardAdapter(List<Queue> queues,CustomClickListener listener) {
        this.queues = queues;
        this.listener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.queue_card, parent, false);

        final CardViewHolder cvh = new CardViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, cvh.getPosition());
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return listener.onLongClick(v, cvh.getPosition());
            }
        });

        return cvh;
    }


    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.name.setText(queues.get(position).getName());
        if(queues.get(position).getTimeLeft()>0)
            holder.timeleft.setText(queues.get(position).getTimeLeft()+" Minute");
        else
            holder.timeleft.setText("Ready");
    }

    @Override
    public int getItemCount() {
        return queues.size();
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView timeleft;

        public CardViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.queue_card_name);
            timeleft = (TextView) itemView.findViewById(R.id.queue_card_timeleft);
        }
    }

}
