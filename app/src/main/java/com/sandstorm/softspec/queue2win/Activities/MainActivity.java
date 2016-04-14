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
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sandstorm.softspec.queue2win.CustomClickListener;
import com.sandstorm.softspec.queue2win.Models.Queue;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.QueueCardAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Queue> queues;
    private QueueCardAdapter queueCardAdapter;
    private RecyclerView queueListView;
    private TextView balance;
    private Button addBalanceButton;
    private ImageButton addQueueButton;
    private int customerId;

    private AlertDialog.Builder addQueueDialogBuilder;
    private EditText newQueueName;
    private EditText amount;

    private AlertDialog.Builder deleteQueueDialogBuilder;
    private AlertDialog.Builder addBalanceDialogBuilder;


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
        addBalanceDialogBuilder = new AlertDialog.Builder(this);

        balance = (TextView) findViewById(R.id.main_text_balance);
        balance.setText(Storage.getInstance().getCustomerList().get(customerId).getBalance()+"");

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

        addQueueButton = (ImageButton) findViewById(R.id.main_imagebutton_addqueue);
        addQueueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQueueDialog();
            }
        });
        addBalanceButton = (Button) findViewById(R.id.main_button_addbalance);
        addBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBalanceDialog();
            }
        });

    }

    private void addBalanceDialog() {
        amount = new EditText(this);
        amount.setInputType(InputType.TYPE_CLASS_NUMBER);
        addBalanceDialogBuilder.setTitle("How much?");
        addBalanceDialogBuilder.setView(amount);
        addBalanceDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addBalance(Integer.parseInt(amount.getText().toString()));
                Toast.makeText(getApplicationContext(), "You have added " + amount.getText().toString() + " Baht.", Toast.LENGTH_SHORT).show();
                balance.setText(Storage.getInstance().getCustomerList().get(customerId).getBalance() + "");
            }
        });
        addBalanceDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = addBalanceDialogBuilder.create();
        alertDialog.show();


    }

    private void addBalance(int balance) {
        Storage.getInstance().getCustomerList().get(customerId).deposit(balance);
    }

    private void deleteQueueDialog() {
        deleteQueueDialogBuilder.setTitle("Are you sure?");
        deleteQueueDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteQueue();
                Toast.makeText(getApplicationContext(), "Your queue has been cancel",Toast.LENGTH_SHORT).show();
                queueListView.setVisibility(View.INVISIBLE);
                addQueueButton.setVisibility(View.VISIBLE);
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
                Toast.makeText(getApplicationContext(), "Your queue has been made",Toast.LENGTH_SHORT).show();
                queues.clear();
                queues.add(Storage.getInstance().getCustomerList().get(customerId).getQueue());
                queueListView.setVisibility(View.VISIBLE);
                addQueueButton.setVisibility(View.INVISIBLE);
                queueCardAdapter.notifyDataSetChanged();
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

    }
}
