package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class EditRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ImageView backbutton, addpic;
    TextInputLayout patientNameBox, patientGenderBox, patientAddressBox, patientContactBox, patientEmailBox, patientTitleBox, patientDescriptiionBox;
    TextInputEditText patientName, patientGender, patientAdddress, patientContact, patientEmail, requestTitle, requestDescription;
    Button submit;
    TextView dob;
    Spinner spinner;
    String blood_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_request);

        patientNameBox = findViewById(R.id.patient_name_enter_box1);
        patientGenderBox = findViewById(R.id.patient_gender_enter_box1);
        patientAddressBox = findViewById(R.id.patient_address_enter_box1);
        patientContactBox = findViewById(R.id.patient_contact_enter_box1);
        patientEmailBox = findViewById(R.id.patient_email_enter_box1);
        patientTitleBox = findViewById(R.id.patient_title_enter_box1);
        patientDescriptiionBox = findViewById(R.id.patient_description_enter_box1);

        patientName = findViewById(R.id.patient_name_enter1);
        patientGender = findViewById(R.id.patient_gender_enter1);
        patientAdddress = findViewById(R.id.patient_address_enter1);
        patientContact = findViewById(R.id.patient_contact_enter1);
        patientEmail = findViewById(R.id.patient_email_enter1);
        requestTitle = findViewById(R.id.patient_title_enter1);
        requestDescription = findViewById(R.id.patient_description_enter1);

        backbutton = findViewById(R.id.back_button1);
        addpic = findViewById(R.id.add_request_pic1);
        submit = findViewById(R.id.request_submit_button1);
        dob = findViewById(R.id.patient_dob_enter1);
        spinner = findViewById(R.id.blood_group_spinner1);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);




        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        Toast.makeText(EditRequestActivity.this, blood_group, Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!patientName.getText().toString().isEmpty())
                {
                    Toast.makeText(EditRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientName.setError("Enter Name");
                }
                if(!patientEmail.getText().toString().isEmpty())
                {
                    Toast.makeText(EditRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientEmail.setError("Enter Email");
                }
                if(!patientGender.getText().toString().isEmpty())
                {
                    Toast.makeText(EditRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientGender.setError("Enter Gender");
                }
                if(!patientAdddress.getText().toString().isEmpty())
                {
                    Toast.makeText(EditRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientAdddress.setError("Enter Address");
                }
                if(!requestTitle.getText().toString().isEmpty())
                {
                    Toast.makeText(EditRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestTitle.setError("Enter Title");
                }
                if(!requestDescription.getText().toString().isEmpty())
                {
                    Toast.makeText(EditRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
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