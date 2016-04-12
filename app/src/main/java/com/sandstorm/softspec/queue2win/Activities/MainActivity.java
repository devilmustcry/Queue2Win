package com.sandstorm.softspec.queue2win.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sandstorm.softspec.queue2win.CustomClickListener;
import com.sandstorm.softspec.queue2win.Models.Queue;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.QueueCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Queue> queues;
    private QueueCardAdapter queueCardAdapter;
    private RecyclerView queueListView;
    private Queue testQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        testQueue = new Queue("Test");
        queues = new ArrayList<Queue>();

        queueCardAdapter = new QueueCardAdapter(queues, new CustomClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, QueueActivity.class);
                intent.putExtra("customerIndex",getIntent().getSerializableExtra("customerIndex"));
                startActivity(intent);
            }
        });

        queueListView = (RecyclerView) findViewById(R.id.rv_main);
        queueListView.setHasFixedSize(true);
        queueListView.setAdapter(queueCardAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        queueListView.setLayoutManager(llm);

    }

    @Override
    protected void onStart() {
        super.onStart();

        queues.clear();
        Queue queue = Storage.getInstance().getCustomerList().get( (int) getIntent().getSerializableExtra("customerIndex")).getQueue();
        if(queue==null) {
            queues.add(testQueue);
        }
        else {
            queues.add(queue);
        }

        queueCardAdapter.notifyDataSetChanged();

    }
}
