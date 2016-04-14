package com.sandstorm.softspec.queue2win.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private ImageButton addQueueButton;
    private int customerId;

    private AlertDialog.Builder addQueueDialogBuilder;
    private EditText newQueueName;

    private AlertDialog.Builder deleteQueueDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {

        customerId = (int) getIntent().getSerializableExtra("customerIndex");

        addQueueDialogBuilder = new AlertDialog.Builder(this);
        deleteQueueDialogBuilder = new AlertDialog.Builder(this);

        testQueue = new Queue("Test");
        queues = new ArrayList<Queue>();


        queueCardAdapter = new QueueCardAdapter(queues, new CustomClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, QueueActivity.class);
                intent.putExtra("customerIndex",getIntent().getSerializableExtra("customerIndex"));
                startActivity(intent);
            }

            @Override
            public boolean onLongClick(View v, int position) {
                deleteQueueDialog();
                return false;
            }
        });

        queueListView = (RecyclerView) findViewById(R.id.rv_main);
        queueListView.setHasFixedSize(true);
        queueListView.setAdapter(queueCardAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        queueListView.setLayoutManager(llm);

        addQueueButton = (ImageButton) findViewById(R.id.main_image_addqueue);
        addQueueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQueueDialog();
            }
        });

    }

    private void deleteQueueDialog() {
        deleteQueueDialogBuilder.setTitle("Are you sure?");
        deleteQueueDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteQueue();
            }
        });
        deleteQueueDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = deleteQueueDialogBuilder.create();

        alertDialog.show();
    }

    private void deleteQueue() {
        Storage.getInstance().getCustomerList().get(customerId).deleteQueue();
        Toast.makeText(getApplicationContext(), "Your queue has been cancel",Toast.LENGTH_SHORT).show();
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();

        queues.clear();
        Queue queue = Storage.getInstance().getCustomerList().get( (int) getIntent().getSerializableExtra("customerIndex")).getQueue();
        if(queue==null) {
            queueListView.setVisibility(View.INVISIBLE);
            addQueueButton.setVisibility(View.VISIBLE);
        }
        else {
            queues.add(queue);
            queueListView.setVisibility(View.VISIBLE);
            addQueueButton.setVisibility(View.INVISIBLE);
        }

        queueCardAdapter.notifyDataSetChanged();

    }

    private void addQueueDialog() {
        newQueueName = new EditText(this);
        addQueueDialogBuilder.setTitle("New Queue");
        addQueueDialogBuilder.setView(newQueueName);
        addQueueDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addQueue();
            }
        });
        addQueueDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = addQueueDialogBuilder.create();
        alertDialog.show();
    }

    private void addQueue() {
        Storage.getInstance().getCustomerList().get(customerId).setQueue(new Queue(newQueueName.getText().toString()));
        Toast.makeText(getApplicationContext(), "Your queue has been made",Toast.LENGTH_SHORT).show();
        onStart();

    }
}
