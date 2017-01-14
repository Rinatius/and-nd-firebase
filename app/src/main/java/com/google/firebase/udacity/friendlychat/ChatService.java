package com.google.firebase.udacity.friendlychat;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatService extends Service {
    private static final int ONGOING_NOTIFICATION_ID = 1;

    //Firebase variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;

    public ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Start foreground mode
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
                .build();

        startForeground(ONGOING_NOTIFICATION_ID, notification);
        Toast.makeText(ChatService.this, "Chat service started!", Toast.LENGTH_LONG).show();

        //Init references to database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("messages");

        attachDatabaseListener();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        detachDatabaseListener();
        super.onDestroy();
    }

    private void attachDatabaseListener() {
        if (mChildEventListener == null){
            // Listens to changes in the database
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    // Get data from database and deserialize
                    FriendlyMessage fm = dataSnapshot.getValue(FriendlyMessage.class);

                    Toast.makeText(ChatService.this, fm.getText(), Toast.LENGTH_LONG).show();

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
            };

            //Connects listener to specific reference in the database
            mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseListener() {
        if (mChildEventListener != null) {
            mMessagesDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }

    }

}
