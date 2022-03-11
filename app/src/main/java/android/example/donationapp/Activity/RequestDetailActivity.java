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

public class RequestDetailActivity extends AppCompatActivity {

    String random, name, gender, dob, blood, address, contact, email, title, description, image;

    ImageView imageView, phoneiv, emailiv;
    TextView headingtv, nametv, bloodtv, addresstv, descriptiontv, gendertv, agetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);


        imageView = (ImageView) findViewById(R.id.request_photo);
        phoneiv = (ImageView) findViewById(R.id.phone_vector_request);
        emailiv = (ImageView) findViewById(R.id.mail_vector_request);

        headingtv = (TextView) findViewById(R.id.request_name);
        nametv = (TextView) findViewById(R.id.patient_name_request);
        gendertv = (TextView) findViewById(R.id.patient_gender_request);
        agetv = (TextView) findViewById(R.id.patient_age_request);
        bloodtv = (TextView) findViewById(R.id.patient_blood_request);
        addresstv = (TextView) findViewById(R.id.request_detail_address);
        descriptiontv = (TextView) findViewById(R.id.request_detail_description_less);



        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            random = (String) b.get("id");
            name = (String) b.get("name");
            gender = (String) b.get("gender");
            dob = (String) b.get("dob");
            blood = (String) b.get("blood");
            address = (String) b.get("address");
            contact = (String) b.get("contact");
            email = (String) b.get("email");
            title = (String) b.get("title");
            description = (String) b.get("description");
            image = (String) b.get("image");



        }

        Glide.with(this).load(image).into(imageView);
        headingtv.setText(title);
        nametv.setText(name);
        gendertv.setText(gender);
        agetv.setText("DOB: " + dob);
        bloodtv.setText(blood);
        addresstv.setText(address);
        descriptiontv.setText(description);

        phoneiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse(contact);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                try {
                    startActivity(callIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RequestDetailActivity.this, "Application not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        emailiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:" + email));

                try {
                    startActivity(mailIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RequestDetailActivity.this, "Application not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}