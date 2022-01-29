package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.example.donationapp.R;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NGOEditActivity extends AppCompatActivity {

    TextInputLayout editNamebox, editDescriptionbox, editAddressbox;
    TextInputLayout editEmailbox, editPasswordbox, editContactbox;
    TextInputEditText editNGOname, editNGOdescription, editNGOaddress, editNGOemail, editNGOcontact, editNGOpassword;
    ImageView backbutton, imageEditbutton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoedit);

        editNamebox = findViewById(R.id.name_edit_box);
        editDescriptionbox = findViewById(R.id.ngo_description_edit_box);
        editAddressbox = findViewById(R.id.ngo_address_edit_box);
        editEmailbox = findViewById(R.id.ngo_email_edit_box);
        editPasswordbox = findViewById(R.id.ngo_password_edit_box);
        editContactbox = findViewById(R.id.ngo_contact_edit_box);

        editNGOname = findViewById(R.id.ngo_name_edit);
        editNGOdescription = findViewById(R.id.ngo_description_edit);
        editNGOaddress = findViewById(R.id.ngo_address_edit);
        editNGOcontact = findViewById(R.id.ngo_contact_name_edit);
        editNGOemail = findViewById(R.id.ngo_email_edit);
        editNGOpassword = findViewById(R.id.ngo_password_edit);

        backbutton = findViewById(R.id.back_button);
        imageEditbutton = findViewById(R.id.ngo_profile_pic_add);
        saveButton = findViewById(R.id.ngo_edit_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editNGOname.getText().toString().isEmpty())
                {
                    Toast.makeText(NGOEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else{
                    editNGOname.setError("Enter Name");
                }
                if(!editNGOdescription.getText().toString().isEmpty())
                {
                    Toast.makeText(NGOEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editNGOdescription.setError("Enter Description");
                }
                if(!editNGOaddress.getText().toString().isEmpty())
                {
                    Toast.makeText(NGOEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editNGOaddress.setError("Enter Address");
                }
                if(!editNGOcontact.getText().toString().isEmpty())
                {
                    Toast.makeText(NGOEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editNGOcontact.setError("Enter Contact");
                }
                if(!editNGOemail.getText().toString().isEmpty())
                {
                    Toast.makeText(NGOEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editNGOemail.setError("Enter Email");
                }
                if(!editNGOpassword.getText().toString().isEmpty())
                {
                    Toast.makeText(NGOEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editNGOpassword.setError("Enter Password");
                }
            }
        });

    }
}