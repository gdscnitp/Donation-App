package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.donationapp.R;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class NGOEventDetail extends AppCompatActivity {

    ImageView phn, mail, pic;
    TextView ngoName, eventName, eventTime, eventDate, eventDescription, eventAddress;
    ImageView editbutton;

    String phoneNo, emailId, eventPic, nName, eName, eTime, eDate, eDescription, eAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoevent_detail);

        phn = findViewById(R.id.ngo_phone_vector);
        mail = findViewById(R.id.ngo_mail_vector);
        pic = findViewById(R.id.ngo_event_photo);
        ngoName = findViewById(R.id.ngo1_name_info);
        eventAddress = findViewById(R.id.ngo1_address_info);
        eventDate = findViewById(R.id.ngo_event_date_info);
        eventDescription = findViewById(R.id.ngo1_event_description_less);
        eventName = findViewById(R.id.ngo1_event_name);
        eventTime = findViewById(R.id.ngo1_time_info);

        editbutton = findViewById(R.id.ngo_event_edit);

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NGOEventDetail.this, EditEventActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b != null)
        {

            phoneNo =(String) b.get("eventPhone");
            emailId =(String) b.get("eventMail");
            eventPic =(String) b.get("eventPic");
            nName = (String) b.get("ngoName");
            eName = (String) b.get("eventName");
            eTime =(String) b.get("eventTime");
            eDate = (String) b.get("ngoDate");
            eDescription = (String) b.get("eventDescription");
            eAddress = (String) b.get("eventAddress");


        }

        Glide.with(this).load(eventPic).placeholder(R.drawable.request_detail_demo).into(pic);
        ngoName.setText(nName);
        eventName.setText(eName);
        eventAddress.setText(eAddress);
        eventTime.setText(eTime);
        eventDescription.setText(eDescription);
        eventDate.setText(eDate);

        phn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri number = Uri.parse(phoneNo);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                try {
                    startActivity(callIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(NGOEventDetail.this, "Application not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:" + emailId));

                try {
                    startActivity(mailIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(NGOEventDetail.this, "Application not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}