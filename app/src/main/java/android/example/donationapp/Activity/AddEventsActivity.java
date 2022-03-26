package android.example.donationapp.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.example.donationapp.Model.EventClass;
import android.example.donationapp.R;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class AddEventsActivity extends AppCompatActivity {

    TextInputLayout editEventTitle, editEventDescription, editEventAddress, editEventTime, editEventDate, editContactDetails, editEmail;
    TextInputEditText title, description, address, contactDetails, email;
    TextView time, date;
    ImageView backbutton, imageEditButton, eventImage;
    Button saveButton;
    String sImageURL = "null";

    FirebaseUser currentUser;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth;
    EventClass eventClass;
    Bitmap bitmap;
    Uri selectedImage;
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference storageReference;
    DocumentReference documentReference;
    CollectionReference collectionReference = firebaseFirestore.collection("UserInformation");
    String ngoName;
    String nUID;


    int hour;
    int minute;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        selectedImage = Objects.requireNonNull(data).getData();
                        bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImage);
                            eventImage.setImageBitmap(bitmap);
                            imageEditButton.setVisibility(View.GONE);
                            Log.e("Inside OnActivity",bitmap.toString());

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        eventImage = findViewById(R.id.profile_pic);

        backbutton = findViewById(R.id.back);
        imageEditButton = findViewById(R.id.profile_pic_add);
        saveButton = findViewById(R.id.submit);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();




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

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stitle = title.getText().toString();
                String sdescription = description.getText().toString();
                String saddress = address.getText().toString();
                String stime = time.getText().toString();
                String sdate = date.getText().toString();
                String scontact = contactDetails.getText().toString();
                String semail = email.getText().toString();


                if(stitle.isEmpty())
                {
                    title.setError("Enter Event Name.");
                }

                if(sdescription.isEmpty())
                {
                    description.setError("Enter Description.");
                }

                if(saddress.isEmpty())
                {
                    address.setError("Enter Address.");
                }

                if(stime.isEmpty())
                {
                    time.setError("Enter Time.");
                }

                if(sdate.isEmpty())
                {
                    date.setError("Enter Date.");
                }

                if(scontact.isEmpty())
                {
                    contactDetails.setError("Enter Contact Details.");
                }

                if(semail.isEmpty())
                {
                    email.setError("Enter Email.");
                }

                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                        {

                                nUID = documentSnapshot.getString("uId");
                                if (currentUser.getUid().equalsIgnoreCase(nUID)) {
                                    ngoName = documentSnapshot.getString("name");
                                    Log.e("Ngo Name in ADDEvent", ngoName.toString());
                                    Log.e("UID of NGOADD is", nUID);
                                    eventClass = new EventClass(stitle, sdate, stime, sdescription, sImageURL, saddress, scontact, semail, currentUser.getUid(), ngoName, generatedString);

                                }
                            }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AddEventsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                eventClass = new EventClass(stitle, sdate, stime, sdescription, sImageURL, saddress, scontact, semail, currentUser.getUid(), ngoName ,generatedString);
//                Log.e("Ngo Name", ngoName.toString());
//                Log.e("UID of NGO is", nUID);


                if(selectedImage != null)
                {
                    Log.e("Inside if condition", selectedImage.toString());

                    storageReference = firebaseStorage.getReference().child("images").child(generatedString);
                    documentReference = FirebaseFirestore.getInstance().collection("Events").document(currentUser.getUid()).collection("random").document(generatedString);

                    storageReference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.e("Image uploaded", "     .");
                            Toast.makeText(AddEventsActivity.this, "Image Uploaded.", Toast.LENGTH_SHORT).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageURL = uri.toString();
                                    Toast.makeText(AddEventsActivity.this, "Path is " + sImageURL, Toast.LENGTH_SHORT).show();

                                    Log.e("Path found", imageURL);


                                    firebaseFirestore.collection("Events").document(currentUser.getUid()).collection("random").document(generatedString).set(eventClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.e("Successfully Updation", "Success");

                                            documentReference.update("eImageUrl", imageURL);

//                                            Log.e("Ngo Name in A", ngoName.toString());
//                                            Log.e("UID of NGOA is", nUID);
                                            Toast.makeText(AddEventsActivity.this, "Event Created.", Toast.LENGTH_SHORT).show();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AddEventsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddEventsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();;
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEventsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else
                {
                    firebaseFirestore.collection("Events").document(currentUser.getUid()).collection("random").document(generatedString).set(eventClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Log.e("Successfully Updation", "Success");
                            Toast.makeText(AddEventsActivity.this, "Event Created.", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddEventsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }





            }
        });


        imageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                someActivityResultLauncher.launch(intent);

                Toast.makeText(AddEventsActivity.this, "Image Recieved", Toast.LENGTH_SHORT).show();

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