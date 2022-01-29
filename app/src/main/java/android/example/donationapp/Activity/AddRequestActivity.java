package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.example.donationapp.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView backbutton, addpic;
    TextInputLayout patientNameBox, patientGenderBox, patientAddressBox, patientContactBox, patientEmailBox, patientTitleBox, patientDescriptiionBox;
    TextInputEditText patientName, patientGender, patientAdddress, patientContact, patientEmail, requestTitle, requestDescription;
    Button submit;
    EditText dob;
    Spinner spinner;
    String blood_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        patientNameBox = findViewById(R.id.patient_name_enter_box);
        patientGenderBox = findViewById(R.id.patient_gender_enter_box);
        patientAddressBox = findViewById(R.id.patient_address_enter_box);
        patientContactBox = findViewById(R.id.patient_contact_enter_box);
        patientEmailBox = findViewById(R.id.patient_email_enter_box);
        patientTitleBox = findViewById(R.id.patient_title_enter_box);
        patientDescriptiionBox = findViewById(R.id.patient_description_enter_box);

        patientName = findViewById(R.id.patient_name_enter);
        patientGender = findViewById(R.id.patient_gender_enter);
        patientAdddress = findViewById(R.id.patient_address_enter);
        patientContact = findViewById(R.id.patient_contact_enter);
        patientEmail = findViewById(R.id.patient_email_enter);
        requestTitle = findViewById(R.id.patient_title_enter);
        requestDescription = findViewById(R.id.patient_description_enter);

        backbutton = findViewById(R.id.back_button);
        addpic = findViewById(R.id.add_request_pic);
        submit = findViewById(R.id.request_submit_button);
        dob = findViewById(R.id.patient_dob_enter);
        spinner = findViewById(R.id.blood_group_spinner);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);




        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                        dob.setText(dayofMonth+ " / "+ (month+1)+ " /"+ year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        blood_group = spinner.getSelectedItem().toString();
        Toast.makeText(AddRequestActivity.this, blood_group, Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!patientName.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientName.setError("Enter Name");
                }
                if(!patientEmail.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientEmail.setError("Enter Email");
                }
                if(!patientGender.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientGender.setError("Enter Gender");
                }
                if(!patientAdddress.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientAdddress.setError("Enter Address");
                }
                if(!requestTitle.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestTitle.setError("Enter Title");
                }
                if(!requestDescription.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestDescription.setError("Enter Description");
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