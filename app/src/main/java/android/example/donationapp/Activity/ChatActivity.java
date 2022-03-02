package android.example.donationapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.donationapp.Adapters.MessageAdapter;
import android.example.donationapp.Model.MessageClass;
import android.example.donationapp.R;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    String rName, receiverUid, senderUid;
    Toolbar toolbar;
    ImageView sendButton;
    EditText textView;
    RecyclerView recyclerView;

    MessageAdapter messageAdapter;
    ArrayList<MessageClass> messageClass;
    String senderRoom, receiverRoom;
    MessageClass message;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rName = getIntent().getStringExtra("name");
        receiverUid = getIntent().getStringExtra("uid");
        senderUid = FirebaseAuth.getInstance().getUid();

        toolbar = findViewById(R.id.action_bar);
        sendButton = findViewById(R.id.send_button);
        textView = findViewById(R.id.message_inputET);
        recyclerView = findViewById(R.id.message_recycler);

        firebaseDatabase = FirebaseDatabase.getInstance();


        messageClass = new ArrayList<MessageClass>();
        messageAdapter = new MessageAdapter(messageClass, this);

        senderRoom = senderUid + receiverUid;
        receiverRoom = receiverUid + senderUid;

        firebaseDatabase.getReference().child("chats")
                .child(senderRoom)
                .child("messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        messageClass.clear();


                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                            MessageClass message = snapshot1.getValue(MessageClass.class);
                            messageClass.add(message);
                            Log.e("Message is",message.getMessage());

                        }
                        messageAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(ChatActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                String mssg = textView.getText().toString();
                if(mssg.equals(""))
                {
                    textView.setError("Enter Text");
                    return;
                }
                String messageTime, messageDate;
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat messageCurrentDate = new SimpleDateFormat("MMM dd, yyyy");
                messageDate = messageCurrentDate.format(calendar.getTime());

                SimpleDateFormat messageCurrentTime = new SimpleDateFormat("hh:mm a");
                messageTime = messageCurrentTime.format(calendar.getTime());


                 message = new MessageClass(mssg, senderUid, messageTime, messageDate);
                 textView.setText("");

                HashMap<String , Object> lastMsgObj = new HashMap<>();
                if(message.getMessage().equals("")){
                    lastMsgObj.put("lastMsg", "Tap to Message");
                }else{
                    lastMsgObj.put("lastMsg" , message.getMessage());
                }
                lastMsgObj.put("lastMsgTime",messageTime);
                lastMsgObj.put("lastMsgdate",messageDate);
                firebaseDatabase.getReference().child("chats").child(senderRoom).updateChildren(lastMsgObj);
                firebaseDatabase.getReference().child("chats").child(receiverRoom).updateChildren(lastMsgObj);

                 String randomkey = firebaseDatabase.getReference().push().getKey();
                 assert randomkey != null;

                 firebaseDatabase.getReference().child("chats")
                         .child(senderRoom)
                         .child("messages")
                         .child(randomkey)
                         .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {

                         firebaseDatabase.getReference().child("chats")
                                 .child(receiverRoom)
                                 .child("messages")
                                 .child(randomkey)
                                 .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {

                                 Toast.makeText(ChatActivity.this, "Message Updated", Toast.LENGTH_SHORT).show();

                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {

                                 Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         });
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {

                         Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });


            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(rName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messageAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}