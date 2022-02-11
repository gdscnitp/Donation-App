package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

public class AddEventsActivity extends AppCompatActivity {

    TextInputLayout editEventTitle, editEventDescription, editEventAddress, editEventTime, editEventDate, editContactDetails, editEmail;
    TextInputEditText title, description, address, contactDetails, email;
    TextView time, date;
    ImageView backbutton, imageEditButton;
    Button saveButton;

    int hour;
    int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        editEventTitle = findViewById(R.id.event_edit_box);
        editEventDescription = findViewById(R.id.description_edit_box);
        editEventAddress = findViewById(R.id.address_edit_box);
        editEventTime = findViewById(R.id.time_edit_box);
        editEventDate = findViewById(R.id.date_edit_box);
        editContactDetails = findViewById(R.id.contact_edit_box);
        editEmail = findViewById(R.id.email_edit_box);

        title = findViewById(R.id.event_edit);
        description = findViewById(R.id.description_edit);
        address = findViewById(R.id.address_edit);
        time = findViewById(R.id.time_edit);
        date = findViewById(R.id.date_edit);
        contactDetails = findViewById(R.id.contact_edit);
        email = findViewById(R.id.email_edit);

        backbutton = findViewById(R.id.back);
        imageEditButton = findViewById(R.id.profile_pic_add);
        saveButton = findViewById(R.id.submit);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEventsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                        date.setText(dayofMonth+ " / "+ (month+1)+ " /"+ year);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        /*time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EventsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);

                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                    }
                }, hour, minute, true);//Yes 24 hour time
            }
        });*/

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!title.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else{
                    title.setError("Enter Event Name.");
                }

                if(!description.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    description.setError("Enter Description.");
                }

                if(!address.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    address.setError("Enter Address.");
                }

                if(!time.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, time.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    time.setError("Enter Time.");
                }

                if(!date.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, date.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    date.setError("Enter Date.");
                }

                if(!contactDetails.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    contactDetails.setError("Enter Contact Details.");
                }

                if(!email.getText().toString().isEmpty())
                {
                    Toast.makeText(AddEventsActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    email.setError("Enter Email.");
                }
            }
        });




    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minutet) {
                hour = hourOfDay;
                minute = minutet;
                time.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);

        timePickerDialog.show();
    }


}