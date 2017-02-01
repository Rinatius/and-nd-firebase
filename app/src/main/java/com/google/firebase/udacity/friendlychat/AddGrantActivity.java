package com.google.firebase.udacity.friendlychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddGrantActivity extends AppCompatActivity {

    //Firebase variables
    private FirebaseDatabase grantsDB;
    private DatabaseReference grantsReference;

    //Interface variables
    private EditText grantName;
    private EditText grantDescription;
    private EditText grantTagsEdit;
    private EditText grantDeadlineEdit;
    private Button publishButton;

    private ListView grantsListView;
    private GrantsListAdapter grantsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grants_layout);

        //Initialize Firebase database and reference

        grantsDB = FirebaseDatabase.getInstance();
        grantsReference = grantsDB.getReference().child("grants");

        //Initialize interface

        grantName = (EditText) findViewById(R.id.grantName);
        grantDescription = (EditText)findViewById(R.id.grantDescription);
        grantTagsEdit = (EditText)findViewById(R.id.grantTagsEdit);
        grantDeadlineEdit = (EditText)findViewById(R.id.grantDeadlineEdit);

        publishButton = (Button)findViewById(R.id.publishButton);

        grantsListView = (ListView)findViewById(R.id.grantsListView);
        grantsListAdapter = new GrantsListAdapter(this);
        grantsListView.setAdapter(grantsListAdapter);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Grant grant = new Grant(grantName.getText().toString(),
                                        grantDescription.getText().toString());
                grant.setTags(grantTagsEdit.getText().toString());
                grant.setDeadline(grantDeadlineEdit.getText().toString());


                grantsReference.push().setValue(grant);
            }
        });

        grantsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grant grant = dataSnapshot.getValue(Grant.class);
                grant.setId(dataSnapshot.getKey());
                grantsListAdapter.add(grant);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        grantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grant grant = (Grant)grantsListAdapter.getItem(position);
                Intent intent = new Intent(AddGrantActivity.this, GrantActivity.class);
                intent.putExtra("grantId", grant.getId());
                startActivity(intent);
            }
        });
    }
}
