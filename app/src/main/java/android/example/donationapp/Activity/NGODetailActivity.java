package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.example.donationapp.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NGODetailActivity extends AppCompatActivity {

    ImageView backButton;
    ImageView phoneCall, email, share;
    ImageView ngoPic;
    TextView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodetail);

        backButton = findViewById(R.id.ngo_detail_backarrow);
        phoneCall = findViewById(R.id.phone_vector_ngodetail);
        email = findViewById(R.id.mail_vector_ngodetail);
        share = findViewById(R.id.share_vector_ngodetail);
        ngoPic = findViewById(R.id.ngo_profile_pic);
        more = findViewById(R.id.ngo_detail_more);

    }
}