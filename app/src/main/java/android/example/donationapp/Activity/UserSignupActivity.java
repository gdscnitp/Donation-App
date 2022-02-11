package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class UserSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputLayout nameBox, phoneBox, emailBox, passwordBox, addressBox, weightBox;
    TextInputEditText nameEnter, phoneEnter, emailEnter, passwordEnter, addressEnter, weightEnter;
    Button submit;
    Spinner spinner;
    ImageView backbutton, picEnter;
    CheckBox donateCheck;
    TextView dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        nameBox = findViewById(R.id.user_name_box);
        phoneBox = findViewById(R.id.user_phone_box);
        emailBox = findViewById(R.id.user_email_box);
        passwordBox = findViewById(R.id.user_password_box);
        addressBox = findViewById(R.id.user_address_box);
        weightBox = findViewById(R.id.user_weight_box);

        nameEnter = findViewById(R.id.user_name_enter);
        phoneEnter = findViewById(R.id.user_phone_enter);
        emailEnter = findViewById(R.id.user_email_enter);
        passwordEnter = findViewById(R.id.user_password_enter);
        addressEnter = findViewById(R.id.user_address_enter);
        weightEnter = findViewById(R.id.user_weight_enter);

        spinner = findViewById(R.id.user_gender_enter_spinner);
        backbutton = findViewById(R.id.back_button_userSignUp);
        picEnter = findViewById(R.id.userSignUp_profile_pic_add);
        donateCheck = findViewById(R.id.user_donor_checkbox);
        dob = findViewById(R.id.user_dob_enter);
        submit = findViewById(R.id.userSignUp_submit);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserSignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {

                        dob.setText(dayOfmonth+" / "+(month+1)+" / "+year);

                    }
                },year, month, day);
                    datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        String spinnerData = spinner.getSelectedItem().toString();
        Toast.makeText(UserSignupActivity.this, spinnerData, Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nameEnter.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nameEnter.setError("Enter Name");
                }
                if(!dob.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dob.setError("Enter DOB");
                }
                if(!spinnerData.isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UserSignupActivity.this, "Choose Gender", Toast.LENGTH_SHORT).show();
                }
                if(!phoneEnter.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    phoneEnter.setError("Enter Phone Number");
                }
                if(!emailEnter.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    emailEnter.setError("Enter Email");
                }
                if(!passwordEnter.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    passwordEnter.setError("Enter Password");
                }
                if(!addressEnter.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addressEnter.setError("Enter Address");
                }
                if(!weightEnter.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    weightEnter.setError("Enter Weight");
                }

                boolean ischecked=donateCheck.isChecked();
                if(ischecked)
                {
                    Toast.makeText(UserSignupActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UserSignupActivity.this, "Not Checked", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}