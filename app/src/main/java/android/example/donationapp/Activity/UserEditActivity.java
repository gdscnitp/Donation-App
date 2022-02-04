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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class UserEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TextInputLayout nameUsereditbox, phoneUsereditbox, emailUsereditbox, passwordUsereditbox, addressUsereditbox, weightUsereditbox;
    TextInputEditText nameUserEdit, phoneUserEdit, emailUserEdit,passwordUserEdit, addressUserEdit, weightUserEdit;
    Button save;
    ImageView userEditBackButton, userPicEdit;
    CheckBox userEditDonate;
    EditText dobuseredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        nameUsereditbox = findViewById(R.id.user_name_editbox);
        phoneUsereditbox = findViewById(R.id.user_phone_editbox);
        emailUsereditbox = findViewById(R.id.user_email_editbox);
        passwordUsereditbox = findViewById(R.id.user_password_editbox);
        addressUsereditbox = findViewById(R.id.user_address_editbox);
        weightUsereditbox = findViewById(R.id.user_weight_editbox);

        nameUserEdit = findViewById(R.id.user_name_edit);
        phoneUserEdit = findViewById(R.id.user_phone_edit);
        emailUserEdit = findViewById(R.id.user_email_edit);
        passwordUserEdit = findViewById(R.id.user_password_edit);
        addressUserEdit = findViewById(R.id.user_address_edit);
        weightUserEdit = findViewById(R.id.user_weight_edit);

        save = findViewById(R.id.user_edit_save);
        userEditBackButton = findViewById(R.id.back_button_useredit);
        userPicEdit = findViewById(R.id.useredit_profile_pic_add);
        spinner = findViewById(R.id.user_gender_edit_spinner);
        userEditDonate = findViewById(R.id.user_donor_checkbox_edit);
        dobuseredit = findViewById(R.id.user_dob_edit);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dobuseredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {

                        dobuseredit.setText(dayofMonth+" / "+(month+1)+" / "+year);
                    }

                },year, day, month);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        String editedGender = spinner.getSelectedItem().toString();
        Toast.makeText(UserEditActivity.this, editedGender, Toast.LENGTH_SHORT).show();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nameUserEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(UserEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nameUserEdit.setError("Enter Name");
                }
                if(!phoneUserEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(UserEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    phoneUserEdit.setError("Enter Phone");
                }
                if(!emailUserEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(UserEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    emailUserEdit.setError("Enter Email");
                }
                if(!passwordUserEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(UserEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    passwordUserEdit.setError("Enter Password");
                }
                if(!addressUserEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(UserEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    addressUserEdit.setError("Enter Address");
                }
                if(!weightUserEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(UserEditActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    weightUserEdit.setError("Enter Weight");
                }

                boolean ischecked=userEditDonate.isChecked();
                if(ischecked)
                {
                    Toast.makeText(UserEditActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UserEditActivity.this, "Not Checked", Toast.LENGTH_SHORT).show();
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